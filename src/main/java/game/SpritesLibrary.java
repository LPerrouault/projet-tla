package game;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/*
prépare différents objets de type javafx.scene.image.Image
permettant d'afficher les différentes formes de carreaux et les éléments mobiles du jeu
*/

public class SpritesLibrary {

    static final Image imgPlayerLarge = new Image(SpritesLibrary.class.getResourceAsStream("/player_01.png"), 128, 128, true, false);
    static final Image imgPlayerSmall = new Image(SpritesLibrary.class.getResourceAsStream("/player_01.png"), Game.TILE_SIZE + 6, Game.TILE_SIZE + 6, true, false);
    static final Image imgGhost = new Image(SpritesLibrary.class.getResourceAsStream("/elementMetal045.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgWall = new Image(SpritesLibrary.class.getResourceAsStream("/block_05.png"), Game.TILE_SIZE, Game.TILE_SIZE, true, false);
    static final Image imgExit = new Image(SpritesLibrary.class.getResourceAsStream("/crate_30.png"), Game.TILE_SIZE, Game.TILE_SIZE, true, false);
    static final Image imgTile = generateImage(Color.gray(0.92), Color.gray(0.96));

    static Image generateImage(Color mainColor, Color borderColor) {
        WritableImage img = new WritableImage(Game.TILE_SIZE, Game.TILE_SIZE);
        PixelWriter pw = img.getPixelWriter();
        for(int x=0;x<Game.TILE_SIZE;x++) {
            for(int y=0;y<Game.TILE_SIZE;y++) {
                boolean isBorder = x<1 || x>(Game.TILE_SIZE-2) || y<1 || y>(Game.TILE_SIZE-2);
                pw.setColor(
                        x,
                        y,
                        isBorder ? borderColor : mainColor
                );
            }
        }
        return img;
    }
}
