package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.FonctionNonDeclareeException;
import yal.tds.entree.Entree;
import yal.tds.entree.EntreeFonction;
import yal.tds.symbole.Symbole;

import java.util.HashMap;

public class TDS {

    private static TDS instance = new TDS();

    public static TDS getInstance() {
        return instance;
    }

    private HashMap<Entree, Symbole> table;
    private int compteurBloc;
    private TableLocale tableLocaleCourante;
    private final TableLocale racine;

    /**
     * Constructeur
     */
    private TDS() {
        this.table = new HashMap<>();
        this.compteurBloc = 0;
        this.racine = new TableLocale(compteurBloc, null);
        this.tableLocaleCourante = this.racine;
    }

    public void sortieBloc() {
        this.tableLocaleCourante = this.tableLocaleCourante.getTableLocalPere();
    }


    public TableLocale getTableLocaleCourante() {
        return this.tableLocaleCourante;
    }


    public void entreeBloc(){
        compteurBloc++;
        TableLocale tableLocale = new TableLocale(compteurBloc, this.tableLocaleCourante);
        tableLocaleCourante.ajouterFille(tableLocale);
        tableLocaleCourante = tableLocale;
    }

    public void entreeBlocVerifier(int numBloc) {
        TableLocale table = null;
        for (TableLocale tl: this.tableLocaleCourante.getFilles()) {
            if (tl.getNumBloc() == numBloc) {
                table = tl;
            }
        }
        this.tableLocaleCourante = table;
    }

    public void sortieBlocVerifier() {
        this.tableLocaleCourante = this.tableLocaleCourante.getTableLocalPere();
    }

    public void ajouter(Entree e, Symbole s) {
        int typeE = 0;
        int typeEn = 0;
        if (e instanceof EntreeFonction) {
            typeE = 1;
        }
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeFonction) {
                typeEn = 1;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom())) {
                throw new DoubleDeclarationException("Fonction déjà déclarée : " + e.getNom());
            }
        }
        this.table.put(e, s);
    }

    public Symbole identifier(Entree e) {
        int typeE = 0;
        int typeEn = 0;
        if (e instanceof EntreeFonction) {
            typeE = 1;
        }
        for (Entree en : this.table.keySet()) {
            if (en instanceof EntreeFonction) {
                typeEn = 1;
            }
            if (typeE == typeEn && en.getNom().equals(e.getNom()) && en.getNbParams() == e.getNbParams()) {
                return this.table.get(en);
            }
        }
        throw new FonctionNonDeclareeException(e.getLigne(), "Fonction non déclarée : " + e.getNom());
    }

}
