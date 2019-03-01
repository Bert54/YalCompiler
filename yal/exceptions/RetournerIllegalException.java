package yal.exceptions;

public class RetournerIllegalException extends AnalyseSemantiqueException {

    public RetournerIllegalException(int ligne, String m) {
        super(ligne, m);
    }

}
