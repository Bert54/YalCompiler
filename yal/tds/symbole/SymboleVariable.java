package yal.tds.symbole;

public class SymboleVariable extends Symbole {

    private int dep;

    public SymboleVariable(int d) {
        this.dep = d;
    }

    public int getDeplacement() {
        return this.dep;
    }

}
