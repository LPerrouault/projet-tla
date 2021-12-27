package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGame implements Level {

    public char[] getWalls(String fileName) throws FileNotFoundException {
        String line = null;
        try ( var scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                if (line == null) {
                    line = scanner.nextLine();
                }
                line += scanner.nextLine();
            }
        }
        return line.toCharArray();
    }

    public ArrayList<Obstacle> getObstaclesLevel1() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(
                new Obstacle(
                        6,
                        0
                )
        );

        obstacles.add(
                new Obstacle(
                        1,
                        0
                )
        );

        obstacles.add(
                new Obstacle(
                        5,
                        2
                )
        );

        obstacles.add(
                new Obstacle(
                        5,
                        1
                )
        );
        return obstacles;
    }

    public ArrayList<Obstacle> getObstaclesLevel2() {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(
                new Obstacle(
                        6,
                        1
                )
        );

        obstacles.add(
                new Obstacle(
                        1,
                        1
                )
        );

        obstacles.add(
                new Obstacle(
                        19,
                        2
                )
        );
        return obstacles;
    }

    public Edio getEdioLevel1() {
        ArrayList<Integer> sequenceMouvements = new ArrayList<Integer>();
        sequenceMouvements.add(4);
        sequenceMouvements.add(6);
        sequenceMouvements.add(2);
        sequenceMouvements.add(8);
        Edio edio = new Edio(12,
                new EdioAction[]{
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU
                },
                sequenceMouvements);
        return edio;
    }

    public Edio getEdioLevel2() {
        ArrayList<Integer> sequenceMouvements = new ArrayList<Integer>();
        sequenceMouvements.add(4);
        sequenceMouvements.add(2);
        sequenceMouvements.add(6);
        sequenceMouvements.add(8);
        Edio edio = new Edio(11,
                new EdioAction[]{
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU
                },
                sequenceMouvements);
        return edio;
    }

    /*public void adjustWalls(Game game) {
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
    }*/
}
