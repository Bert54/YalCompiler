package yal.tds.symbole;

import yal.arbre.expressions.ExpressionBinaire;

public abstract class Symbole {

    public abstract int getDeplacement();

    public abstract int getNumBloc();

    public abstract int getEnjambe();

    public abstract ExpressionBinaire getExpression();

}
