package yal.arbre.expressions;

import yal.exceptions.DivisionParZeroException;

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
                // Si l'expression droite est juste une constante, alors on sait si l'utilisateur tente de
                // diviser par 0. Si c'est le cas, alors on produit une exception
                if (this.expd instanceof ConstanteEntiere) {
                    int cons = Integer.parseInt(expd.toString());
                    if (cons == 0) {
                        throw new DivisionParZeroException(this.getNoLigne(), "Tentative de division par zéro");
                    }
                }
                string.append("div $t8, $v0\n");
                string.append("mfhi $v0\n");
                break;
        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }

}
