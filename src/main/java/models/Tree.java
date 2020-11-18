package models;

import java.util.*;

public class Tree {

    public static final int MAX_LEVEL = 4;

    private final Node root;
    private final List<String> V;  // list of no terminals
    private final Map<String, List<String>> P; // map of properties

    public Tree(List<String> v, String axiom, Map<String, List<String>> p) {
        V = v;
        P = p;
        this.root = new Node(null, axiom, 0);
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
                Node aux = new Node(node, val.replaceFirst(character, replace), node.level + 1);
                node.nodeList.add(aux);
                buildTree(aux);
            }
        }
    }

    private OptionalInt findVariable(String info) {
        return info.chars().filter(x -> V.contains("" + (char) x)).findFirst();
    }

    public void print() {
        this.print(root);
    }

    private void print(Node node) {
        String word = findVariable(node.info).isPresent() ? node.info : "\u001B[31m" + node.info + "\u001B[0m";
        System.out.println("\t".repeat(node.level) + word);
        node.nodeList.forEach(this::print);
    }

}
