package utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {

    private final String axiom;
    private final List<String> V;
    private final List<String> E;
    private final Map<String, List<String>> productions;

    public Utils(String axiom, List<String> V, List<String> E, Map<String, List<String>> productions) {
        this.axiom = axiom;
        this.V = V;
        this.E = E;
        this.productions = productions;
    }

    public boolean validate() {
        return axiomIsNotTerminal() && VisdisjuntcE() && correctProductions();
    }

    private boolean axiomIsNotTerminal() {
        return V.contains(axiom);
    }

    private boolean VisdisjuntcE() {
        return E.stream().noneMatch(V::contains);
    }

    private boolean correctProductions() {
        long keysError = productions.keySet().stream().filter(x -> !(V.contains(x))).count();
        if (keysError > 0) return false;
        Collection<List<String>> listAssign = productions.values();
        for (List<String> list : listAssign) {
            for (String assign : list) {
                char[] characters = assign.toCharArray();
                for (char character : characters) {
                    if (!(V.contains("" + character)) && !(E.contains("" + character)))
                        return false;
                }
            }
        }
        return true;
    }

}
