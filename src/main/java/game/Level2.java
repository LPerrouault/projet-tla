package game;

import java.util.ArrayList;

public class Level2 implements Level {
    public char[] getWalls() {
        String s =
            "     #       #      " +
            "#### # ##### # #### " +
            "     #   # # #    # " +
            " ####### # # ## # # " +
            "       # # #    # # " +
            " ##### # # ###### # " +
            " #       #        # " +
            " # ################ " +
            " # #    *  # # #    " +
            " # # ##### # # # ###" +
            " # #     # # # #    " +
            "   ##### # # # #### " +
            "## #     #   #      " +
            "   #################";
        return s.toCharArray();
    }

    public ArrayList<Ghost> getGhosts() {
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
