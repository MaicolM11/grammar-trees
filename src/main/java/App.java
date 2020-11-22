import models.Tree;
import utils.Utils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

	static Utils utils;
	static PrintWriter printWriter=new PrintWriter(System.out);
	static Scanner sc = new Scanner(System.in);


	public static void main(String[] args) {
		int numCase=Integer.parseInt(sc.nextLine());
		for (int i = 0; i < numCase; i++) {
			printWriter.println("------------------------------------------------------------------------------");
			readData();	
			printWriter.println("------------------------------------------------------------------------------");
		}
		printWriter.close();
	}

	private static void readData() throws ArrayIndexOutOfBoundsException, NumberFormatException {
		int numProduction = Integer.parseInt(sc.nextLine());
		List<String> terminals = Arrays.asList(sc.nextLine().split(" "));
		List<String> notTerminals = Arrays.asList(sc.nextLine().split(" "));
		String axiom = sc.nextLine();
		Map<String, List<String>> productions = new HashMap<>();
		for (int i = 0; i < numProduction; i++) {
			String[] line = sc.nextLine().split(" ");
			String[] asign = new String[line.length - 1];
			System.arraycopy(line, 1, asign, 0, asign.length);
			productions.put(line[0], Arrays.asList(asign));
		}
		String word=sc.nextLine();
		evaluateCase(axiom, notTerminals, terminals, productions,word);
	}

	private static void evaluateCase(String axiom, List<String> V,List<String> E ,Map<String, List<String>> productions,String word) {
		utils = new Utils(axiom, V, E, productions);
		if (utils.validate()) {
			Tree tree = new Tree(V, axiom, productions);
			tree.print(printWriter);
			String result=tree.findWord(word);
			printWriter.println(result.isEmpty()?"La palabra no pertenece al lenguaje":"La palabra pertenece al lenguaje\n"+result);
		} else {
			System.err.println("Parametros invalidados");
		}
	}
}
