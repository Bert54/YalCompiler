package yal.arbre.expressions;

public class ExpressionBinaire extends Expression {

    /**
     * Constructeur d'une expression binaire simple
     * @param n numero de ligne
     */
    protected ExpressionBinaire(int n) {
        super(n) ;
    }

    /**
     * Constructeur d'une expression binaire
     * @param n numero de ligne
     * @param expg operande gauche
     * @param oper operateur
     * @param expd operande droit
     */
    public ExpressionBinaire(int n, Expression expg, String oper, Expression expd) {
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
        // Génération du code des expressions de gauche et droite avant toute chose
        string.append(this.expg.toMIPS());
        string.append(this.expd.toMIPS());
        // Récupération des résultats des exp gauche et droite
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $t8, 0($sp)\n");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        switch (oper) { // opération entre les deux expressions
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
            case "/":
                string.append("div $v0, $t8\n");
                string.append("mflo $v0\n");
                break;
        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
