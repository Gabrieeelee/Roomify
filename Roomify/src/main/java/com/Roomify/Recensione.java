package com.Roomify;

public class Recensione {
    private int valutazione;
    private String commento;
    private Cliente cl;
    private Struttura st;


    public Recensione(int valutazione, String commento, Cliente cl, Struttura st) {
        this.valutazione = valutazione;
        this.commento = commento;
        this.cl = cl;
        this.st = st;
    }
}
