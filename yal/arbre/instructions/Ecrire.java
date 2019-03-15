package yal.arbre.instructions;

import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.ExpressionLogique;
import yal.tds.Valeurs;

public class Ecrire extends Instruction {

    protected ExpressionBinaire expB ;
    protected ExpressionLogique expL ;

    /**
     * Constructeur d'une ecriture
     * @param e expression binaire a ecrire
     * @param n numero de ligne
     */
    public Ecrire (ExpressionBinaire e, int n) {
        super(n) ;
        expB = e ;
        expL = null;
    }
    /**
     * Constructeur d'une ecriture
     * @param e expression logique a ecrire
     * @param n numero de ligne
     */
    public Ecrire (ExpressionLogique e, int n) {
        super(n) ;
        expL = e ;
        expB = null;
    }

    @Override
    public void verifier() {
        // Il ne faut pas vérifier l'expression null
        if(expB != null)
            expB.verifier();
        if(expL != null)
            expL.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Ecriture\n");
        if(expB != null) {  // On vérie si on a bien une expression binaire
            string.append(this.expB.toMIPS());
            string.append("addi $sp, $sp, 4\n");
            string.append("lw $v0, 0($sp)\n");
            string.append("move $a0, $v0\n");   // placement de $v0 dans $a0
            string.append("li $v0, 1\n");       // code du print d'un entier
        }
        else {  // Sinon c'est une expression logique
            string.append(this.expL.toMIPS());
            string.append("addi $sp, $sp, 4\n");
            string.append("lw $v0, 0($sp)\n");
            string.append("move $t8, $v0\n");   // placement de $v0 dans $t8
            string.append("li $v0, 4\n");       // code du print d'une chaine de caractères
            // Branchement
            string.append("beqz $t8, boolean" + Valeurs.getInstance().getCompteurBooleen() + "\n");
            // Instruction ALORS. != 0 donc vrai
            string.append("la $a0, vrai\n");
            string.append("b finBool"+Valeurs.getInstance().getCompteurBooleen()+"\n");
            // Instruction SINON. == 0 donc faux
            string.append("boolean" + Valeurs.getInstance().getCompteurBooleen() + ": \n");
            string.append("la $a0, faux\n");
            string.append("finBool"+Valeurs.getInstance().getCompteurBooleen()+":\n");
            Valeurs.getInstance().incrementerCompteurBoleen();
        }
        string.append("syscall\n");         // appel du système pour l'affichage de l'entier
        string.append(this.sautDeLigne());
        return string.toString();
    }

}
