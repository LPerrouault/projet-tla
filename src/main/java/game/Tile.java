package game;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
Gère l'affichage d'un carreau, pouvant être :
- vide
- un mur
- une sortie
- la zone de l'ennemi
*/
public class Tile {
    private TileState state;
    private ImageView imageView = new ImageView();

    public Tile(int x, int y, Pane parent) {
        setState(TileState.EMPTY);
        imageView.setTranslateX(x * Game.TILE_SIZE);
        imageView.setTranslateY(y * Game.TILE_SIZE);
        Tooltip.install(imageView, new Tooltip("(" + x + ", " + y + ")"));
        parent.getChildren().add(imageView);
    }

    void setState(TileState state) {
        this.state = state;
        switch (state) {
            case EMPTY:
                imageView.setImage(SpritesLibrary.imgTile);
                imageView.setViewOrder(20);
                break;
            case WALL:
                imageView.setImage(SpritesLibrary.imgMur);
                imageView.setViewOrder(2);
                break;
            case EXIT:
                imageView.setImage(SpritesLibrary.imgVictoire);
                imageView.setViewOrder(20);
                break;
            case DIO:
                imageView.setImage(SpritesLibrary.imgEnnemi);
                imageView.setViewOrder(20);
        }
    }

    TileState getState() {
        return state;
    }

}
