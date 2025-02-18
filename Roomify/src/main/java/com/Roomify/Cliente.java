package com.Roomify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends Utente {

    private Map<Integer,Prenotazione> listaPrenotazioniClienti;
    private ArrayList<Recensione> listaRecensioni;

    public Cliente(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        super(id,nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        listaPrenotazioniClienti= new HashMap<>();
        listaRecensioni = new ArrayList<>();
    }

    public void addPrenotazione(Prenotazione pre){
        listaPrenotazioniClienti.put(pre.getId(), pre);
        System.out.println("Inserito prenotazione: "+pre.toString());
    }

    public Map<Integer, Prenotazione> getListaPrenotazioniClienti(LocalDate dataInizio, LocalDate dataFine) {
        Map<Integer,Prenotazione> listaPrenotazioniPeriodo=new HashMap<>();
        for(Prenotazione pren: listaPrenotazioniClienti.values()){
            if(dataInizio.isBefore(pren.getDatainizio()) && dataFine.isAfter(pren.getDatafine())){
                listaPrenotazioniPeriodo.put(pren.getId(), pren);
            }
        }
        return listaPrenotazioniPeriodo;
    }

    public Map<Integer, Prenotazione> getListaPrenotazioniClienti(){
        return listaPrenotazioniClienti;
    }

    public void addRecensione(Recensione re){
        listaRecensioni.add(re);
    }

}
