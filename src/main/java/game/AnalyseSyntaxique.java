package game;

import java.util.List;

public class AnalyseSyntaxique {

    private int pos;
    private int profondeur;
    private List<Token> tokens;

    /*
    effectue l'analyse syntaxique à partir de la liste de tokens
    et retourne la valeur de l'expression
     */
    public void analyse(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        pos = 0;
        //S();
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
    }

    /*

    méthodes des symboles non terminaux

      */
    /*
    private void S() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.intVal ||
                getTokenClass() == TokenClass.leftPar) {

            // production S -> AS'

            profondeur++;
            A();
            profondeur--;
            S_prime();
            return;
        }
        throw new UnexpectedTokenException("intVal ou ( attendu");
    }

    private void S_prime() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.add) {

            // production S' -> +S

            getToken();
            printNode("+");
            profondeur++;
            S();
            profondeur--;
            return;
        }

        if (getTokenClass() == TokenClass.rightPar || isEOF()) {

            // production S' -> epsilon

            return;
        }

        throw new UnexpectedTokenException("+ ou ) attendu");
    }

    private void A() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.leftPar) {

            // production A -> ( S ) A'

            getToken();
            profondeur++;
            S();
            profondeur--;
            if (getTokenClass() == TokenClass.rightPar) {
                getToken();
                A_prime();
                return;
            }
            throw new UnexpectedTokenException(") attendu");
        }

        if (getTokenClass() == TokenClass.intVal) {

            // production A -> intVal A'

            Token tokIntVal = getToken();
            printNode(tokIntVal.getValue()); // affiche la valeur int
            A_prime();
            return;
        }

        throw new UnexpectedTokenException("intVal ou ( attendu");
    }

    private void A_prime() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.multiply) {

            // production A' -> * A

            getToken();
            printNode("*");
            A();
            return;
        }

        if (getTokenClass() == TokenClass.add ||
                getTokenClass() == TokenClass.rightPar ||
                isEOF()) {

            // production A' -> epsilon

            return;
        }
        throw new UnexpectedTokenException("* + ou ) attendu");

    }
    */

    /*

    autres méthodes

     */

    private boolean isEOF() {
        return pos >= tokens.size();
    }

    /*
     * Retourne la classe du prochain token à lire
     * SANS AVANCER au token suivant
     */
    private TokenClass getTokenClass() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            return tokens.get(pos).getCl();
        }
    }

    /*
     * Retourne le prochain token à lire
     * ET AVANCE au token suivant
     */
    private Token getToken() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            Token current = tokens.get(pos);
            pos++;
            return current;
        }
    }

    private void printNode(String s) {
        for(int i=0;i<profondeur;i++) {
            System.out.print("    ");
        }
        System.out.println(s);
    }
}
