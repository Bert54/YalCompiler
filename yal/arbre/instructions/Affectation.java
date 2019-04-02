package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;
import yal.tds.TDS;
import yal.tds.TableLocale;

public class Affectation extends Instruction {

    private Idf idf;
    private ExpressionBinaire exp;

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
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Affectation\n");
        string.append(this.exp.toMIPS());
        string.append("addi $sp, $sp, 4\n");
        string.append("lw $v0, 0($sp)\n");    // on charge la variable
        if (this.idf.getBlocRef() > -1) {
            TableLocale pere = TDS.getInstance().getTableLocaleCourante();
            int superBloc = pere.getNumBloc();
            string.append("move $a2, $sp\n");
            string.append("move $a3, $s7\n");
            while (superBloc != this.idf.getBlocRef()) {
                string.append("lw $s7, 12($s7)\n");
                pere = pere.getTableLocalPere();
                superBloc = pere.getNumBloc();
            }
            string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
            string.append("move $sp, $a2\n");
            string.append("move $s7, $a3\n");
        }
        else {
            string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)\n");
        }
        return string.toString();
    }
}
