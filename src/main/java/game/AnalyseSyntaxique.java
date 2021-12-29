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
    public LevelGame analyse(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        pos = 0;
        LevelGame level = new LevelGame();
        level = S(level);
        if (pos != tokens.size()) {
            System.out.println("L'analyse syntaxique s'est terminé avant l'examen de tous les tokens");
            throw new IncompleteParsingException();
        }
        return level;
    }

    /*

    méthodes des symboles non terminaux

      */

    private LevelGame S(LevelGame level) throws UnexpectedTokenException {

        if (getTokenClass() == TokenClass.dioActions || getTokenClass() == TokenClass.setWalls) {
            // production S -> AS'
            profondeur++;
            level = A(level);
            profondeur--;
            level = S_prime(level);
            return level;
        }
        throw new UnexpectedTokenException("Fonction attendue");
    }

    private LevelGame S_prime(LevelGame level) throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.dioActions || getTokenClass() == TokenClass.setWalls){
            // production S' -> S
            level = S(level);
            profondeur--;
            return level;
        } else if (isEOF()) {
            // production S' -> epsilon
            return level;
        }
        throw new UnexpectedTokenException("dioActions ou setWalls attendu");
    }

    private LevelGame A(LevelGame level) throws UnexpectedTokenException {
        //Contient la liste des actions de Edio, création d'un objet de cette classe
       if (getTokenClass() == TokenClass.dioActions) {
            // production A -> dioActions(B G
            getToken();
            printNode("dioActions(");
            Edio edio = new Edio();
            edio = B(edio);
            profondeur--;
            G();
            level.setEdio(edio);
            return level;
        } else if (getTokenClass() == TokenClass.setWalls){
            // production A -> setWalls(F G
            getToken();
            printNode("setWalls(");
            profondeur++;
            String walls = F();
            profondeur--;
            G();
            level.setWalls(walls.toCharArray());
            return level;
        }
        throw new UnexpectedTokenException("dioActions ou setWalls attendu");
    }

    private Edio B(Edio edio) throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.dioMove || getTokenClass() == TokenClass.dioPrepare || getTokenClass() == TokenClass.dioAttaque) {
            // production B -> CB'
            profondeur++;
            edio = C(edio);
            profondeur--;
            edio = B_prime(edio);
            return edio;
        }

        throw new UnexpectedTokenException("dioMove, dioPrepare ou dioAttaque attendu");
    }
    private Edio B_prime(Edio edio) throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.dioMove ||
                getTokenClass() == TokenClass.dioPrepare || getTokenClass() == TokenClass.dioAttaque) {
            // production B' -> B
            edio = B(edio);
            profondeur--;
            return edio;
        } else if(getTokenClass() == TokenClass.rightPar){
            // production B' -> epsilon
            return edio;
        }
        throw new UnexpectedTokenException("dioMove, dioPrepare, dioAttaque ou ) attendu");
    }
    
    private Edio C(Edio edio) throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.dioMove) {
            // production C -> dioMove(E G
            Token token = getToken();
            printNode("dioMove(");
            profondeur++;
            int caseMove = E();
            profondeur--;
            G();
            edio.getSequenceActions().add(EdioAction.MOVE);
            edio.getSequenceMouvements().add(caseMove);
            return edio;
        } else if (getTokenClass() == TokenClass.dioPrepare) {
            // production C -> dioPrepare(D G
            Token token = getToken();
            printNode("dioPrepare(");
            profondeur++;
            TokenClass typeAttaque = D();
            profondeur--;
            if (typeAttaque == TokenClass.couteau) {
                edio.getSequenceActions().add(EdioAction.PREPARE_COUTEAU);
            } else if (typeAttaque == TokenClass.rouleau) {
                edio.getSequenceActions().add(EdioAction.PREPARE_ROULEAU);
            }
            G();
            return edio;
        } else if (getTokenClass() == TokenClass.dioAttaque) {
            // production C -> dioAttaque(D G
            Token token = getToken();
            printNode("dioAttaque(");
            profondeur++;
            TokenClass typeAttaque = D();
            profondeur--;
            G();
            if (typeAttaque == TokenClass.couteau) {
                edio.getSequenceActions().add(EdioAction.ATTACK_COUTEAU);
            } else if (typeAttaque == TokenClass.rouleau) {
                edio.getSequenceActions().add(EdioAction.ATTACK_ROULEAU);
            }
            return edio;
        }
        throw new UnexpectedTokenException("dioMove, dioPrepare, dioAttaque attendu");
    }
    private TokenClass D() throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.couteau || getTokenClass() == TokenClass.rouleau) {
            // production D -> couteau ou D -> rouleau
            Token token = getToken();
            printNode(token.toString());
            return token.getCl();
        }
        throw new UnexpectedTokenException("couteau ou rouleau attendu");
    }
    
    private int E() throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.intVal) {
            // production E -> intVal
            Token token = getToken();
            printNode(token.toString());
            return Integer.parseInt(token.getValue());
        }
        throw new UnexpectedTokenException("intVal attendu");
    }

    private String F() throws UnexpectedTokenException {
        if (getTokenClass() == TokenClass.stringVal) {
            // production F -> string
            Token tokString = getToken();
            printNode(tokString.toString());
            return tokString.getValue();
        }
        throw new UnexpectedTokenException("stringVal attendu");
    }

    /**
     * Vérifie si le Token est rightPar (parenthèse gauche)
     * @throws UnexpectedTokenException Le Token n'est pas rightPar
     */
    private void G() throws UnexpectedTokenException {
        if(getTokenClass() == TokenClass.rightPar){
            getToken();
            printNode(")");
            return;
        }
        throw new UnexpectedTokenException("rightPar attendu");
    }
    
    /*

    autres méthodes

     */

    /**
     * Vérifie si la lecture a atteint la fin du fichier
     * @return true si fin du fichier
     */
    private boolean isEOF() {
        return pos >= tokens.size();
    }

    /**
     * Retourne la classe du prochain token à lire
     * SANS AVANCER au token suivant
     * @return TokenClass la classe du prochain Token
     */
    private TokenClass getTokenClass() {
        if (pos >= tokens.size()) {
            return null;
        } else {
            return tokens.get(pos).getCl();
        }
    }

    /**
     * Retourne le prochain token à lire
     * ET AVANCE au token suivant
     * @return Token le prochain Token
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

    /**
     * Affiche une chaîne en fonction de la profondeur dans l'analyse
     * @param s La chaîne à afficher
     */
    private void printNode(String s) {
        for(int i=0;i<profondeur;i++) {
            System.out.print("    ");
        }
        System.out.println(s);
    }
}
