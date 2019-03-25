package yal.tds.symbole;

public class SymboleTableau extends SymboleVariable {

    private int enjambe;

    /**
     * Constructeur d'un symbole de variable
     *
     * @param d Déplacement de la variable
     */

    public SymboleTableau(int d, int enj) {
        super(d);
        this.enjambe = enj;
    }
}
