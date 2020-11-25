package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase nodo, trata de cada hijo, nodo, hoja que genere el arbol,
 */
public class Node {

    protected List<Node> nodeList;  // lista de nodos hijos
    protected String info;          // informacion del nodo, la palabra que contiene
    protected Node parent;          // nodo padre
    protected int level;            // nivel en el arbol

    public Node(Node parent, String info, int level) {
        this.info = info;
        this.level = level;
        this.nodeList = new ArrayList<>();
        this.parent = parent;
    }

}