package game;

import java.util.List;

public class TestAnalyseLexicale {

	public static void main(String[] args) {
		test("playerStart(2,1)");
		test("playerStart (2,1)");
		test("playerStart(2,1) dioStart(2,5) dioActions(dioMove(5,9) dioPrepare(couteau) dioAttaque(couteau))");

	}

	private static void test(String entree) {
		System.out.println("\nAnalyse lexicale de '" + entree + "'");
		try {
			List<Token> tokens = new AnalyseLexicale().analyse(entree);
			System.out.println();
			for (Token t : tokens) {
				System.out.println(t);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
