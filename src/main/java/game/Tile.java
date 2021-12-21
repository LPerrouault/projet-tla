package game;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
Gère l'affichage d'un carreau, pouvant être :
- vide
- un mur
- une sortie
*/
public class Tile {
    private TileState state;
    private ImageView imageView = new ImageView();

    public Tile(int x, int y, Pane parent) {
        setState(TileState.E);
        imageView.setTranslateX(x * Game.TILE_SIZE);
        imageView.setTranslateY(y * Game.TILE_SIZE);
        Tooltip.install(imageView, new Tooltip("(" + x + ", " + y + ")"));
        parent.getChildren().add(imageView);
    }

    void setState(TileState state) {
        this.state = state;
        switch (state) {
            case E:
                imageView.setImage(SpritesLibrary.imgTile);
                imageView.setViewOrder(20);
                break;
            case W:
                imageView.setImage(SpritesLibrary.imgWall);
                imageView.setViewOrder(0);
                break;
            case F:
                imageView.setImage(SpritesLibrary.imgExit);
                imageView.setViewOrder(20);
                break;
        }
    }

    TileState getState() {
        return state;
    }

}
