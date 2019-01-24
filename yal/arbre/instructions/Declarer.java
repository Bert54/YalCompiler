package yal.arbre.instructions;

import yal.tds.TDS;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Declarer extends Instruction {

    private String nom;
    private EntreeVariable ev;
    private int dep;

    public Declarer(int n, String nom, EntreeVariable ev) {
        super(n);
        this.ev = ev;
        this.nom = nom;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(this.ev);
        this.dep = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append("addi $sp, $sp, -4\n");
        string.append("sw $zero, " + dep + "($s7)" + "\n");
        return string.toString();
    }
}
