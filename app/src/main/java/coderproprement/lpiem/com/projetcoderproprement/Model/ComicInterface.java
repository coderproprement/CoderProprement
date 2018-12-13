package coderproprement.lpiem.com.projetcoderproprement.Model;

public interface ComicInterface {
    /**
     * Ajoute un créateur de comic dans la liste
     * @param c le créateur de comic
     */
    void addCreator(ComicCreator c);

    /**
     * Vérifie si le créateur de comic est présent ou non dans la liste
     * @param c le créateurd e comic
     * @return {true} => présent dans la liste | {false} => non présent dans la liste
     */
    boolean isCreatorPresent (ComicCreator c);

    /**
     * Supprime un Créateur de comic
     * @param c le créateur à supprimer
     */
    void removeCreator(ComicCreator c);
}
