package com.Roomify;

public class Prenotazione {

    private int id;
    private int datainizio;
    private int datafine;
    private String stato;

    Stanza st;
    CasaVacanze cv;
    Cliente cl;

    public Prenotazione(int id, int datainizio, int datafine, String stato, CasaVacanze cv, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.cv = cv;
        this.cl = cl;
    }

    public Prenotazione(int id, int datainizio, int datafine, String stato, Stanza st, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.st = st;
        this.cl = cl;
    }
}
