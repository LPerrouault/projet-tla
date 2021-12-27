package game;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Edio {

    // coordonnées et orientation courante
    private int x = 19;
    private int y;

    //Index dans la séquence d'actions
    private int indexAction;
    //Séquence d'instruction
    private EdioAction[] sequenceActions;

    //Index dans la séquence de mouvement
    private int indexMouvement;
    //Séquence de mouvement
    private ArrayList<Integer> sequenceMouvements;

    // éléments graphiques de Edio
    private ImageView imageEdio = new ImageView(SpritesLibrary.imgEdioSmall);    //Image prenant les autres images en valeur
    private ImageView imageNone = new ImageView(SpritesLibrary.imgEdioSmall);
    private ImageView imageCouteau = new ImageView(SpritesLibrary.imgEdioCouteau);
    private ImageView imageRouleau = new ImageView(SpritesLibrary.imgEdioRoadroller);

    /**
     * Constructeur de la classe Edio
     *
     * @param y La ligne de début où se trouve Edio
     * @param sequenceActions Les actions effectuées en boucle par Edio
     * @param sequenceMouvements Lors que Edio effectue l'action MOVE, consulte
     * la case à laquelle il doit se déplacer
     */
    public Edio(int y, EdioAction[] sequenceActions, ArrayList<Integer> sequenceMouvements) {
        this.x = 19;    //edio est positionné tout à gauche de l'écran
        this.y = y;

        this.sequenceActions = sequenceActions; //La séquence des actions de edio
        this.sequenceMouvements = sequenceMouvements; //Contient les lignes auxquelles
        //edio doit se déplacer lorsqu'il y a une EdioAction MOVE
        this.indexMouvement = 0;
        this.indexAction = 0;

        this.imageEdio.setViewOrder(1); //Edio est visible par dessus les murs

        translate(this.x, this.y);
    }

    //Déplacement de Edio sans transition
    private void translate(int x, int y) {
        imageEdio.setTranslateX(x * Game.TILE_SIZE + 3);
        imageEdio.setTranslateY(y * Game.TILE_SIZE + 3);
    }
    
    void nextMove() {
        boolean has_moved = false;
        do {
            has_moved = nextStep();
            indexAction = (indexAction + 1) % sequenceActions.length;
        } while (!has_moved);
    }
    
    private boolean nextStep() {
        boolean has_moved = false;
        Integer new_y;
        switch (sequenceActions[indexAction]) {
            case MOVE:
                imageEdio.setImage(imageNone.getImage());
                this.y = sequenceMouvements.get(indexMouvement);
                indexMouvement = (indexAction + 1) % sequenceMouvements.size();
                has_moved = true;
                translate(x, y);
                break;

            case PREPARE_COUTEAU:
                imageEdio.setImage(imageCouteau.getImage());
                has_moved = true;
                break;
                
            case PREPARE_ROULEAU:
                imageEdio.setImage(imageRouleau.getImage());
                has_moved = true;
                break;
                
            case ATTACK_COUTEAU:
                imageEdio.setImage(imageNone.getImage());
                has_moved = true;
                break;
                
            case ATTACK_ROULEAU:
                imageEdio.setImage(imageNone.getImage());
                has_moved = true;
                break;
        }
        return has_moved;
    }

    //Accesseurs
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    javafx.scene.Node getNode() {
        return imageEdio;
    }
    
    @Override
    public String toString() {
        return "Edio{" +
                "x=" + x +
                ", y=" + y +
                "}";
    }
}
