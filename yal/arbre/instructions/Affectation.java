package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;
import yal.tds.TDS;
import yal.tds.Valeurs;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Affectation extends Instruction {

    private Idf idf;
    private ExpressionBinaire exp;
    private Symbole symbole;
    private int numBloc;

    /**
     * Constructeur d'une affectation
     * @param n numero de ligne
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
        symbole = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(idf.getNom(),this.noLigne));
        numBloc = TDS.getInstance().getTableLocaleCourante().getNumBloc();
    }

    @Override
    public String toMIPS() {
        /*StringBuilder string = new StringBuilder("#Affectation\n");
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");    // on charge la variable
        string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
        return string.toString();*/
        StringBuilder string = new StringBuilder("#Affectation\n");
        if(! (numBloc == symbole.getNumBloc())) {
            int cnt = Valeurs.getInstance().getCompteurBoucle();
            Valeurs.getInstance().incrementerCompteurBoucle();
            // On stocke la base locale courante
            string.append("move $t8,$s7\n");
            // Tant qu'on a pas trouvé le bon numéro de bloc, on continue à remonter grâce au chainage dynamique
            string.append("tantque" + cnt + ": \n");  // étiquette tantque
            string.append("lw $v0,4($s7)\n");
            string.append("beq $v0,"+symbole.getNumBloc()+" fintantque" + cnt + "\n");   // on se dirige
            // On prend le chainage dynamique
            string.append("lw $s7,8($s7)\n");
            string.append("b tantque" + cnt + "\n");  // on retourne au tantque
            string.append("fintantque" + cnt + ": \n");   // étiquette fintantque

            // On revient à la base locale courante
            string.append("move $t9,$s7\n");
            string.append("move $s7,$t8\n");

            string.append(this.exp.toMIPS());

            string.append("addi $sp, $sp, 4\n");
            string.append("lw $v0, 0($sp)\n");    // on charge la variable
            string.append("sw $v0, " + this.idf.getDeplacement() + "($t9)\n");
        } else {
            string.append(this.exp.toMIPS());
            string.append("addi $sp, $sp, 4\n");
            string.append("lw $v0, 0($sp)\n");    // on charge la variable
            string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");

        }


        return string.toString();
    }
}
