package com.Roomify;

public class Recensione {
    private int id;
    private int valutazione;
    private String commento;
    private Cliente cl;
    private Struttura st;
    private String stato;
    private String commentoHost;


    public Recensione(int id,int valutazione, String commento, Cliente cl, Struttura st) {
        this.id = id;
        this.valutazione = valutazione;
        this.commento = commento;
        this.cl = cl;
        this.st = st;
    }

    public String getStato(){
        return stato;
    }

    public void setCommentoHost(String commentoHost){
        this.commentoHost = commentoHost;
    }

    public void setRecensioneConclusa(){
        this.stato = "Concluso";
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCommentoHost() {
        return commentoHost;
    }

    public int getId() {
        return id;
    }
}
