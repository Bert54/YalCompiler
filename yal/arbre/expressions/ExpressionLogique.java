package yal.arbre.expressions;

import yal.tds.Valeurs;

public class ExpressionLogique extends Expression {

    /**
     * Constructeur d'une expression logique simple
     * @param n numero de ligne
     */
    protected ExpressionLogique(int n) {
        super(n) ;
    }

    /**
     * Constructeur d'une expression logique
     * @param n numero de ligne
     * @param expg operande gauche
     * @param oper operateur
     * @param expd operande droit
     */
    public ExpressionLogique(int n, Expression expg, String oper, Expression expd) {
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
        // Récupération des résultats logique des exp gauche et droite
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $t8, 0($sp)\n");
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");
        // opérateur "et" ou "ou" : on a deux expressions logiques, on a donc que des 0 et 1 à comparer
        if (this.oper.equals("et")) {
            string.append("and $v0, $v0, $t8\n");

        }
        else if (this.oper.equals("ou")) {
            string.append("or $v0, $v0, $t8\n");
        }
        else {
            // Sinon, on a deux expressions binaire. On les compare donc normalement et on enregistre un booléen sous forme d'entier
            switch (this.oper) {
                case "<":
                    string.append("bge $v0, $t8, condcompafaux" + Valeurs.getInstance().getCompteurComparaisonLogique() + "\n");
                    break;
                case ">":
                    string.append("ble $v0, $t8, condcompafaux" + Valeurs.getInstance().getCompteurComparaisonLogique() + "\n");
                    break;
                case "==":
                    string.append("bne $v0, $t8, condcompafaux" + Valeurs.getInstance().getCompteurComparaisonLogique() + "\n");
                    break;
                case "!=":
                    string.append("beq $v0, $t8, condcompafaux" + Valeurs.getInstance().getCompteurComparaisonLogique() + "\n");

            }
            string.append("condcompavrai" + Valeurs.getInstance().getCompteurComparaisonLogique() + ": \n");
            string.append("li $v0, 1\n");
            string.append("b fincondcompa" + Valeurs.getInstance().getCompteurComparaisonLogique() + "\n");
            string.append("condcompafaux" + Valeurs.getInstance().getCompteurComparaisonLogique() + ": \n");
            string.append("li $v0, 0\n");
            string.append("fincondcompa" + Valeurs.getInstance().getCompteurComparaisonLogique() + ": \n");
            Valeurs.getInstance().incrementerCompteurComparaisonLogique();

        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }
}
