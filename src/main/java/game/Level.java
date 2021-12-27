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
    default ArrayList<Obstacle> getObstaclesLevel1() {
        return new ArrayList<Obstacle>();
    }
    default ArrayList<Obstacle> getObstaclesLevel2() {
        return new ArrayList<Obstacle>();
    }


    /*
    fonction permettant de modifier la visibilité des murs, après chaque déplacement du joueur
    par défaut : aucun changement de visibilité des murs
    */
    default void adjustWalls(Game game) {};
}
