package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // fenêtre principale et panneau de menu
        BorderPane gameMenu = new BorderPane();
        GridPane menuPane = new GridPane();

        Label gameName = new Label("Les Péripéties Extraordinaires de Jaja\n");
        gameName.setFont(new Font(17));
        gameMenu.setPadding(new Insets(15,20,15,20));
        gameMenu.setTop(gameName);

        ImageView imageJaja = new ImageView(SpritesLibrary.imgJajaLarge);
        ImageView imageEdio = new ImageView(SpritesLibrary.imgEdioCouteauLarge);
        menuPane.add(imageJaja, 0, 0, 1,4);
        menuPane.add(imageEdio, 3, 0,1,4);
        Button btnLevel1 = new Button("1");
        btnLevel1.setPadding(new Insets(15,20,15,20));
        menuPane.add(btnLevel1, 2,2);
        Button btnLevel2 = new Button("2");
        btnLevel2.setPadding(new Insets(15,20,15,20));
        menuPane.add(btnLevel2, 2,3);
        menuPane.setPrefWidth(100);
        menuPane.setPadding( new Insets(15,20,15,20));
        menuPane.setMargin(btnLevel1, new Insets(0,10,5,10));
        menuPane.setMargin(btnLevel2, new Insets(5,10,0,10));
        menuPane.setMargin(imageEdio, new Insets(0,10,0,10));
        menuPane.setMargin(imageJaja, new Insets(0,10,0,10));

        gameMenu.setCenter(menuPane);
        gameMenu.setAlignment(menuPane, Pos.CENTER);


        Scene scene = new Scene(gameMenu);
        primaryStage.setScene(scene);
        primaryStage.show();

        // panneau racine du jeu

        BorderPane gamePane = new BorderPane();

        Game game = new Game(gamePane);

        btnLevel1.setOnAction(event -> {
            // affiche le panneau racine du jeu (à la place du panneau de menu)
            scene.setRoot(gamePane);

            // affecte un object correspondant au niveau choisi
            game.setLevel(new LevelGame());

            // démarre le jeu
            try {
                game.start(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // ajuste la taille de la fenêtre
            primaryStage.sizeToScene();
        });

        btnLevel2.setOnAction(event -> {
            scene.setRoot(gamePane);
            game.setLevel(new LevelGame());
            try {
                game.start(2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.sizeToScene();
        });

        // gestion du clavier

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.Q) {
                // touche q : quitte le jeu et affiche le menu principal
                game.stop();
                scene.setRoot(gameMenu);
                primaryStage.sizeToScene();
            }
            if (event.getCode() == KeyCode.R) {
                // touche r : redémarre le niveau en cours
                try {
                    game.start(game.getValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
