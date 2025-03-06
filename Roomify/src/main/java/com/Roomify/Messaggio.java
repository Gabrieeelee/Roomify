package com.Roomify;

public class Messaggio {
    private int id;
    private String descrizione;

    public Messaggio(int id, String descrizione){
        this.id = id;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return "Messaggio: \n|ID: "+id+
                "\n|Descrizione: "+ descrizione;
    }
}
