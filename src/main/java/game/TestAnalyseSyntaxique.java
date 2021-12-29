package game;

import java.util.List;

public class TestAnalyseSyntaxique {

	public static void main(String[] args) {

		testAnalyseSyntaxique("dioActions(dioMove(1))");
		testAnalyseSyntaxique("setWalls(XXOBSYZVZYVZUTCYCTZC)");
		testAnalyseSyntaxique("setWalls(XXOBSYZVZYVZUTCYCTZC) dioActions(dioMove(5) dioPrepare(couteau) dioAttaque(couteau))");

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
