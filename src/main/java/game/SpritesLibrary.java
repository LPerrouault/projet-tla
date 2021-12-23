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

    static final Image imgJajaLarge = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/jaja.png"), 64, 64, true, false);
    static final Image imgEdioLarge = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/jaja.png"), 64, 64, true, false);
    static final Image imgJajaSmall = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/jaja.png"), Game.TILE_SIZE + 6, Game.TILE_SIZE + 6, true, false);
    static final Image imgEdioSmall = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/jaja.png"), Game.TILE_SIZE + 6, Game.TILE_SIZE + 6, true, false);
    static final Image imgGhost = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgCouteau = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgCouteauHaut = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau_haut.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgCouteauBas = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau_bas.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgRouleau1 = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau_bas.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgRoulau2 = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/couteau_bas.png"), Game.TILE_SIZE - 6, Game.TILE_SIZE - 6, true, false);
    static final Image imgMur = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/mur.png"), Game.TILE_SIZE, Game.TILE_SIZE, true, false);
    static final Image imgVictoire = new Image(SpritesLibrary.class.getResourceAsStream("/sprites/victoire.png"), Game.TILE_SIZE, Game.TILE_SIZE, true, false);
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
