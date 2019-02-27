package yal.exceptions;

public class RetournerManquantException extends AnalyseSemantiqueException {

    public RetournerManquantException(int ligne, String m) {
        super(ligne, m);
    }

}
