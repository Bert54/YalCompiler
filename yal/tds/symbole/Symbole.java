package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public abstract class Symbole {

    /**
     * retourne le deplacement
     * @return le deplacement
     */
    public abstract int getDeplacement();

    /**
     * retourne le numero de bloc
     * @return le numero de bloc
     */
    public abstract int getNumBloc();

    /**
     * retourne l'enjambe
     * @return l'enjambe
     */
    public abstract int getEnjambe();

    /**
     * retourne l'expression
     * @return l'expression
     */
    public abstract ExpressionBinaire getExpression();

}
