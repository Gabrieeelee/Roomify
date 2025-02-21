package com.Roomify;

import java.time.LocalDate;

public class Prenotazione {

    private int id;
    private LocalDate datainizio;
    private LocalDate datafine;
    private String stato;

    Stanza st;

    Cliente cl;
    Struttura struttu;

    public Prenotazione() {
    }

    public Prenotazione(int id, LocalDate datainizio, LocalDate datafine, String stato, Struttura struttu, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.struttu = struttu;
        this.cl = cl;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDatainizio() {
        return datainizio;
    }

    public LocalDate getDatafine() {
        return datafine;
    }

    public String getStato() {
        return stato;
    }

    public Prenotazione(int id, LocalDate datainizio, LocalDate datafine, String stato, Stanza st, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.st = st;
        this.cl = cl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatainizio(LocalDate datainizio) {
        this.datainizio = datainizio;
    }

    public void setDatafine(LocalDate datafine) {
        this.datafine = datafine;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setSt(Stanza st) {
        this.st = st;
    }


    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    public void setStruttu(Struttura struttu) {
        this.struttu = struttu;
    }

    public Stanza getStanza(){
        return st;
    }

    public Stanza getSt() {
        return st;
    }

    public Cliente getCl() {
        return cl;
    }

    public Struttura getStruttu() {
        return struttu;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", datainizio=" + datainizio +
                ", datafine=" + datafine +
                ", stato='" + stato + '\'' +
                ", st=" + st +
                ", cl=" + cl +
                ", struttu=" + struttu +
                '}';
    }


    public Struttura isRecensibile(){
        if(LocalDate.now().isAfter(datafine)){
            return struttu;
        }
        else
            return null;
    }
}
