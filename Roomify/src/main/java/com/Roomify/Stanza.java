package com.Roomify;

import java.util.List;

public class Stanza {

    private String nome;
    private int nopsiti;
    private float prezzopernotte;
    private int dimensione;
    private String descrizione;
    private List<Servizio> listaservizi;

    public Stanza(String nome, int nopsiti, float prezzopernotte, int dimensione, String descrizione, List<Servizio> listaservizi) {
        this.nome = nome;
        this.nopsiti = nopsiti;
        this.prezzopernotte = prezzopernotte;
        this.dimensione = dimensione;
        this.descrizione = descrizione;
        this.listaservizi = listaservizi;
    }

    public String getNome() {
        return nome;
    }

    public int getNopsiti() {
        return nopsiti;
    }

    public float getPrezzopernotte() {
        return prezzopernotte;
    }

    public int getDimensione() {
        return dimensione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<Servizio> getListaservizi() {
        return listaservizi;
    }
}
