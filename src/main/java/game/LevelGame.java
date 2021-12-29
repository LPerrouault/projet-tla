package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelGame{
    private char[] walls;
    private Edio edio;
    
    public LevelGame(){
    }
    public LevelGame(char[] walls, Edio edio){
        this.walls = walls;
        this.edio = edio;
    }
    
    public char[] getWalls(){
        return walls;
    }
    public Edio getEdio(){
        return edio;
    }
    public void setWalls(char[] walls){
        this.walls = walls;
    }
    public void setEdio(Edio edio){
        this.edio = edio;
    }

    /**
     * Retourne le contenu du fichier du niveau
     * @param fileName
     * @return
     * @throws FileNotFoundException 
     */
    public String getFile(String fileName) throws FileNotFoundException {
        String line = null;
        try ( var scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                if (line == null) {
                    line = scanner.nextLine();
                }
                line += scanner.nextLine();
            }
        }
        return line;
    }
    /*
    public Edio getEdioLevel1() {
        ArrayList<Integer> sequenceMouvements = new ArrayList<Integer>();
        sequenceMouvements.add(4);
        sequenceMouvements.add(6);
        sequenceMouvements.add(1);
        sequenceMouvements.add(8);
        sequenceMouvements.add(11);
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
                    EdioAction.ATTACK_ROULEAU,
                    EdioAction.MOVE,
                    EdioAction.PREPARE_COUTEAU,
                    EdioAction.ATTACK_COUTEAU
                },
                sequenceMouvements);
        return edio;
    }

    public Lemonde getLeMondeLevel1() {
        int[][] tabCases = {
                {1, 2},
                {8, 8}
        };
        Lemonde lemonde = new Lemonde(tabCases);
        return lemonde;
    }
    
    public Lemonde getLeMondeLevel2() {
        int[][] tabCases = {
                {1, 2},
                {8, 8}
        };
        Lemonde lemonde = new Lemonde(tabCases);
        return lemonde;
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
    }*/
}
