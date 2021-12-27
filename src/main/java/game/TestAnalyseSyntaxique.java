package game;

import java.util.List;

public class TestAnalyseSyntaxique {

	public static void main(String[] args) {

		testAnalyseLexicale("playerStart(2,1)");
		testAnalyseSyntaxique("playerStart(2,1)");
		/*
		testAnalyseSyntaxique("1 + 2 * (3 + 4)");
		testAnalyseSyntaxique("3 + (3 * 2)");
		testAnalyseSyntaxique("3 + (3 *  ");
		testAnalyseSyntaxique("3 + (3 * 2) )");
		testAnalyseSyntaxique("1 + (6 + 1) * 10 + 2 * 3");
		testAnalyseSyntaxique("3 + 2 + 7 * 3");
		testAnalyseSyntaxique("(1 + 2 * 3) + 4");
		testAnalyseSyntaxique("2 * (3 + 4)");
		testAnalyseSyntaxique("(2)*37");
		 */

		testAnalyseSyntaxique("3 * 3 + 7 * 7 + 4");

	}

	private static void testAnalyseLexicale(String entree) {
		System.out.println();
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			for(Token t: tokens) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static void testAnalyseSyntaxique(String entree) {
		System.out.println();
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			System.out.println("Liste des tokens :");
			for(Token t: tokens) {
				System.out.println(" " + t);
			}
			System.out.println("Analyse syntaxique :");

			new AnalyseSyntaxique().analyse(tokens);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
