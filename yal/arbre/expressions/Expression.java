package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected Expression expg;
    protected String oper;
    protected Expression expd;

    /**
     * Constructeur de l'arbre abstrait
     *
     * @param n numero de ligne
     */
    protected Expression(int n) {
        super(n);
        this.expd = null;
        this.expg = null;
    }

    public Expression(int n, Expression expg, String oper, Expression expd) {
        super(n);
        this.expg = expg;
        this.oper = oper;
        this.expd = expd;
    }

}
