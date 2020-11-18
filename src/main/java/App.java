import models.Tree;

import java.util.List;
import java.util.Map;

public class App {

    static List<String> terminals = List.of("a", "b");
    static List<String> notTerminals = List.of("S", "A", "B", "C");
    static String axiom = "S";
    static Map<String, List<String>> productions = Map.of(
            "C", List.of("Ca", "b"),
            "B", List.of("Ba","Ca","bb"),
            "S", List.of("A","B"));


    public static void main(String[] args) {
        // validar parametros
        Tree tree = new Tree(notTerminals, axiom, productions);
        tree.print();
    }

}
