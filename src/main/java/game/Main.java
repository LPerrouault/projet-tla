package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // fenêtre principale et panneau de menu

        GridPane menuPane = new GridPane();
        Button btnLevel1 = new Button("level1");
        menuPane.add(btnLevel1, 0, 1);
        Button btnLevel2 = new Button("level2");
        menuPane.add(btnLevel2, 0, 2);
        ImageView imageView = new ImageView(SpritesLibrary.imgPlayerLarge);
        menuPane.add(imageView, 1, 0, 1, 4);

        Scene scene = new Scene(menuPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        // panneau racine du jeu

        BorderPane gamePane = new BorderPane();

        Game game = new Game(gamePane);

        btnLevel1.setOnAction(event -> {
            // affiche le panneau racine du jeu (à la place du panneau de menu)
            scene.setRoot(gamePane);

            // affecte un object correspondant au niveau choisi
            game.setLevel(new Level1());

            // démarre le jeu
            game.start();

            // ajuste la taille de la fenêtre
            primaryStage.sizeToScene();
        });

        btnLevel2.setOnAction(event -> {
            scene.setRoot(gamePane);
            game.setLevel(new Level2());
            game.start();
            primaryStage.sizeToScene();
        });

        // gestion du clavier

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Q) {
                // touche q : quitte le jeu et affiche le menu principal
                game.stop();
                scene.setRoot(menuPane);
                primaryStage.sizeToScene();
            }
            if (event.getCode() == KeyCode.R) {
                // touche r : redémarre le niveau en cours
                game.start();
            }
            if (event.getCode() == KeyCode.LEFT) {
                game.left();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                game.right();
            }
            if (event.getCode() == KeyCode.UP) {
                game.up();
            }
            if (event.getCode() == KeyCode.DOWN) {
                game.down();
            }
        });
    }
}
