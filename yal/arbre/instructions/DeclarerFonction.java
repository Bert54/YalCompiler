package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionBinaire;
import yal.arbre.expressions.Idf;
import yal.exceptions.RetournerManquantException;
import yal.tds.TDS;
import yal.tds.Valeurs;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

import java.util.ArrayList;

public class DeclarerFonction extends Instruction {

    private String nom;
    private ArrayList<Idf> params;
    private ArbreAbstrait corps;
    private int numBloc;

    /**
     * Constructeur d'une declaration
     * @param n numero de ligne
     * @param nom nom de la variable
     */
    public DeclarerFonction(int n, String nom) {
        super(n);
        this.nom = nom;
    }

    /**
     * Constructeur d'une declaration
     * @param n numero de ligne
     * @param nom nom de la variable
     * @param parametres liste des parametres
     * @param corps corps de la fonction
     */
    public DeclarerFonction(int n, String nom, ArrayList<Idf> parametres, ArbreAbstrait corps) {
        super(n);
        this.nom = nom;
        this.params = parametres;
        this.corps = corps;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.getInstance().identifier(new EntreeFonction(this.nom, this.getNoLigne(), this.params.size()));
        this.numBloc = s.getNumBloc();
        TDS.getInstance().entreeBlocVerifier(this.numBloc);
        for (Idf d: this.params) {
            d.verifier();
        }
        this.corps.verifier();
        if (TDS.getInstance().getTableLocaleCourante().getNbRetour() == 0) {
            throw new RetournerManquantException(this.getNoLigne(), "Pas de retour dans le corps principal de la fonction '" + this.nom + "'");
        }
        TDS.getInstance().sortieBlocVerifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder();
        int cnt = Valeurs.getInstance().getNbFonctionPasse();
        Valeurs.getInstance().incrementerNbFontionPasse();
        Valeurs.getInstance().depiler(numBloc);
        string.append("#Déclaration fonction\n");
        // Permet de sauter la fonction lors de l'exécution principal du programme
        string.append("j fonctionskip"+ cnt +"\n");
        // Entrée dans le bloc de la fonction
        //TDS.getInstance().entreeBlocVerifier(this.numBloc);
        string.append(this.nom + this.params.size() + ":\n");    // Nom/Etiquette de la fonction en MIPS
        // Adresse de retour
        string.append("sw $ra, ($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        // Sauvegarde de la base locale
        string.append("sw $s7, ($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        // Empiler le numéro de région
        string.append("li $v0, " + this.numBloc + "\n");
        string.append("sw $v0, ($sp)\n");
        string.append("addi $sp, $sp, -4\n");
        // Initialisation de la base locale
        string.append("move $s7, $sp\n");
        // Récupération des paramètres pour les utiliser en tant que variables locaux
        for (int i = 0 ; i < this.params.size() ; i++) {
            string.append("lw $v0, " + (12 + (this.params.size() * 4)) + "($sp)\n");
            string.append("sw $v0,"  + this.params.get(i).getDeplacement() + "($s7)\n");
            Valeurs.getInstance().empiler(numBloc);
            string.append("addi $sp, $sp, -4\n");
        }
        String a = this.corps.toMIPS(); // Génération du code MIPS du corps de la fonction
        string.append("addi $sp, $sp, " + Valeurs.getInstance().getTaillePile(numBloc) + "\n"); // Incrémententation du compteur afin de réserver la place pour les variables
        string.append(a);
        // Etiquette permettant de sauter la fonction lors de l'exécution principal du programme
        string.append("fonctionskip"+ cnt +":\n");
        return string.toString();
    }

}
