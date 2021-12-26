package game;

import game.action.EdioAction;
import game.action.EnnemiAction;
import game.action.GhostAction;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGame implements Level {


    public char[] getWalls(String fileName ) throws FileNotFoundException {
        String line = null;
        try (var scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                if (line ==null)
                    line = scanner.nextLine();
                line += scanner.nextLine();
            }
        }
        return line.toCharArray();
    }

    // mouvemant  de edio
    public ArrayList<Edio> edioRandomMove(){
        ArrayList<Edio> edios = new ArrayList<>();
        int randNb = 1;
        ArrayList<EdioAction> edioAction = new ArrayList<>();

        for (int i=0; i<=3; i++){
            randNb = (int) (Math.random()*2)+1;
            System.out.println(randNb);
            if (randNb == 2)
            edioAction.add(EdioAction.TOP);
            else
                edioAction.add(EdioAction.DOWN);
        }

        EdioAction[] action = new EdioAction[edioAction.size()];
        for (int i=0; i<edioAction.size(); i++){
            action[i] = edioAction.get(i);
        }
        edios.add( new Edio(5,1, action));
        return edios;
    }

    public ArrayList<Edio> getEdioLevel1() {
        ArrayList<Edio> edios = new ArrayList<>();
        Edio edio = new Edio(
                5,
                1,
                new EdioAction[]{
                        EdioAction.DOWN,
                        EdioAction.DOWN,
                        EdioAction.TOP,
                        EdioAction.TOP,
                        EdioAction.TOP,
                        EdioAction.TOP,
                        EdioAction.DOWN,
                        EdioAction.DOWN,
                        EdioAction.DOWN
                }
        );
        edios.add(edio);
        return edios;
    }

    public ArrayList<Ennemi> getEnnemieLevel1(Edio edio){
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        ArrayList<Integer> eduiSequence = edio.getTabSequence();
        ennemis.add(new Ennemi(7,getEdioLevel1().get(0) ,new EnnemiAction[]{
                EnnemiAction.FORWARD_COUTEAU,
        }));
        ennemis.add(new Ennemi(3,getEdioLevel1().get(0)  ,new EnnemiAction[]{
                EnnemiAction.FORWARD_COUTEAU
        }));
        ennemis.add(new Ennemi(6, getEdioLevel1().get(0) ,new EnnemiAction[]{
                EnnemiAction.FORWARD_COUTEAU
        }));
        ennemis.add(new Ennemi(8,getEdioLevel1().get(0)  ,new EnnemiAction[]{
                EnnemiAction.FORWARD_COUTEAU
        }));
        ennemis.add(new Ennemi(4,getEdioLevel1().get(0)  ,new EnnemiAction[]{
                EnnemiAction.FORWARD_COUTEAU
        }));
        return ennemis;
    }

    public ArrayList<Ghost> getGhostsLevel2() {
        ArrayList<Ghost> ghosts = new ArrayList<>();

        return ghosts;
    }

    public void adjustWalls(Game game) {
        game.getTile(2, 9).setState(
                game.isVisited(2, 10) > 0 ?
                        TileState.WALL :
                        TileState.EMPTY
        );
        game.getTile(12, 7).setState(
                (game.isVisited(2, 10) == 1 && game.isVisited(12, 4) != 1) ?
                        TileState.EMPTY :
                        TileState.WALL
        );
    }
}
