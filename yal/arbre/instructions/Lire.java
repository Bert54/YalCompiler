package yal.arbre.instructions;

public class Lire extends Instruction {

    /**
     * Constructeur d'une lecture de variable
     * @param n numero de ligne
     */
    public Lire(int n) {
        super(n);
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        StringBuilder string = new StringBuilder("");
        string.append("li $v0, 5\n");   // Code du read pour un entier
        string.append("syscall\n");
        string.append(this.sautDeLigne());
        return string.toString();
    }
}
