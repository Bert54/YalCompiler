package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.IdfTab;
import yal.tds.TDS;
import yal.tds.Valeurs;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class AffectationTab extends Instruction {

    private IdfTab idft;
    private ExpressionBinaire exp;
    private Symbole symbole;
    private int numBloc;

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
        symbole = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(idft.getNom(),this.noLigne));
        numBloc = TDS.getInstance().getTableLocaleCourante().getNumBloc();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        if (!(numBloc == symbole.getNumBloc())) {
            int cnt = Valeurs.getInstance().getCompteurBoucle();
            Valeurs.getInstance().incrementerCompteurBoucle();
            // On stocke la base locale courante
            string.append("move $t8,$s7\n");
            // Tant qu'on a pas trouvé le bon numéro de bloc, on continue à remonter grâce au chainage dynamique
            string.append("tantque" + cnt + ": \n");  // étiquette tantque
            string.append("lw $v0,4($s7)\n");
            string.append("beq $v0," + symbole.getNumBloc() + " fintantque" + cnt + "\n");   // on se dirige
            // On prend le chainage dynamique
            string.append("lw $s7,8($s7)\n");
            string.append("b tantque" + cnt + "\n");  // on retourne au tantque
            string.append("fintantque" + cnt + ": \n");   // étiquette fintantque

            // On revient à la base locale courante
            string.append("move $t9,$s7\n");
            string.append("move $s7,$t8\n");
            string.append(this.exp.toMIPS()); // On évalue l'expression calculant la valeur à affecter
            string.append(this.idft.getPosition().toMIPS()); // On évalue l'expression qui va déterminer l'indice du tableau
            string.append("addi $sp, $sp, 4\n"); // Récupération de l'indice et calcul de la position vis-à-vis de cet indice
            string.append("lw $v0, 0($sp)\n");
            int enjNeg = this.idft.getEnjambe() - this.idft.getEnjambe() * 2; // enjambe positif, on doit la rendre négative car on doit descendre dans la pile
            string.append("li $t8, " + enjNeg + "\n");
            string.append("mult $v0, $t8\n");
            string.append("mflo $v0\n");
            if (this.idft.estDynamique()) { // Chargement d'une valeur d'un tableau dynamique
                string.append("lw $a1, " + this.idft.getOrigine() + "($s7)\n"); // Chargement de l'adresse d'implémentation du tableau dynamique
                string.append("move $t8, $a1\n");
                string.append("add $a1, $v0, $t8\n");
            } else { // Chargement d'une valeur d'un tableau statique
                string.append("li $t8, " + this.idft.getOrigine() + "\n");
                string.append("add $t8, $t8, $s7\n");
                string.append("add $a1, $v0, $t8\n");
            }
            string.append("addi $sp, $sp, 4\n"); // Récupération de la valeur à affecter et sauvegarde
            string.append("lw $v0, 0($sp)\n");
            string.append("sw $v0, 0($a1)\n");

        } else {

            string.append(this.exp.toMIPS()); // On évalue l'expression calculant la valeur à affecter
            string.append(this.idft.getPosition().toMIPS()); // On évalue l'expression qui va déterminer l'indice du tableau
            string.append("addi $sp, $sp, 4\n"); // Récupération de l'indice et calcul de la position vis-à-vis de cet indice
            string.append("lw $v0, 0($sp)\n");
            int enjNeg = this.idft.getEnjambe() - this.idft.getEnjambe() * 2; // enjambe positif, on doit la rendre négative car on doit descendre dans la pile
            string.append("li $t8, " + enjNeg + "\n");
            string.append("mult $v0, $t8\n");
            string.append("mflo $v0\n");
            if (this.idft.estDynamique()) { // Chargement d'une valeur d'un tableau dynamique
                string.append("lw $a1, " + this.idft.getOrigine() + "($s7)\n"); // Chargement de l'adresse d'implémentation du tableau dynamique
                string.append("move $t8, $a1\n");
                string.append("add $a1, $v0, $t8\n");
            } else { // Chargement d'une valeur d'un tableau statique
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
