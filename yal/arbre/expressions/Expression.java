package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public class Expression extends ArbreAbstrait {

    protected Expression expg;
    protected String oper;
    protected Expression expd;

    /**
     * Constructeur d'une expression
     * @param n numero de ligne
     */
    protected Expression(int n) {
        super(n) ;
    }

    public Expression(int n, Expression expg, String oper, Expression expd) {
        super(n);
        this.expg = expg;
        this.oper = oper;
        this.expd = expd;
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
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $t8, 0($sp)\n");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        switch (oper) {
            case "+":
                string.append("add $v0, $v0, $t8\n");
                break;
            default:
                System.out.println("Fuck you");
        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
