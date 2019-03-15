package yal.arbre.instructions;

import yal.tds.TDS;
import yal.tds.TableLocale;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Declarer extends Instruction {

    private String nom;
    private int dep;

    /**
     * Constructeur d'une declaration
     * @param n numero de ligne
     * @param nom nom de la variable
     */
    public Declarer(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        this.dep = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Déclaration\n");
        string.append("addi $sp, $sp," + " -4 " + "\n");   // réservation de l'espace pour la variable
        string.append("sw $zero, " + dep + "($s7)" + "\n"); // on place le registre 0 dans la variable
        return string.toString();
    }
}
