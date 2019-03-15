package yal.tds.symbole;

public class SymboleFonction extends Symbole {

    private int numBloc;

    public SymboleFonction(int num) {
        this.numBloc = num;
    }

    @Override
    public int getDeplacement() {
        return 0;
    }

    @Override
    public int getNumBloc() {
        return this.numBloc;
    }
}
