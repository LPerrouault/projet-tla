package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

public class Game {

    final String message = "r : redémarrer la partie / q : quitter pour le menu";

    final static int TILE_SIZE = 32;
    final static int BOARD_WIDTH = 20;
    final static int BOARD_HEIGHT = 14;

    // grille

    protected Tile tiles[] = new Tile[BOARD_WIDTH * BOARD_HEIGHT];

    // détermine si un carreau a été parcouru par le joueur,
    // pour produire des effets "cachés" lorsque le joueur se déplace
    // par exemple changer la visibilité de certains murs

    protected int visited[] = new int[BOARD_WIDTH * BOARD_HEIGHT];

    // coordonnées du joueur

    private int player_x;
    private int player_y;
    private ImageView playerNode;

    // fantômes

    private List<Ghost> ghosts;

    // éléments de l'interface utilisateur
    private Label label;
    private Pane pane;

    // booléen jeu en cours ou terminé
    private boolean running;

    // niveau en cours
    Level level;

    Game(BorderPane borderPane) {
        pane = new Pane();
        pane.setPrefSize(
                Game.BOARD_WIDTH * Game.TILE_SIZE,
                Game.BOARD_HEIGHT * Game.TILE_SIZE
        );
        borderPane.setCenter(pane);

        label = new Label();
        borderPane.setBottom(label);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> animate()
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void setLevel(Level level) {
        this.level = level;
    }

    void start() {

        label.setText(message);

        Arrays.fill(visited, 0);

        pane.getChildren().clear();

        for(int y = 0; y < BOARD_HEIGHT; y++) {
            for(int x = 0; x < BOARD_WIDTH; x++) {
                tiles[y*BOARD_WIDTH + x] = new Tile(x, y, pane);
            }
        }

        char[] walls = level.getWalls();

        for(int i = 0; i<walls.length; i++) {
            switch (walls[i]) {
                case '#':
                    tiles[i].setState(TileState.WALL);
                    break;
                case '*':
                    tiles[i].setState(TileState.EXIT);
            }
        }

        // fantômes

        ghosts = level.getGhosts();

        // position initiale du joueur

        player_x = 0;
        player_y = 0;

        playerNode = new ImageView(SpritesLibrary.imgPlayerSmall);
        playerNode.setTranslateX(player_x * Game.TILE_SIZE - 3);
        playerNode.setTranslateY(player_y * Game.TILE_SIZE - 3);

        ObservableList<Node> children = pane.getChildren();
        children.add(playerNode);
        ghosts.forEach(ghost -> children.add(ghost.getNode()));

        running = true;
    }

    void stop() {
        running = false;
    }

    void left() {
        if (running && player_x>0 && isNotWall(player_x - 1, player_y)) {
            player_x--;
            playerRefresh();
        }
    }
    void right() {
        if (running && player_x<BOARD_WIDTH-1 && isNotWall(player_x + 1, player_y)) {
            player_x++;
            playerRefresh();
        }
    }
    void up() {
        if (running && player_y>0 && isNotWall(player_x, player_y - 1)) {
            player_y--;
            playerRefresh();
        }
    }
    void down() {
        if (running && player_y<BOARD_HEIGHT-1 && isNotWall(player_x, player_y + 1)) {
            player_y++;
            playerRefresh();
        }
    }

    void playerRefresh() {

        // déplacement de l'élément graphique du joueur

        // déplacement avec transition visuelle
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(playerNode);
        transition.setToX(player_x * Game.TILE_SIZE - 3);
        transition.setToY(player_y * Game.TILE_SIZE - 3);
        transition.setDuration(Duration.millis(80));
        transition.play();

        // déplacement sans transition visuelle
        // playerNode.setTranslateX(player_x * Game.TILE_SIZE - 3);
        // playerNode.setTranslateY(player_y * Game.TILE_SIZE - 3);

        // le joueur a-t-il trouvé une sortie ?
        if (isExit(player_x, player_y)) {
            tiles[player_y*BOARD_WIDTH + player_x].setState(TileState.EMPTY);
            endGame(true);
        } else {

            // test collision avec fantome
            ghosts.forEach(ghost -> {
                if (ghost.getX() == player_x && ghost.getY() == player_y) {
                    endGame(false);
                }
            });

            // Mise à jour de visited
            int value = visited[player_y*BOARD_WIDTH + player_x] + 1;
            if (value > 2) value = 1;

            visited[player_y*BOARD_WIDTH + player_x] = value;

            // Mise à jour de la visibilité des murs
            level.adjustWalls(this);
        }

    }

    Tile getTile(int x, int y) {
        return tiles[y*BOARD_WIDTH + x];
    }

    int isVisited(int x, int y) {
        return visited[y*BOARD_WIDTH + x];
    }

    boolean isNotWall(int x, int y) {
        return tiles[y*BOARD_WIDTH + x].getState() != TileState.WALL;
    }

    boolean isExit(int x, int y) {
        return tiles[y*BOARD_WIDTH + x].getState() == TileState.EXIT;
    }

    public void animate() {
        if (running) {
            ghosts.forEach(ghost -> {
                ghost.nextMove();
                // fin de jeu si un fantome vient toucher le joueur
                if (ghost.getX() == player_x && ghost.getY() == player_y) {
                    endGame(false);
                }
            });
        }
    }

    private void endGame(Boolean success) {
        if (success) {
            label.setText("GAGNE ! " + message);
        } else {
            label.setText("PERDU ! " + message);
        }
        running = false;
    }
}
