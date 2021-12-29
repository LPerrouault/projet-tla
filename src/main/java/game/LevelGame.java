package game;

import java.io.File;
import java.io.FileNotFoundException;
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
}
