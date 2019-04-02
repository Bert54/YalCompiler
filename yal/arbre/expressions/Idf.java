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
    private int ref;

    /**
     * Constructeur d'un identificateur
     * @param n numero de ligne
     * @param nom nom de l'identificateur
     */
    public Idf(int n, String nom) {
        super(n);
        this.nom = nom;
        this.ref = -1;
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

    public int getBlocRef() {
        return this.ref;
    }

    @Override
    public void verifier() throws VariableNonDeclareeException {
        Entree e = new EntreeVariable(this.nom, this.getNoLigne());
        Symbole s = TDS.getInstance().getTableLocaleCourante().identifier(e);
        TableLocale pere = TDS.getInstance().getTableLocaleCourante().getTableLocalPere();
        while(pere != null && s == null){
            s = pere.identifier(e);
            this.ref = pere.getNumBloc();
            pere = pere.getTableLocalPere();
        }
        if(s == null) {
            throw new VariableNonDeclareeException(e.getLigne(), "Variable non déclarée : " + e.getNom());
        }
        this.dep = s.getDeplacement();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        if (this.ref > -1) {
            TableLocale pere = TDS.getInstance().getTableLocaleCourante();
            int superBloc = pere.getNumBloc();
            string.append("move $a2, $sp\n");
            string.append("move $a3, $s7\n");
            while (superBloc != this.ref) {
                string.append("lw $s7, 12($s7)\n");
                pere = pere.getTableLocalPere();
                superBloc = pere.getNumBloc();
            }
            string.append("lw $v0, " + this.dep + "($s7)\n");
            string.append("move $sp, $a2\n");
            string.append("move $s7, $a3\n");
        }
        else {
            string.append("lw $v0, " + this.dep + "($s7)\n");
        }
        string.append("sw $v0, 0($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        return string.toString();
    }
}
