package yal.arbre.expressions;

import yal.tds.TDS;
import yal.tds.Valeurs;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends ExpressionBinaire {

    private String nom;
    private int dep;
    private Symbole symbole;
    private int numBloc;

    /**
     * Constructeur d'un identificateur
     * @param n numero de ligne
     * @param nom nom de l'identificateur
     */
    public Idf(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    /**
     * Getter sur le deplacement de la variable dans la pile
     * @return deplacement dans la pile
     */
    public int getDeplacement() {
        return this.dep;
    }

    public String getNom() {
        return this.nom;
    }

    @Override
    public void verifier() {
        this.symbole = TDS.getInstance().getTableLocaleCourante().identifier(new EntreeVariable(this.nom, this.getNoLigne()));
        this.dep = symbole.getDeplacement();
        this.numBloc = TDS.getInstance().getTableLocaleCourante().getNumBloc();
    }

    @Override
    public String toMIPS() {
    /*    StringBuilder string = new StringBuilder();
        string.append("lw $v0, " + this.dep + "($s7)\n");
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }*/
        StringBuilder string = new StringBuilder("");
        if (this.numBloc != symbole.getNumBloc()) {
            int cnt = Valeurs.getInstance().getCompteurBoucle();
            Valeurs.getInstance().incrementerCompteurBoucle();
            // On stocke la base locale courante
            string.append("#idf\n");
            string.append("move $t8,$s7\n");
            // Tant qu'on a pas trouvé le bon numéro de bloc, on continue à remonter grâce au chainage dynamique
            string.append("tantque" + cnt + ": \n");  // étiquette tantque
            string.append("lw $v0,4($s7)\n");
            string.append("beq $v0," + symbole.getNumBloc() + " fintantque" + cnt + "\n");   // on se dirige
            // On prend le chainage dynamique
            string.append("lw $s7,8($s7)\n");
            string.append("b tantque" + cnt + "\n");  // on retourne au tantque
            string.append("fintantque" + cnt + ": \n");   // étiquette fintantque

            string.append("lw $v0, " + this.dep + "($s7)\n");
            string.append("sw $v0, 0($sp)\n");
            string.append("addi $sp, $sp, -4\n");

            // On revient à la base locale courante
            string.append("move $s7,$t8\n");

        } else {
            string.append("lw $v0, " + this.dep + "($s7)\n");
            string.append("sw $v0, 0($sp)\n");
            string.append("addi $sp, $sp, -4\n");
        }

//        if (numBloc != symbole.getNumBloc()) {
//            // On revient à la base locale courante
//            string.append("move $s7,$t8\n");
//        }
        return string.toString();
    }
}
