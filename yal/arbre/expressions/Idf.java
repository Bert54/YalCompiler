package yal.arbre.expressions;

import yal.exceptions.VariableNonDeclareeException;
import yal.tds.TDS;
import yal.tds.TableLocale;
import yal.tds.entree.Entree;
import yal.tds.entree.EntreeVariable;
import yal.tds.symbole.Symbole;

public class Idf extends ExpressionBinaire {

    private String nom;
    private int dep;
    private boolean globale;
    private int nbAppel;

    /**
     * Constructeur d'un identificateur
     * @param n numero de ligne
     * @param nom nom de l'identificateur
     */
    public Idf(int n, String nom) {
        super(n);
        this.nom = nom;
        this.nbAppel = 0;
        this.globale = false;
    }

    /**
     * Getter sur le deplacement de la variable dans la pile
     * @return deplacement dans la pile
     */
    public int getDeplacement() {
        return this.dep;
    }

    @Override
    public void verifier() throws VariableNonDeclareeException{
        Entree e = new EntreeVariable(this.nom, this.getNoLigne());
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(e);
        if(s == null) {
            this.nbAppel++;
            globale = true;
        }
        TableLocale pere = TDS.getInstance().getTableLocaleCourante().getTableLocalPere();
        while(pere != null && s == null){
            s = pere.identifier(e);
            pere = pere.getTableLocalPere();
            this.nbAppel ++;
        }
        if(s == null) {
            throw new VariableNonDeclareeException(e.getLigne(), "Variable non déclarée : " + e.getNom());
        }
        this.dep = s.getDeplacement();
    }

    public boolean isGlobale() {
        return globale;
    }

    public int getNbAppel(){
        return this.nbAppel;
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        if(!globale) {
            string.append("lw $v0, " + this.dep + "($s7)\n");
            string.append("sw $v0, 0($sp)\n");
            string.append("addi $sp, $sp, -4\n");
            return string.toString();
        }else{
            System.out.println(getNbAppel());
            for(int i = 0; i<getNbAppel();i++) {
                // on empile le bloc
                string.append("sw $s7, 0($sp)\n");
                string.append("addi $sp, $sp, -4\n");
                // On remonte jusqu'au chainage dynamique
                string.append("lw $s7,8($s7)\n");
            }
            string.append("lw $v0,"+ this.dep +"($s7)\n");
            for(int i = 0; i<getNbAppel();i++) {
                // on revient à la base locale courante
                string.append("addi $sp, $sp, 4\n");
                string.append("lw $s7, ($sp)\n");
            }
            string.append("sw $v0, 0($sp)\n");
            string.append("addi $sp, $sp, -4\n");
            return string.toString();
        }
    }
}
