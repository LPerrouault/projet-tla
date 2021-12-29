package game;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;

public class Lemonde {
    //Coordonnées
    private int x;
    private int y;
    
    //Array contenant les cases appelant Lemonde
    private int[][] listCases;

    // éléments graphiques de Edio
    private ImageView imageView = new ImageView(SpritesLibrary.imgLeMonde);

    /**
     * Constructeur de la classe LeMonde
     *
     * @param listCases La liste des cases
     */
    public Lemonde(int[][] listCases) {
        x = -1;
        y = -1;
        this.listCases = listCases;

        this.imageView.setViewOrder(0); //Le Monde est visible en priorité

        translate(this.x, this.y);
    }

    //Déplacement de Le Monde sans transition
    private void translate(int x, int y) {
        imageView.setTranslateX(x * Game.TILE_SIZE + 3);
        imageView.setTranslateY(y * Game.TILE_SIZE + 3);
    }
    
    //Accesseurs
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
        translate(this.x, this.y);
    }
    public void setY(int y) {
        this.y = x;
        translate(this.x, this.y);
    }
    public int[][] getCases() {
        return listCases;
    }
    
    javafx.scene.Node getNode() {
        return imageView;
    }
    
    @Override
    public String toString() {
        return "LeMonde{" +
                "x=" + x +
                ", y=" + y +
                "}";
    }
}
