package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected ExpressionBinaire expg;
    protected String oper;
    protected ExpressionBinaire expd;

    /**
     * Constructeur de l'arbre abstrait
     *
     * @param n numero de ligne
     */
    protected Expression(int n) {
        super(n);
    }

    public Expression(int n, ExpressionBinaire expg, String oper, ExpressionBinaire expd) {
        super(n);
        this.expg = expg;
        this.oper = oper;
        this.expd = expd;
    }

}
