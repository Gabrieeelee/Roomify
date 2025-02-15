package com.Roomify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Beb extends Struttura{

    private Map<String,Stanza> listaStanze;


    public Beb(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo) {
        super(id, nome, descrizione, paese, citta, provincia, cap, indirizzo);
        this.listaStanze = new HashMap<>();
    }

    //Inserimento della stanza caso d'uso 1
    public Stanza inserisciStanza(int id,String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Servizio> listaServizi){
        Stanza st=new Stanza(id,nome, nospiti, prezzopernotte, dimensione, descrizione, listaServizi);
        listaStanze.put(nome, st);
        System.out.println("Inserimento stanza: "+ nome +"-> completato!");
        return st;
    }

    public Map<String, Stanza> getListaStanze() {
        return listaStanze;
    }

    public ArrayList<Stanza> isAvaible(LocalDate dataInizio, LocalDate dataFine, int nOspiti) {

        ArrayList<Stanza> listStanzeDisp = new ArrayList<>();
        Map<String, Stanza> stnBeb =  this.getListaStanze();
        for (Stanza stanza : stnBeb.values()) {
            Stanza sv = stanza.isAvailable(dataInizio, dataFine, nOspiti);
            if (sv != null) {
                listStanzeDisp.add(sv);
            }
        }
        return listStanzeDisp;
    }

}


