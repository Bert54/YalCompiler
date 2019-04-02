package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public class SymboleVariable extends Symbole {

    private int dep;
    private int numBloc;

    /**
     * Constructeur d'un symbole de variable
     * @param d Déplacement de la variable
     * @param num numero de bloc de la variable
     */
    public SymboleVariable(int d, int num) {
        this.dep = d;
        numBloc = num;
    }

    @Override
    public int getDeplacement() {
        return this.dep;
    }

    @Override
    public int getNumBloc() {
        return this.numBloc;
    }

    @Override
    public int getEnjambe() {
        return 0;
    }

    @Override
    public ExpressionBinaire getExpression() {
        return null;
    }

}
