package yal.arbre.expressions;

public class ExpressionLogique extends Expression {
    /**
     * Constructeur de l'arbre abstrait
     *
     * @param n numero de ligne
     */
    public ExpressionLogique(int n, Expression expg, String oper, Expression expd) {
        super(n, expg, oper, expd);
    }

    public String getOperande() {
        return this.oper;
    }

    @Override
    public void verifier() {
        this.expg.verifier();
        this.expd.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append(this.expg.toMIPS());
        string.append(this.expd.toMIPS());
        return string.toString();
    }
}
