package utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Utils {
	
	private String axiom;
	private List<String> V;
	private List<String> E;
	private Map<String, List<String>> productions;
	

	public Utils(String axiom, List<String> V,List<String> E ,Map<String, List<String>> productions) {
    this.axiom=axiom;
    this.V=V;
    this.E=E;
    this.productions=productions;
	}
	
	public boolean validate() {
		return axiomIsNotTerminal() && VisdisjuntcE() && correctProductions();
	}
	
	private boolean axiomIsNotTerminal() {
		return (V.contains(axiom));
	}

	private boolean VisdisjuntcE() {
		for (String terminals : E) {
			if (V.contains(terminals))
				return false;
		}
		return true;
	}

	private boolean correctProductions() {
		long keysError = productions.keySet().stream().filter(x -> !(V.contains(x))).count();
		if (keysError > 0) {
			return false;
		} else {
			Collection<List<String>> listAsign = productions.values();
			for (List<String> list : listAsign) {
				for (String asign : list) {
					char[] characters = asign.toCharArray();
					for (int i = 0; i < characters.length; i++) {
						if (!(V.contains("" + characters[i])) && !(E.contains("" + characters[i])))
							return false;
					}

				}
			}
			return true;
		}

	}

}
