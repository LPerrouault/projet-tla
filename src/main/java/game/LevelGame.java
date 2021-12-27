package game;

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

    public ArrayList<Obstacle> getObstaclesLevel1() {
        ArrayList<Obstacle> ghosts = new ArrayList<>();
        ghosts.add(
                new Obstacle(
                        6,
                        0
                )
        );

        ghosts.add(
                new Obstacle(
                        1,
                        0
                )
        );

        ghosts.add(
                new Obstacle(
                        19,
                        2
                )
        );

        ghosts.add(
                new Obstacle(
                        5,
                        1
                )
        );
        return ghosts;
    }

    public ArrayList<Obstacle> getObstaclesLevel2() {
        ArrayList<Obstacle> ghosts = new ArrayList<>();
        ghosts.add(
                new Obstacle(
                        6,
                        1
                )
        );

        ghosts.add(
                new Obstacle(
                        1,
                        1
                )
        );

        ghosts.add(
                new Obstacle(
                        19,
                        2
                )
        );
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
