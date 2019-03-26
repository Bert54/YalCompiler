package yal.exceptions;

public class DivisionParZeroException extends AnalyseSemantiqueException  {

    public DivisionParZeroException(int ligne, String m) {
        super(ligne, m);

    }
}
