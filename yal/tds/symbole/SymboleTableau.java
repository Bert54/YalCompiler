package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public class SymboleTableau extends SymboleVariable {

    private int enjambe;
    private ExpressionBinaire exp;
    private int numBloc;


    /**
     * Constructeur d'un symbole de variable correspondant à un tableau
     * @param d Origine du tableau
     * @param enj enjambé
     * @param e taille du tableau
     * @param num numero de bloc
     */
    public SymboleTableau(int d, int enj, ExpressionBinaire e, int num) {
        super(d, num);
        this.enjambe = enj;
        this.exp = e;
        this.numBloc = num;
    }

    @Override
    public int getEnjambe() {
        return this.enjambe;
    }

    @Override
    public ExpressionBinaire getExpression() {
        return this.exp;
    }

    @Override
    public int getNumBloc() {
        return this.numBloc;
    }
}
