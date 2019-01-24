package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait {

    /**
     * Constructeur d'une expression
     * @param n numero de ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

}
