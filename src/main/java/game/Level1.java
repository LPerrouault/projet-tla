package game;

import java.util.ArrayList;

public class Level1 implements Level {
    public char[] getWalls() {
        String s =
            "     #       #      " +
            "#### # ##### # #### " +
            "     #   # # #    # " +
            " ####### # # ## # # " +
            "       # # #    # # " +
            " ##### # # ###### # " +
            " #       #        # " +
            " # ######### ###### " +
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

}
