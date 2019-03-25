package yal.arbre.expressions;

public class ExpressionBinaireNegative extends ExpressionBinaire {

    ExpressionBinaire exp;

    /**
     * Constructeur d'une expression binaire avec signe négatif
     * @param n Numero de ligne
     * @param e L'expression binaire négative
     */
    public ExpressionBinaireNegative(int n, ExpressionBinaire e) {
        super(n);
        this.exp = e;
    }

    public ExpressionBinaire getExp() { return this.exp; }

    @Override
    public void verifier() {
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        // On évalue l'expression avant toute chose
        string.append(this.exp.toMIPS());
        // Négation de l'expression
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
