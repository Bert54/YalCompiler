package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait {

    /**
     * Constructeur d'une instruction
     * @param n numero de ligne
     */
    protected Instruction(int n) {
        super(n);
    }

    /**
     * Permet de passer a la ligne dans le code mips genere
     * @return code mips d'un retour a la ligne
     */
    protected String sautDeLigne() {
        StringBuilder string = new StringBuilder("");
        string.append("\n#Saut de ligne\n");
        string.append("li $v0, 4\n"); // Code du print pour le retour à la ligne
        string.append("la $a0, ln\n");  // Saut à la ligne
        string.append("syscall\n\n");   // Appel du saut de ligne
        return string.toString();
    }

}
