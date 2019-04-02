package yal.tds.entree;

public class EntreeFonction extends Entree {

    int nbParams;

    /**
     * Constructeur d'une entrée
     *
     * @param n Nom de l'entrée
     * @param l Numéro de ligne de l'entrée
     * @param params nombre de paramètres
     */
    public EntreeFonction(String n, int l, int params) {
        super(n, l);
        this.nbParams = params;
    }

    @Override
    public int getNbParams() {
        return this.nbParams;
    }

}
