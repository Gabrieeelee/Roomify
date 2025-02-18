package com.Roomify;

public class Servizio {
    private int codice;
    private String nome;
    private String descrizione;

    public Servizio(int codice, String nome, String descrizione) {
        this.codice=codice;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

}
