package yal.tds.entree;

public class EntreeVariable extends Entree {

    /**
     * Constructeur d'une entrée de variable
     * @param n Nom de la variable
     * @param l Numéro de ligne
     */
    public EntreeVariable(String n, int l) {
        super(n, l);
    }

    @Override
    public int getNbParams() {
        return 0;
    }

}
