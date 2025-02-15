package com.Roomify;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends Utente {

    private Map<Integer,Prenotazione> listaPrenotazioniClienti;

    public Cliente(String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        super(nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        listaPrenotazioniClienti= new HashMap<>();
    }

    public void addPrenotazione(Prenotazione pre){
        listaPrenotazioniClienti.put(pre.getId(), pre);
        System.out.println("Inserito prenotazione: "+pre.toString());

    }
}
