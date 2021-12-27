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

    private void S() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.playerStart ||
                getTokenClass() == TokenClass.dioStart || getTokenClass() == TokenClass.dioActions || getTokenClass() == TokenClass.playerWait
                || getTokenClass() == TokenClass.setWalls) {

            // production S -> AS'

            profondeur++;
            A();
            profondeur--;
            S_prime();
            return;
        }
        throw new UnexpectedTokenException("Fonction attendue");
    }

    private void S_prime() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.playerStart ||
                getTokenClass() == TokenClass.dioStart || getTokenClass() == TokenClass.dioActions || getTokenClass() == TokenClass.playerWait
                || getTokenClass() == TokenClass.setWalls) {

            // production S' -> S

            profondeur++;
            S();
            profondeur--;
            return;
        }

        if (isEOF()) {

            // production S' -> epsilon

            return;
        }

        throw new UnexpectedTokenException("Fonction attendue");
    }

    private void A() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.playerStart ||
                getTokenClass() == TokenClass.dioStart || getTokenClass() == TokenClass.playerWait) {

            // production A -> fonction(E

            getToken();
            profondeur++;
            E();
            profondeur--;

            return;
        }

        if (getTokenClass() == TokenClass.dioActions) {

            // production A -> dioActions(B)

            getToken();
            profondeur++;
            B();
            profondeur--;

            return;
        }

        if (getTokenClass() == TokenClass.setWalls) {

            // production A -> setWall(F)

            getToken();
            profondeur++;
            F();
            profondeur--;

            return;
        }

        throw new UnexpectedTokenException("attendu");
    }

    private void B() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.dioMove ||
                getTokenClass() == TokenClass.dioPrepare || getTokenClass() == TokenClass.dioAttaque) {

            // production B -> CB'

            profondeur++;
            C();
            profondeur--;

            return;
        }

        throw new UnexpectedTokenException("intVal ou ( attendu");
    }
    private void B_prime() throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.dioMove ||
                getTokenClass() == TokenClass.dioPrepare || getTokenClass() == TokenClass.dioAttaque) {

            // production B' -> B

            profondeur++;
            B();
            profondeur--;
            return;
        }

        if (isEOF()) {

            // production B' -> epsilon

            return;
        }

        throw new UnexpectedTokenException("Fonction attendue");

    }
    private void C() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.dioMove) {

            // production C -> dioMove(E)

            getToken();
            profondeur++;
            E();
            profondeur--;
        }

        if (getTokenClass() == TokenClass.dioPrepare || getTokenClass() == TokenClass.dioAttaque) {

            // production C -> fonction(D

            getToken();
            profondeur++;
            D();
            profondeur--;
            return;
        }

        throw new UnexpectedTokenException("Fonction attendue");
    }
    private void D() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.couteau || getTokenClass() == TokenClass.rouleau) {

            // production D -> couteau ou D -> rouleau

            getToken();

            return;

        }

        throw new UnexpectedTokenException("couteau ou rouleau attendu");
    }
    private void E() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.intVal) {

            // production E -> intVal

            Token tokIntVal = getToken();
            printNode(tokIntVal.getValue()); // affiche la valeur int

            return;
        }

        throw new UnexpectedTokenException("intVal attendu");
    }

    private void F() throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.string) {

            // production F -> string
            Token tokString = getToken();
            printNode(tokString.toString());
            return;
        }

        throw new UnexpectedTokenException("string attendu");
    }
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
