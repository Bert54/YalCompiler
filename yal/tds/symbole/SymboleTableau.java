package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public class SymboleTableau extends SymboleVariable {

    private int enjambe;
    private ExpressionBinaire exp;

    /**
     * Constructeur d'un symbole de variable
     *
     * @param d Déplacement de la variable
     */

    public SymboleTableau(int d, int enj, ExpressionBinaire e) {
        super(d);
        this.enjambe = enj;
        this.exp = e;
    }

    @Override
    public int getEnjambe() {
        return this.enjambe;
    }

    @Override
    public ExpressionBinaire getExpression() {
        return this.exp;
    }

}
