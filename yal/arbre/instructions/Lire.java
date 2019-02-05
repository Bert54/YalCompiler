package yal.arbre.instructions;

import yal.arbre.expressions.Idf;

public class Lire extends Instruction {

    protected Idf idf ;

    /**
     * Constructeur d'une lecture de variable
     * @param n numero de ligne
     */
    public Lire(int n, Idf e) {
        super(n);
        this.idf = e;
    }

    @Override
    public void verifier() {
        this.idf.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("#Lecture\n");
        string.append("li $v0, 5\n");   // Code du read pour un entier
        string.append("syscall\n");     // appel système pour lire l'entier
        string.append("sw $v0, " + this.idf.getDeplacement() + "($s7)" + " #on empile la variable\n");    // on empile la variable
        string.append(this.sautDeLigne());
        return string.toString();
    }
}
