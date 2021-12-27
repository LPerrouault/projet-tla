package game;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;

/*
gère le comportement d'un obstacle, à l'aide d'une séquence d'instruction
 */
public class Obstacle {

    // coordonnées et orientation courante
    private int x;
    private int y;


    //Type d'objet ennemi et image lui correspondant
    private int typeEnnemi; //0 = couteau; 1 = rouleau1; 2 = rouleau2
    private ImageView imageView;

    //Liste des images
    private final ImageView imageCouteau = new ImageView(SpritesLibrary.imgCouteau);
    private final ImageView imageRouleau1 = new ImageView(SpritesLibrary.imgRouleau1);
    private final ImageView imageRouleau2 = new ImageView(SpritesLibrary.imgRouleau2);
    private ArrayList<ImageView> imageList = new ArrayList<ImageView>();

    public Obstacle(int y, int typeE) {
        this.typeEnnemi = typeE;
        this.x = 18;
        if (typeEnnemi == 1) {  //Si l'objet est la première partie du rouleau
            this.x -= 1;        //Il débute une case à gauche
        }
        this.y = y;

        //On ajoute les images à la liste
        imageList.add(imageCouteau);    //typeE = 0
        imageList.add(imageRouleau1);   //typeE = 1
        imageList.add(imageRouleau2);   //typeE = 2
        this.imageView = imageList.get(typeE);  //L'image change en fonction du type

        //Les obstacles sont visibles par dessus toutes les autres images
        imageView.setViewOrder(0);
        translate(x, y);

    }

    /*
    réalise des actions dans la séquence jusqu'à réaliser un déplacement
     */
    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStep();
        } while (!has_moved);
    }

    /*
    réalise une action dans la séquence
    retourne vrai si cette action est un déplacement du fantôme
     */
    private boolean nextStep() {

        boolean has_moved = false;
        if (x > -1) {   //L'obstacle peut se déplacer jusqu'à la case -1
            x = x - 1;  //L'obstacle se déplace d'une case à gauche
        }
        has_moved = true;
        smoothTranslate(x, y);

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
