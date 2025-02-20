package com.Roomify.Assistenza;

import com.Roomify.Messaggio;
import com.Roomify.Utente;

import java.util.ArrayList;

public abstract class RichiestaAssistenza {
    protected int id;
    protected String descrizione;
    protected Utente utente;
    protected String stato;
    protected ArrayList<Messaggio> listaMessaggi;

    public RichiestaAssistenza(int id,String descrizione, Utente utente,String stato) {
        this.id = id;
        this.descrizione = descrizione;
        this.utente = utente;
        this.stato = stato;
        this.listaMessaggi = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void addMessaggio(Messaggio mess){
        listaMessaggi.add(mess);
    }
    public abstract void mostraDettagli();

    public ArrayList<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }


}
