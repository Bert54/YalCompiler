package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public class SymboleVariable extends Symbole {

    private int dep;

    /**
     * Constructeur d'un symbole de variable
     * @param d Déplacement de la variable
     */
    public SymboleVariable(int d) {
        this.dep = d;
    }

    /**
     * Retourne la valeur de déplacement de cette variable
     * @return La valeur d déplacement
     */
    public int getDeplacement() {
        return this.dep;
    }

    @Override
    public int getNumBloc() {
        return 0;
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
