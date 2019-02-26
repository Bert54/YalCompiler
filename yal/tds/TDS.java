package yal.tds;

public class TDS {

    private static TDS instance = new TDS();

    public static TDS getInstance() {
        return instance;
    }

    private int compteurBloc;
    private TableLocale tableLocaleCourante;
    private final TableLocale racine;

    /**
     * Constructeur
     */
    private TDS() {
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


    public void entrerBloc(){
        compteurBloc++;
        TableLocale tableLocale = new TableLocale(compteurBloc, this.tableLocaleCourante);
        tableLocaleCourante.ajouterFille(tableLocale);
        tableLocaleCourante = tableLocale;
    }

    public void entrerBlocVerifier() {

    }

    public void sortieBlocVerifier() {

    }

}
