package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public class SymboleTableau extends SymboleVariable {

    private int enjambe;
    private ExpressionBinaire exp;


    /**
     * Constructeur d'un symbole de variable correspondant à un tableau
     * @param d Origine du tableau
     * @param enj enjambé
     * @param e taille du tableau
     */
    public SymboleTableau(int d, int enj, ExpressionBinaire e) {
        super(d);
        this.enjambe = enj;
        this.exp = e;
    }

    /**
     * retourne l'enjambé du tableau
     * @return l'enjambé du tableau
     */
    @Override
    public int getEnjambe() {
        return this.enjambe;
    }

    /**
     * retourne l'expression correspondant à la taille du tableau
     * @return l'expression correspondant à la taille du tableau
     */
    @Override
    public ExpressionBinaire getExpression() {
        return this.exp;
    }

}
