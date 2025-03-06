package com.Roomify;

public class Recensione {
    private int id;
    private int valutazione;
    private String commento;
    private Cliente cl;
    private Struttura st;
    private String stato;
    private String commentoHost = null;


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

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCommentoHost() {
        return commentoHost;
    }

    public int getId() {
        return id;
    }

    public Recensione isRisp(){
        if(this.stato!="Concluso")
            return this;
        else
            return null;
    }

    public Struttura getSt() {
        return st;
    }

    @Override
    public String toString() {
        return "Recensione:" +
                "\n|ID=" + id +
                "\n|Valutazione=" + valutazione +
                "\n|Commento='" + commento + '\'' +
                "\n|Cliente=" + cl.getCodicefiscale() +
                "\n|Struttura=" + st.getNome() +
                "\n|Stato='" + stato + '\'' +
                "\n|Commento Host='" + commentoHost ;
    }

    public boolean isCommentato() {
        if (commentoHost != null){
            return true;
        }else{
            return false;
        }
    }
}
