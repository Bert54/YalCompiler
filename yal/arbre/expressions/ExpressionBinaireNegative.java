package yal.arbre.expressions;

public class ExpressionBinaireNegative extends ExpressionBinaire {

    ExpressionBinaire exp;

    public ExpressionBinaireNegative(int n, ExpressionBinaire e) {
        super(n);
        this.exp = e;
    }

    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("li $t8, 2\n");
        string.append("mult $v0, $t8\n");
        string.append("mflo $t8\n");
        string.append("lw $v0, 0($sp)\n");
        string.append("sub $v0, $v0, $t8\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }
}
