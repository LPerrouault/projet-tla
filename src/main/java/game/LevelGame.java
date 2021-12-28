package game;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;

public class LevelGame implements Level {

    public char[] getWalls(String fileName) throws FileNotFoundException {
        String line = null;
        try ( var scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                if (line == null) {
                    line = scanner.nextLine();
                }
                line += scanner.nextLine();
            }
            line = line.replace("setWalls","");
            line = line.replace("(","");
            line = line.replace(")","");
            line = line.replace(" ","");

        }
        return line.toCharArray();
    }

    public void setWalls(int value, String level ){
        String filneme = "level"+value+".txt";
        File file = new File("src/main/resources/level/"+filneme);

            try {
                FileWriter fw =  new FileWriter(file.getName());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(level);
                bw.close();
            } catch (IOException e) {
                System.out.println("Erreur création du fichier " );
                System.out.println(level);
            }
    }

    public Edio getEdioLevel1() {
        ArrayList<Integer> sequenceMouvements = new ArrayList<Integer>();
        sequenceMouvements.add(4);
        sequenceMouvements.add(6);
        sequenceMouvements.add(2);
        sequenceMouvements.add(8);
        Edio edio = new Edio(12,
                new EdioAction[]{
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU
                },
                sequenceMouvements);
        return edio;
    }

    public Edio getEdioLevel2() {
        ArrayList<Integer> sequenceMouvements = new ArrayList<Integer>();
        sequenceMouvements.add(4);
        sequenceMouvements.add(2);
        sequenceMouvements.add(6);
        sequenceMouvements.add(8);
        Edio edio = new Edio(11,
                new EdioAction[]{
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_ROULEAU,
                    EdioAction.ATTACK_ROULEAU
                },
                sequenceMouvements);
        return edio;
    }

    /*public void adjustWalls(Game game) {
        game.getTile(2, 9).setState(
                game.isVisited(2, 10) > 0 ?
                        TileState.WALL :
                        TileState.EMPTY
        );
        game.getTile(12, 7).setState(
                (game.isVisited(2, 10) == 1 && game.isVisited(12, 4) != 1) ?
                        TileState.EMPTY :
                        TileState.WALL
        );
    }*/
}
