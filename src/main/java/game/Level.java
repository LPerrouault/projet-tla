package game;

import java.util.ArrayList;

/*
description d'un niveau
*/
interface Level {

    /*
    placement initial des murs et des sorties
    */
    char[] getWalls();

    /*
    instructions de déplacement des fantômes
    */
    default ArrayList<Ghost> getGhosts() {
        return new ArrayList<Ghost>();
    }

    /*
    fonction permettant de modifier la visibilité des murs, après chaque déplacement du joueur
    par défaut : aucun changement de visibilité des murs
    */
    default void adjustWalls(Game game) {};
}
