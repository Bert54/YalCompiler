package yal;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;
import yal.tds.TDS;
import yal.tds.Valeurs;

public class Yal {

    /**
     * Constructeur du programme principal
     * @param nomFichier nom du fichier a tester
     */

    public Yal(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(".data\nln : 	.asciiz \"\\n\"");  // Constante pour le saut de ligne
            flot.println("vrai : 	.asciiz \"vrai\"");
            flot.println("faux : 	.asciiz \"faux\"");
            flot.println("\n.text\nmain :\n"); // Début du programme MIPS
            // J'empile le numéro de bloc du main
            flot.println("li $v0,0");
            flot.println("sw $v0,($sp)");
            flot.println("addi $sp,$sp,-4");
            // Mémorise la tête de pile
            flot.println("move $s7,$sp\n");
            //flot.println("addi $sp, $sp, " + Valeurs.getInstance().getTaillePile(TDS.getInstance().getTableLocaleCourante().getNumBloc()) + "\n"); // Incrémententation du compteur afin de réserver la place pour les variables
            flot.println(arbre.toMIPS()); // Transformation de l'arbre abstrait en code MIPS
            flot.println("end :\n\n" + // Fin du programme MIPS
                    "li $v0, 10\n" +
                    "syscall ");
            flot.close() ;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(yal.Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new yal.Yal(args[0]) ;
    }
    
}
