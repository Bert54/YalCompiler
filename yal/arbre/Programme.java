package yal.arbre;

import yal.tds.TDS;

public class Programme extends ArbreAbstrait {

    private BlocDInstructions blocDInstructions;

    /**
     * Constructeur du programme principal
     * @param n numero de ligne
     * @param bi bloc d'instruction du main
     */
    public Programme(int n, ArbreAbstrait bi){
        super(n);
        this.blocDInstructions = (BlocDInstructions)bi;
    }

    @Override
    public void verifier() {
        TDS.getInstance().entreeBlocVerifier(0); // 0 car main
        blocDInstructions.verifier();
        TDS.getInstance().sortieBlocVerifier();
    }

    @Override
    public String toMIPS() {
        return blocDInstructions.toMIPS();
    }
}
