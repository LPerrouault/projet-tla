package game;

import game.action.EdioAction;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Edio {
    // coordonnées et orientation initiales
    private int init_x;
    private int init_y;
    private int init_orientation;

    // coordonnées et orientation courante
    private int x = 19;
    private int y;
    private int orientation;
    private int action;

    // index dans la séquence de mouvement
    private int indexMouvement;

    // index dans la séquence d'action
    private int indexAction;


    // séquence d'instruction
    private EdioAction[] sequence;

    // élément graphique de Edio
    private ImageView imageEdio;
    private ImageView imageCouteau;
    private ImageView imageRouleau;


    public Edio(int y, int orientation,EdioAction[] sequence) {
        this.init_x = 19;
        this.init_y = y;
        this.init_orientation = orientation;

        this.y = y;
        this.orientation = orientation;
        this.action = action;

        this.sequence = sequence;
        indexMouvement = 0;
        indexAction = 0;

        imageEdio = new ImageView(SpritesLibrary.imgEdioSmall);
        imageCouteau = new ImageView(SpritesLibrary.imgEdioCouteau);
        imageRouleau = new ImageView(SpritesLibrary.imgEdioRoadroller);
        imageEdio.setViewOrder(20);
        imageCouteau.setViewOrder(20);
        imageRouleau.setViewOrder(20);

        translate(x, y);
    }
    private void translate(int x, int y) {
        imageEdio.setTranslateX(x * Game.TILE_SIZE + 3);
        imageEdio.setTranslateY(y * Game.TILE_SIZE + 3);
    }

    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStepMouvement();
            indexMouvement = (indexMouvement + 1) % sequence.length;
        } while (!has_moved);
    }

    private boolean nextStepMouvement() {

        boolean has_moved = false;

        switch (sequence[indexMouvement]) {
            case TOP:
                if (y > 0) y = y - 1;
                orientation = 1;
                has_moved = true;
                smoothTranslate(x, y);
                break;

            case DOWN:
              if (y < Game.BOARD_HEIGHT - 1) y = y + 1;
                orientation = orientation - 1;
                has_moved = true;
                smoothTranslate(x, y);
                break;
        }
        return has_moved;
    }



    private void smoothTranslate(int x, int y) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imageEdio);
        transition.setToX(getX() * Game.TILE_SIZE + 3);
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
        return imageEdio;
    }

}
