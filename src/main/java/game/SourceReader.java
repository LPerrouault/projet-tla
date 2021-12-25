package game;

/*
 * SourceReader gère la lecture d'une chaine de caractère,
 * caractère par caractère, avec méthode "goBack",
 * pour l'analyse lexicale
 */
public class SourceReader {

    private String contenu;
    private int index=0;

    public SourceReader(String contenu) {
        this.contenu = contenu;
    }

    /*
   Renvoie un caractère ou null si fin de fichier
     */
    public Character lectureSymbole() {
        if (index >= contenu.length()) return null;
        char c = contenu.charAt(index);
        index++;
        return c;
    }

    /*
    Remet en lecture un caractère
     */
    public void goBack() throws Exception {
        if (index == 0) {
            throw new Exception("Appel de goBack interdit");
        } else {
            index--;
        }
    }

}
