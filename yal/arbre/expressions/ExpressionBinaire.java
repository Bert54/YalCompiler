package yal.arbre.expressions;

public class ExpressionBinaire extends Expression {

    /**
     * Constructeur d'une expression
     * @param n numero de ligne
     */
    protected ExpressionBinaire(int n) {
        super(n) ;
    }

    public ExpressionBinaire(int n, ExpressionBinaire expg, String oper, ExpressionBinaire expd) {
        super(n, expg, oper, expd);
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
            case "-":
                string.append("sub $v0, $v0, $t8\n");
                break;
            case "*":
                string.append("mult $v0, $t8\n");
                string.append("mflo $v0\n");
                break;
            default:
                System.out.println("Fuck you");
        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
