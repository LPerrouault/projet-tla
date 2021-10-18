package game;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


/*
gère le comportement d'un fantôme, à l'aide d'une séquence d'instruction
*/
public class Ghost {

    // coordonnées et orientation initiales
    private int init_x;
    private int init_y;
    private int init_orientation;

    // coordonnées et orientation courante
    private int x;
    private int y;
    private int orientation;

    // index dans la séquence
    private int index;

    // séquence d'instruction
    private GhostAction[] sequence;

    // élément graphique du fantôme
    private ImageView imageView;

    public Ghost(int x, int y, int orientation, GhostAction[] sequence) {

        this.init_x = x;
        this.init_y = y;
        this.init_orientation = orientation;

        this.x = x;
        this.y = y;
        this.orientation = orientation;

        this.sequence = sequence;

        index = 0;

        imageView = new ImageView(SpritesLibrary.imgGhost);
        imageView.setViewOrder(10);
        translate(x, y);

    }

    /*
    réalise des actions dans la séquence jusqu'à réaliser un déplacement
    */
    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStep();
            index = (index + 1) % sequence.length;
        } while (!has_moved);
    }

    /*
    réalise une action dans la séquence
    retourne vrai si cette action est un déplacement du fantôme
    */
    private boolean nextStep() {

        boolean has_moved = false;

        switch (sequence[index]) {
            case FORWARD:
                switch (orientation) {
                    case 0:
                        if (y > 0) y = y - 1;
                        break;
                    case 1:
                        if (x < Game.BOARD_WIDTH - 1) x = x + 1;
                        break;
                    case 2:
                        if (y < Game.BOARD_HEIGHT - 1) y = y + 1;
                        break;
                    case 3:
                        if (x > 0) x = x - 1;
                        break;
                }
                has_moved = true;
                smoothTranslate(x, y);
                break;
            case TURN_LEFT:
                orientation = orientation - 1;
                if (orientation<0) orientation = 3;
                break;
            case TURN_RIGHT:
                orientation = (orientation + 1) % 4;
                break;
            case REINIT:
                x = init_x;
                y = init_y;
                orientation = init_orientation;
                has_moved = true;
                translate(x, y);
                break;
        }

        return has_moved;
    }

    /*
    replace l'élément graphique du fantôme sans transition
    */
    private void translate(int x, int y) {
        imageView.setTranslateX(x * Game.TILE_SIZE + 3);
        imageView.setTranslateY(y * Game.TILE_SIZE + 3);
    }

    /*
    replace l'élément graphique du fantôme avec transition
    */
    private void smoothTranslate(int x, int y) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imageView);
        transition.setToX(x * Game.TILE_SIZE + 3);
        transition.setToY(y * Game.TILE_SIZE + 3);
        transition.setDuration(Duration.millis(80));
        transition.play();
    }

    int getX() {
        return x;
    }

    int getY() {
      return y;
    }

    javafx.scene.Node getNode() {
        return imageView;
    }

}
