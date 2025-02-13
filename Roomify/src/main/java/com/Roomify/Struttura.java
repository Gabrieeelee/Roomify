package com.Roomify;

public abstract class Struttura {
    private int id;
    private String nome;
    private String descrizione;
    private String paese;
    private String citta;
    private String provincia;
    private int  cap;
    private String indirizzo;

    public Struttura(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.paese = paese;
        this.citta = citta;
        this.provincia = provincia;
        this.cap = cap;
        this.indirizzo = indirizzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }


}
