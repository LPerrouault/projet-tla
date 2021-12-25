package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe implémente une analyse syntaxique, paramétrée dans le constructeur
 * La méthode analyse() effectue l'analyse d'une chaîne entrée et retourne soit la liste des tokens reconnus, soit lève une exception
 */
public class AnalyseLexicale {

	/**
	 * Default constructor
	 */
	public AnalyseLexicale() {
		// paramètres de l'algorithme d'analyse lexicale

		this.TRANSITIONS = new Integer[][]{
			//         espace chiffre lettre   (    )   ,
			/* 0 */  {      0,     1,     2,null, 104, 105}, //début de lecture
			/* 1 */  {    101,     1,   101, 101, 101, 101}, //après un chiffre
			/* 2 */  {    103,     2,     2, 102, 103, 103}  //après une lettre

			// 101 accepte entier                   (goBack : oui)
			// 102 accepte nom de fonction          (goBack : non)
			// 103 accepte identifiant              (goBack : oui)
			// 104 accepte )                        (goBack : non)
                        // 105 accepte ,                        (goBack : non)
		};

		this.ETAT_INITIAL = 0;

	}

	/**
	 * Table de transition de l'automate
	 */
	private Integer TRANSITIONS[][];

	/**
	 * Numéro de l'état par lequel l'automate commence.
	 */
	private Integer ETAT_INITIAL;

	private int indiceSymbole(Character c) throws IllegalCharacterException {
		if (c == null) return 0;
		if (Character.isWhitespace(c)) return 0;
		if (Character.isDigit(c)) return 1;
		if (Character.isLetter(c)) return 2;
		if (c == '(') return 3;
		if (c == ')') return 4;
                if (c == ',') return 5;
		throw new IllegalCharacterException();
	}

	public List<Token> analyse(String entree) throws Exception {

		List<Token> tokens = new ArrayList<>();
		Integer etat = this.ETAT_INITIAL;
		SourceReader sr = new SourceReader(entree);
		String buf = "";

		Character c;

		do {
			// si la fin d'entrée est atteinte, c est null, et il faut traiter une dernière transition
			// d'ou la boucle do/while, avec lecture d'un symbole au début du corps de la boucle
			// et condition while à la fin de la boucle
			c = sr.lectureSymbole();
			Integer e = TRANSITIONS[etat][indiceSymbole(c)];
			System.out.println("Symbole " + (c==null?"null":"'"+c+"'") + " transition " + etat + " -> " + e);
			if (e == null) {
				System.out.println(" pas de transition depuis état " + etat + " avec symbole " + c);
				throw new LexicalErrorException(" pas de transition depuis état " + etat + " avec symbole " + c);
			}
			if (e >= 100) {
				// un état d'acceptation a été atteint
				// -> création du token correspondant
				if (e == 101) {
					tokens.add(new Token(TokenClass.intVal));
                                        sr.goBack();
				} else if (e == 102) {
                                    if (buf.equals("playerStart")) {
                                        tokens.add(new Token(TokenClass.playerStart));
                                    } else if (buf.equals("dioStart")) {
                                        tokens.add(new Token(TokenClass.dioStart));
                                    } else if (buf.equals("dioActions")) {
                                        tokens.add(new Token(TokenClass.dioActions));
                                    } else if (buf.equals("dioMove")) {
                                        tokens.add(new Token(TokenClass.dioMove));
                                    } else if (buf.equals("dioPrepare")) {
                                        tokens.add(new Token(TokenClass.dioPrepare));
                                    } else if (buf.equals("dioAttaque")) {
                                        tokens.add(new Token(TokenClass.dioAttaque));
                                    } else if (buf.equals("playerWait")) {
                                        tokens.add(new Token(TokenClass.playerWait));
                                    } else if (buf.equals("setWalls")) {
                                        tokens.add(new Token(TokenClass.setWalls));
                                    }
				} else if (e == 103) {
                                    if (buf.equals("couteau")) {
                                        tokens.add(new Token(TokenClass.couteau));
                                    } else if (buf.equals("rouleau")) {
                                        tokens.add(new Token(TokenClass.rouleau));
                                    }
                                    sr.goBack();
				} else if (e == 104) {
					tokens.add(new Token(TokenClass.rightPar));
				} else if (e == 105) {
                                        tokens.add(new Token(TokenClass.comma));
                                }
				// un état d'acceptation a été atteint
				// retourne à l'état 0
				etat = 0;
				// reinitialise buf
				buf = "";
			} else {
				// pas dans un état d'acceptation
				etat = e;
				// ajoute le symbole qui vient d'être examiné à buf
				// sauf s'il s'agit un espace ou assimilé
				if (etat>0) buf = buf + c;
			}

		} while (c != null);

		return tokens;
	}

}
