package models;

import java.io.PrintWriter;
import java.util.*;

import models.Node;

public class Tree {

    public static final int MAX_LEVEL = 10;

    private final Node root;
    private final List<String> V;  // list of no terminals
    private final Map<String, List<String>> P; // map of properties
    private final List<Node> words;
    private final String axiom;

    public Tree(List<String> v, String axiom, Map<String, List<String>> p) {
        V = v;
        P = p;
        this.axiom=axiom;
        this.root = new Node(null, axiom, 0);
        words= new ArrayList<>();
        buildTree(root);
    }

    private void buildTree(Node node) {
        if (node.level == MAX_LEVEL) return;
        String val = node.info; // abS
        OptionalInt noTerminal = findVariable(val); // {S} solo para un no terminal
        if (noTerminal.isPresent()) {
            String character = "" + (char) noTerminal.getAsInt(); // S
            List<String> replaces = P.getOrDefault(character, new ArrayList<>()); // S -> {xA}, {xxB}

            for (String replace : replaces) {  // S -> {xA}, {xxB}
                Node aux = new Node(node, val.replaceFirst(character, replace.equals("λ")?"":replace), node.level + 1);
                node.nodeList.add(aux);
                buildTree(aux);
            }
        }
        else {
        	words.add(node);
        }
    }

    private OptionalInt findVariable(String info) {
        return info.chars().filter(x -> V.contains("" + (char) x)).findFirst();
    }

    public void print(PrintWriter printWriter) {
        this.print(root,printWriter);
    }

    private void print(Node node,PrintWriter printWriter) {
    	if (node.level==4) return ;
        String word = findVariable(node.info).isPresent() ? node.info : "\u001B[31m" + node.info + "\u001B[0m";
        printWriter.println("\t".repeat(node.level) + node.info);
        node.nodeList.forEach(x->print(x,printWriter));
    }
    
    public String findWord(String word) {
    	String exit="";
    	Optional<Node> searchNode=words.stream().filter(x-> x.info.equals(word)).findFirst();
    	if (searchNode.isPresent()) {
			for (Node i = searchNode.get(); i.parent != null ; i=i.parent) {
				exit="-->"+i.info + exit;
			}
			exit=axiom + exit;
		}
    	return exit;
    }
}