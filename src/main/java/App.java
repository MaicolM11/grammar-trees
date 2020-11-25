import models.Tree;
import utils.Utils;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    static PrintWriter printWriter = new PrintWriter(System.out);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int numCase = Integer.parseInt(sc.nextLine());
        try {
            for (int i = 1; i <= numCase; i++) {
                printWriter.println("-- CASO #" + i + " -------------------------------------------------------------------");
                readData();
                printWriter.println("------------------------------------------------------------------------------");
                printWriter.println();
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            printWriter.println("Error: invalid format");
        }
        printWriter.close();
    }

    private static void readData() throws ArrayIndexOutOfBoundsException, NumberFormatException {
        sc.nextLine();
        List<String> terminals = Arrays.asList(sc.nextLine().trim().split("\\s+"));
        List<String> notTerminals = Arrays.asList(sc.nextLine().trim().split("\\s+"));
        String axiom = sc.nextLine().trim();
        Map<String, List<String>> productions = new HashMap<>();
        int numProduction = Integer.parseInt(sc.nextLine().trim());
        for (int i = 0; i < numProduction; i++) {
            String[] line = sc.nextLine().trim().split("\\s+");
            String[] assign = new String[line.length - 1];
            System.arraycopy(line, 1, assign, 0, assign.length);
            productions.put(line[0], Arrays.asList(assign));
        }
        String word = sc.nextLine().trim();
        evaluateCase(axiom, notTerminals, terminals, productions, word);
    }

    // evalua los datos de la gramatica, imprime el arbol general - particular,
    private static void evaluateCase(String axiom, List<String> V, List<String> E, Map<String, List<String>> productions, String word) {
        Utils utils = new Utils(axiom, V, E, productions);
        if (utils.validate()) {
            Tree tree = new Tree(V, axiom, productions);
            printWriter.println("Arbol de derivación general: \n");
            tree.print(printWriter);
            String result = tree.findWord(word);
            printWriter.println();
            if (result.isEmpty()) {
                printWriter.println("La palabra " + word + " no pertenece al lenguaje");
            } else {
                printWriter.println("La palabra " + word + " pertenece al lenguaje");
                printWriter.println(result);
            }
        } else {
            printWriter.println("Parametros invalidados. Revise que todos los parametros cumplan con las reglas.\nEj: λ ∈ Σ");
        }
    }
}
