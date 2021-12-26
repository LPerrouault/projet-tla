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

    // coordonnées  initiales
    private int init_x;
    private int init_y;

    // index dans la séquence
    private int index;

    // séquence d'instruction
    private EnnemiAction[] sequence;
    private ArrayList<Integer> edioSequence;
    private Edio edio;

    // élément graphique
    private ImageView imageCouteau1;
    private ImageView imageCouteau2;
    private ImageView imageCouteau3;
    private  ImageView imageRouleau1;
    private  ImageView imageRouleau2;


    public Ennemi( int y,Edio edio, EnnemiAction[] sequence) {
        this.init_x = 18;
        this.init_y = y;

        this.x = 18;
        this.y = y;
        setY(y);
        this.sequence = sequence;
        this.edio = edio;

        imageCouteau1 = new ImageView(SpritesLibrary.imgCouteau);
        imageCouteau2 = new ImageView(SpritesLibrary.imgCouteau);
        imageCouteau3 = new ImageView(SpritesLibrary.imgCouteau);
        imageRouleau1 = new ImageView(SpritesLibrary.imgRouleau1);
        imageRouleau2 = new ImageView(SpritesLibrary.imgRouleau2);


        translate(this.x,getY(), sequence);

    }

    private void translate(int x, int y, EnnemiAction[] sequence) {
        for (int i =0; i<sequence.length; i++) {
            System.out.println(EnnemiAction.FORWARD_COUTEAU);
            if (sequence[i] == EnnemiAction.FORWARD_COUTEAU) {
                imageCouteau1.setTranslateX(x * Game.TILE_SIZE + 3);
                imageCouteau1.setTranslateY((y-1 )* Game.TILE_SIZE + 3);

                imageCouteau1.setTranslateX(x * Game.TILE_SIZE + 3);
                imageCouteau1.setTranslateY(y * Game.TILE_SIZE + 3);

                imageCouteau1.setTranslateX(x * Game.TILE_SIZE + 3);
                imageCouteau1.setTranslateY((y+1) * Game.TILE_SIZE + 3);

            } else if (sequence[i] == EnnemiAction.FORWARD_ROULEAU){
                System.out.println(x);
                System.out.println(y);
                imageRouleau1.setTranslateX((x+1) * Game.TILE_SIZE + 3);
                imageRouleau1.setTranslateY((y+1) * Game.TILE_SIZE + 3);
                imageRouleau2.setTranslateX(x * Game.TILE_SIZE + 3);
                imageRouleau2.setTranslateY(y * Game.TILE_SIZE + 3);
            }
        }
    }

    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStep();
            index = (index + 1) % sequence.length;
        } while (!has_moved);
    }


    private boolean nextStep() {

        boolean has_moved = false;

        switch (sequence[index]) {
            case FORWARD_COUTEAU:
                if (x>0)
                x = x-1;
                else {
                    x = init_x;
                    y = init_y;
                }

                has_moved = true;
                smoothTranslateCouteau(x, y);
                break;

            case FORWARD_ROULEAU:
                if (x>0)
                x = x-1;
                else {
                    x = init_x;
                    y = init_y;
                }
                has_moved = true;
                smoothTranslateRouleau(x, y);
                break;

        }
        return has_moved;
    }

    private void smoothTranslateCouteau(int x, int y) {
        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();
        TranslateTransition transition3 = new TranslateTransition();

        transition1.setNode(imageCouteau1);
        transition2.setNode(imageCouteau2);
        transition3.setNode(imageCouteau3);

        //couteau haut
        transition1.setToX(x * Game.TILE_SIZE + 3);
        transition2.setToY((y-3) * Game.TILE_SIZE + 3);

        //couteau milieu
        transition2.setToX(x * Game.TILE_SIZE + 3);
        transition2.setToY(y * Game.TILE_SIZE + 3);

        //couteau bas
        transition3.setToX(x * Game.TILE_SIZE + 3);
        transition3.setToY((y+2) * Game.TILE_SIZE + 3);

        //Durée de la transition
        transition1.setDuration(Duration.millis(50));
        transition2.setDuration(Duration.millis(50));
        transition3.setDuration(Duration.millis(50));

        transition1.play();
        transition2.play();
        transition3.play();
    }

    private void smoothTranslateRouleau(int x, int y) {
        TranslateTransition transitionRouleau1 = new TranslateTransition();
        TranslateTransition transitionRouleau2 = new TranslateTransition();

        transitionRouleau1.setNode(imageRouleau1);
        transitionRouleau2.setNode(imageRouleau2);
        transitionRouleau1.setToX(x * Game.TILE_SIZE + 3);
        transitionRouleau1.setToY(y * Game.TILE_SIZE + 3);
        transitionRouleau2.setToX((x+1) * Game.TILE_SIZE + 3);
        transitionRouleau2.setToY(y * Game.TILE_SIZE + 3);
        transitionRouleau2.setDuration(Duration.millis(80));
        transitionRouleau1.setDuration(Duration.millis(80));
        transitionRouleau2.play();
        transitionRouleau1.play();
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getInit_x() {
        return init_x;
    }

    int getInit_y() {
        return init_y;
    }


    public void setY(int y) {
        this.y = y;
    }


    javafx.scene.Node getNodeCouteau1() {
        return imageCouteau1;
    }
    javafx.scene.Node getNodeCouteau2() {
        return imageCouteau2;
    }
    javafx.scene.Node getNodeCouteau3() {
        return imageCouteau3;
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

                '}';
    }
}
