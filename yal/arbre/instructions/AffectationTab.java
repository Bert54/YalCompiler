package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.IdfTab;
import yal.tds.TDS;
import yal.tds.TableLocale;

public class AffectationTab extends Instruction {

    private IdfTab idft;
    private ExpressionBinaire exp;

    /**
     * Constructeur d'une affectation de tableau
     * @param n numéro de ligne
     * @param tab indentificateur du tableau
     * @param e indice du tableau à affecter
     */
    public AffectationTab(int n, IdfTab tab, ExpressionBinaire e) {
        super(n);
        this.idft = tab;
        this.exp = e;
    }

    @Override
    public void verifier() {
        this.idft.verifier();
        this.exp.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        string.append(this.exp.toMIPS()); // On évalue l'expression calculant la valeur à affecter
        string.append(this.idft.getPosition().toMIPS()); // On évalue l'expression qui va déterminer l'indice du tableau
        string.append("addi $sp, $sp, 4\n"); // Récupération de l'indice et calcul de la position vis-à-vis de cet indice
        string.append("lw $v0, 0($sp)\n");
        int enjNeg = this.idft.getEnjambe() - this.idft.getEnjambe() * 2; // enjambe positif, on doit la rendre négative car on doit descendre dans la pile
        string.append("li $t8, " + enjNeg + "\n");
        string.append("mult $v0, $t8\n");
        string.append("mflo $v0\n");
        if (this.idft.getBlocRef() > -1) {
            TableLocale pere = TDS.getInstance().getTableLocaleCourante();
            int superBloc = pere.getNumBloc();
            string.append("addi $sp, $sp, 4\n");
            string.append("lw $v1, 0($sp)\n");
            string.append("move $a2, $sp\n");
            string.append("move $a3, $s7\n");
            while (superBloc != this.idft.getBlocRef()) {
                string.append("lw $s7, 12($s7)\n");
                pere = pere.getTableLocalPere();
                superBloc = pere.getNumBloc();
            }
            if (this.idft.estDynamique()) { // Chargement d'une valeur d'un tableau dynamique
                string.append("lw $a1, " + this.idft.getOrigine() + "($s7)\n"); // Chargement de l'adresse d'implémentation du tableau dynamique
                string.append("move $t8, $a1\n");
                string.append("add $a1, $v0, $t8\n");
            }
            else { // Chargement d'une valeur d'un tableau statique
                string.append("li $t8, " + this.idft.getOrigine() + "\n");
                string.append("add $t8, $t8, $s7\n");
                string.append("add $a1, $v0, $t8\n");
            }
            string.append("sw $v1, 0($a1)\n");
            string.append("move $sp, $a2\n");
            string.append("move $s7, $a3\n");
        }
        else {
            if (this.idft.estDynamique()) { // Chargement d'une valeur d'un tableau dynamique
                string.append("lw $a1, " + this.idft.getOrigine() + "($s7)\n"); // Chargement de l'adresse d'implémentation du tableau dynamique
                string.append("move $t8, $a1\n");
                string.append("add $a1, $v0, $t8\n");
            }
            else { // Chargement d'une valeur d'un tableau statique
                string.append("li $t8, " + this.idft.getOrigine() + "\n");
                string.append("add $t8, $t8, $s7\n");
                string.append("add $a1, $v0, $t8\n");
            }
            string.append("addi $sp, $sp, 4\n"); // Récupération de la valeur à affecter et sauvegarde
            string.append("lw $v0, 0($sp)\n");
            string.append("sw $v0, 0($a1)\n");
        }
        return string.toString();
    }
}
