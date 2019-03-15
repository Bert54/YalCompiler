package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;

public class Affectation extends Instruction {

    private Idf idf;
    private ExpressionBinaire exp;

    /**
     * Constructeur d'une affectation
     *
     * @param n   numero de ligne
     * @param idf identificateur
     * @param exp expression
     */
    public Affectation(int n, Idf idf, ExpressionBinaire exp) {
        super(n);
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        this.idf.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Affectation\n");
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");    // on charge la variable
        System.out.println(idf.isGlobale());
        if (!idf.isGlobale()) {
            string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
            return string.toString();
        } else{
            for(int i = 0; i<idf.getNbAppel();i++) {
                // on empile le bloc
                string.append("sw $s7, 0($sp)\n");
                string.append("addi $sp, $sp, -4\n");
                // On remonte jusqu'au chainage dynamique
                string.append("lw $s7,8($s7)\n");
            }
            string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
            for(int i = 0; i<idf.getNbAppel();i++) {
                // on revient à la base locale courante
                string.append("addi $sp, $sp, 4\n");
                string.append("lw $s7, ($sp)\n");
            }
            return string.toString();
        }
    }
}
