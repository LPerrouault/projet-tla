package game;

import game.action.EnnemiAction;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class Ennemi {

    // coordonnées courante
    private int x;
    private int y;

    // index dans la séquence
    private int index;

    // séquence d'instruction
    private EnnemiAction[] sequence;
    private ArrayList<Integer> edioSequence;
    private Edio edio;

    // élément graphique
    private ImageView imageRouleau1;
    private ImageView imageRouleau2;

    private ImageView imageCouteau;


    public Ennemi( int y,Edio edio, EnnemiAction[] sequence) {
        this.x = 19;
        this.y = y;
        setY(y);
        this.sequence = sequence;
        this.edio = edio;

        imageRouleau1 = new ImageView(SpritesLibrary.imgRouleau1);
        imageRouleau2 = new ImageView(SpritesLibrary.imgRouleau2);
        imageCouteau = new ImageView(SpritesLibrary.imgCouteau);

        translate(this.x,getY(), sequence);

    }

    private void translate(int x, int y, EnnemiAction[] sequence) {
        for (int i =0; i<sequence.length; i++) {
            if (!equals(EnnemiAction.FORWARD_COUTEAU)) {
                imageCouteau.setTranslateX(x * Game.TILE_SIZE + 3);
                imageCouteau.setTranslateY(y * Game.TILE_SIZE + 3);
            } else if (!equals(EnnemiAction.FORWARD_ROULEAU)){
                imageRouleau1.setTranslateX(x * Game.TILE_SIZE + 3);
                imageRouleau1.setTranslateY(y * Game.TILE_SIZE + 3);
//                imageRouleau2.setTranslateX(x * Game.TILE_SIZE + 3);
//                imageRouleau2.setTranslateY(y * Game.TILE_SIZE + 3);
            }
        }
    }

    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStepMouvement();
            index = (index + 1) % sequence.length;
        } while (!has_moved);
    }


    private boolean nextStepMouvement() {

        boolean has_moved = false;

        switch (sequence[index]) {
            case FORWARD_COUTEAU:
                x = x-1;
                has_moved = true;
                smoothTranslateCouteau(x, y);
                break;

            case FORWARD_ROULEAU:
                x = x-1;
                has_moved = true;
                smoothTranslateRouleau(x, y);
                break;
        }
        return has_moved;
    }

    private void smoothTranslateCouteau(int x, int y) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imageCouteau);
        transition.setToX(x * Game.TILE_SIZE + 3);
        transition.setToY(y * Game.TILE_SIZE + 3);
        transition.setDuration(Duration.millis(80));
        transition.play();
    }

    private void smoothTranslateRouleau(int x, int y) {
        TranslateTransition transitionRouleau1 = new TranslateTransition();
       // TranslateTransition transitionRouleau2 = new TranslateTransition();

        transitionRouleau1.setNode(imageRouleau1);
      //  transitionRouleau2.setNode(imageRouleau2);
     //   transitionRouleau2.setToX(x * Game.TILE_SIZE + 3);
       // transitionRouleau2.setToY(y * Game.TILE_SIZE + 3);
        transitionRouleau1.setToX(x * Game.TILE_SIZE + 3);
        transitionRouleau1.setToY(y * Game.TILE_SIZE + 3);
        //transitionRouleau2.setDuration(Duration.millis(80));
        //transitionRouleau1.setDuration(Duration.millis(80));
        //transitionRouleau2.play();
        transitionRouleau1.play();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    javafx.scene.Node getNodeCouteau() {
        return imageCouteau;
    }
    javafx.scene.Node getNodeRouleau1() {
        return imageRouleau1;
    }
    javafx.scene.Node getNodeRouleau2() {
        return imageRouleau2;
    }

    @Override
    public String toString() {
        return "Ennemi{" +
                "x=" + x +
                ", y=" + y +
                ", index=" + index +
                ", sequence=" + Arrays.toString(sequence) +
                ", imageRouleau1=" + imageRouleau1 +
                ", imageRouleau2=" + imageRouleau2 +
                ", imageCouteau=" + imageCouteau +
                '}';
    }
}
