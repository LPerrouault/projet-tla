package game;

import game.action.EdioAction;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

    // stockage du parcour de la sequence
    public ArrayList<Integer> tabSequence;

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
        setX(19);
        this.orientation = orientation;
        this.action = action;
        tabSequence = new ArrayList<Integer>();

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
                setAddListSequence(y);
                has_moved = true;
                smoothTranslate(x, y);
                break;

            case DOWN:
              if (y < Game.BOARD_HEIGHT - 1) y = y + 1;
                orientation = orientation - 1;
                setAddListSequence(y);
                has_moved = true;
                smoothTranslate(x, y);
                break;

            case STOP:
                has_moved = false;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    javafx.scene.Node getNode() {
        return imageEdio;
    }

    public void setAddListSequence(Integer integer) {
        tabSequence.add(integer);
    }

    public ArrayList<Integer> getTabSequence() {
        return tabSequence;
    }

    @Override
    public String toString() {
        return "Edio{" +
                "x=" + x +
                ", y=" + y +
                ", orientation=" + orientation +
                ", tabSequence=" + tabSequence +
                ", sequence=" + Arrays.toString(sequence) +
                ", imageEdio=" + imageEdio +
                ", imageCouteau=" + imageCouteau +
                ", imageRouleau=" + imageRouleau +
                '}';
    }
}
