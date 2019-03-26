package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.IdfTab;

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
        string.append("li $t8, " + this.idft.getOrigine() + "\n");
        string.append("add $a1, $v0, $t8\n");
        string.append("addi $sp, $sp, 4\n"); // Récupération de la valeur à affecter et sauvegarde
        string.append("lw $v0, 0($sp)\n");
        string.append("sw $v0, 0($a1)\n");
        return string.toString();
    }
}
