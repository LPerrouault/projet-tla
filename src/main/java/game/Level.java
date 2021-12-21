package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
description d'un niveau
*/
interface Level {

    /*
    placement initial des murs et des sorties
    */
    char[] getWalls(String fileName) throws FileNotFoundException;

    /*
    instructions de déplacement des fantômes
    */
    default ArrayList<Ghost> getGhostsLevel1() {
        return new ArrayList<Ghost>();
    }
    default ArrayList<Ghost> getGhostsLevel2() {
        return new ArrayList<Ghost>();
    }


    /*
    fonction permettant de modifier la visibilité des murs, après chaque déplacement du joueur
    par défaut : aucun changement de visibilité des murs
    */
    default void adjustWalls(Game game) {};
}
