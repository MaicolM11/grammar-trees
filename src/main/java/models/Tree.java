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
        this.root = new Node(axiom, 0);
        init(root);
    }

    private void init(Node node) {
        if (node.level == MAX_LEVEL) return;
        String val = node.info; // abS
        OptionalInt noTerminal = findVariable(val); // {S} solo para un no terminal
        if (noTerminal.isPresent()) {
            String character = "" + (char) noTerminal.getAsInt(); // S
            List<String> replaces = P.getOrDefault(character, new ArrayList<>()); // S -> {xA}, {xxB}

            for (String replace : replaces) {  // S -> {xA}, {xxB}
                Node aux = new Node(node, val.replace(character, replace), node.level + 1);
                node.nodeList.add(aux);
                init(aux);
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
        String value = "\t".repeat(node.level) + node.info;
        if (findVariable(node.info).isPresent()) {
            System.out.println(value);
        } else {
            System.out.println("\u001B[31m" + value + "\u001B[0m");
        }
        node.nodeList.forEach(this::print);
    }

}
