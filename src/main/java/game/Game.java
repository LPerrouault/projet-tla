package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game<value> {

    final String message = " R : redémarrer la partie \n" + " \n Q : pour revenir au menu";

    final static int TILE_SIZE = 32;
    final static int BOARD_WIDTH = 20;
    final static int BOARD_HEIGHT = 14;
    public int value;

    // grille
    protected Tile tiles[] = new Tile[BOARD_WIDTH * BOARD_HEIGHT];

    // détermine si un carreau a été parcouru par le joueur,
    // pour produire des effets "cachés" lorsque le joueur se déplace
    // par exemple changer la visibilité de certains murs
    protected int visited[] = new int[BOARD_WIDTH * BOARD_HEIGHT];

    //Coordonnées du joueur
    private int player_x;
    private int player_y;
    private ImageView playerNode;

    //Affichage de Edio
    private Edio edio;

    //Affichage des obstacles créés par Edio
    private List<Obstacle> obstacles;
    private boolean listObstacle = true;

    // éléments de l'interface utilisateur
    private Label label;
    private Pane pane;

    // Jeu en cours ou terminé
    private boolean running;

    // Niveau en cours
    LevelGame levelGame;

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
                //A chaque KeyFrame, lance la fonction animate()
                new KeyFrame(
                        Duration.millis(500),
                        event -> animate()
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void setLevel(LevelGame levelGame) {
        this.levelGame = levelGame;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    void start(int value) throws IOException {
        setValue(value);
        Arrays.fill(visited, 0);

        pane.getChildren().clear();

        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                tiles[y * BOARD_WIDTH + x] = new Tile(x, y, pane);
            }
        }
        char[] walls = null;
        //Choix du niveau
        if (value == 1) {
            //Génération des murs
            walls = levelGame.getWalls("src/main/resources/level/Level1.txt");
            //Génération des obstacles
            obstacles = levelGame.getObstaclesLevel1();
            System.out.println(obstacles);
            edio = levelGame.getEdioLevel1();
        } else if (value == 2) {
            //Génération des murs
            walls = levelGame.getWalls("src/main/resources/level/Level2.txt");
            //Génération des obstacles
            obstacles = levelGame.getObstaclesLevel2();
            edio = levelGame.getEdioLevel2();
        }

        for (int i = 0; i < walls.length; i++) {
            switch (walls[i]) {
                case 'W':
                    tiles[i].setState(TileState.WALL);
                    break;
                case 'F':
                    tiles[i].setState(TileState.EXIT);
                    break;
                case 'D':
                    tiles[i].setState(TileState.DIO);
                    break;
                case 'E':
                    tiles[i].setState(TileState.EMPTY);
            }
        }

        // position initiale du joueur
        player_x = 0;
        player_y = 0;

        playerNode = new ImageView(SpritesLibrary.imgJajaSmall);
        playerNode.setTranslateX(player_x * Game.TILE_SIZE - 3);
        playerNode.setTranslateY(player_y * Game.TILE_SIZE - 3);

        ObservableList<Node> children = pane.getChildren();
        children.add(playerNode);
        children.add(edio.getNode());
        obstacles.forEach(obstacle -> children.add(obstacle.getNode()));
        running = true;
    }

    void stop() {
        running = false;
    }

    void left() {
        if (running && player_x > 0 && isNotWall(player_x - 1, player_y)) {
            player_x--;
            playerRefresh();
        }
    }

    void right() {
        if (running && player_x < BOARD_WIDTH - 1 && isNotWall(player_x + 1, player_y)) {
            player_x++;
            playerRefresh();
        }
    }

    void up() {
        if (running && player_y > 0 && isNotWall(player_x, player_y - 1)) {
            player_y--;
            playerRefresh();
        }
    }

    void down() {
        if (running && player_y < BOARD_HEIGHT - 1 && isNotWall(player_x, player_y + 1)) {
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
            tiles[player_y * BOARD_WIDTH + player_x].setState(TileState.EMPTY);
            endGame(true);
        } else {

            // test collision avec fantome
            obstacles.forEach(ghost -> {
                if (ghost.getX() == player_x && ghost.getY() == player_y) {
                    endGame(false);
                }
            });

            // Mise à jour de visited
            int value = visited[player_y * BOARD_WIDTH + player_x] + 1;
            if (value > 2) {
                value = 1;
            }

            visited[player_y * BOARD_WIDTH + player_x] = value;

            // Mise à jour de la visibilité des murs
            levelGame.adjustWalls(this);
        }

    }

    Tile getTile(int x, int y) {
        return tiles[y * BOARD_WIDTH + x];
    }

    int isVisited(int x, int y) {
        return visited[y * BOARD_WIDTH + x];
    }

    boolean isNotWall(int x, int y) {
        return tiles[y * BOARD_WIDTH + x].getState() != TileState.WALL;
    }

    boolean isExit(int x, int y) {
        return tiles[y * BOARD_WIDTH + x].getState() == TileState.EXIT;
    }

    public void animate() {
        if (running) {
            //Animation de edio
            edio.nextMove();
            //Animation des obstacle
            if (listObstacle == true) {
                ArrayList<Integer> comp = new ArrayList<Integer>();

                obstacles.forEach(obstacle -> {
                    if (obstacle.getX() == -1) {
                        comp.add(-1);
                        if (comp.size() == obstacles.size()) {
                            listObstacle = false;
                            return;
                        }
                    } else {
                        obstacle.nextMove();
                        // fin de jeu si un obstacle de vient toucher le joueur
                        if (obstacle.getX() == player_x && obstacle.getY() == player_y) {
                            endGame(false);
                            System.out.println("stop: obstacle detecté");
                        }
                    }
                });
            } else {
                obstacles.removeAll(obstacles);
            }
        }
    }

    private void endGame(Boolean success) {
        BorderPane borderPane = new BorderPane();
        GridPane endGame = new GridPane();
        Label action = new Label(message);
        action.setFont(new Font(14));
        Label status = new Label();
        pane.getChildren().clear();
        if (success) {
            status.setText(" Victoire ! ");
            status.setTextFill(Color.GREEN);
            ImageView imageJaja = new ImageView(SpritesLibrary.imgJajaLarge);
            endGame.add(imageJaja, 1, 1);
            endGame.setMargin(imageJaja, new Insets(0, 0, 0, 80));
        } else {
            status.setText(" Défaite ! ");
            status.setTextFill(Color.RED);
            ImageView imageJaja = new ImageView(SpritesLibrary.imgJajaDefaite);
            endGame.add(imageJaja, 1, 1);
            endGame.setMargin(imageJaja, new Insets(0, 0, 0, 80));
        }
        endGame.add(status, 1, 0);
        status.setFont(new Font(40));
        endGame.add(action, 1, 2);
        endGame.setMargin(status, new Insets(30, 10, 30, 10));
        status.setAlignment(Pos.CENTER);
        action.setAlignment(Pos.CENTER);
        endGame.setMargin(action, new Insets(40, 10, 40, 10));
        borderPane.setCenter(endGame);
        borderPane.setMargin(endGame, new Insets(80, 0, 50, 200));
        pane.getChildren().add(borderPane);

        running = false;
    }
}
