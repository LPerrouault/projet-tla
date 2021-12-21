package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGame implements Level {


    public char[] getWalls(String file ) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("res/Level2.txt");
        Scanner scanner = new Scanner(file);
        String s = null;

        while (scanner.hasNextLine()){
            s += scanner.nextLine();
        }
        scanner.close();

        return s.toCharArray();
    }

    public ArrayList<Ghost> getGhostsLevel1() {
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(
                new Ghost(
                        6,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        1,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        19,
                        2,
                        2,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        5,
                        12,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_LEFT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );
        return ghosts;
    }

    public ArrayList<Ghost> getGhostsLevel2() {
        ArrayList<Ghost> ghosts = new ArrayList<>();
        ghosts.add(
                new Ghost(
                        6,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.TURN_RIGHT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        1,
                        4,
                        1,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
                )
        );

        ghosts.add(
                new Ghost(
                        19,
                        2,
                        2,
                        new GhostAction[]{
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.FORWARD,
                                GhostAction.REINIT
                        }
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
