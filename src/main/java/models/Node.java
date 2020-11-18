package models;

import java.util.ArrayList;
import java.util.List;

public class Node {

    protected List<Node> nodeList;
    protected String info;
    protected Node parent;
    protected int level;

    public Node(Node parent, String info, int level) {
        this.info = info;
        this.level = level;
        this.nodeList = new ArrayList<>();
        this.parent = parent;
    }

}