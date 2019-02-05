package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    protected Expression expg;
    protected String oper;
    protected Expression expd;

    /**
     * Constructeur d'une expression simple
     * @param n numero de ligne
     */
    protected Expression(int n) {
        super(n);
        this.expd = null;
        this.expg = null;
    }

    /**
     * Constructeur d'une expression
     * @param n numero de ligne
     * @param expg operande gauche
     * @param oper operateur
     * @param expd operande droit
     */
    public Expression(int n, Expression expg, String oper, Expression expd) {
        super(n);
        this.expg = expg;
        this.oper = oper;
        this.expd = expd;
    }

}
