package com.Roomify;

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
    public void inserisciStanza(String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        ArrayList<Servizio> listaserv = new ArrayList<>();
        if (listacodservizi.size() != 0){
            System.out.println("Non si sono selezionati i servizi");
            HashMap<Integer, Servizio> servizi = Roomify.getInstance().getServizi();
            for (int i = 0; i < listacodservizi.size(); i++) {
                listaserv.add(servizi.get(listacodservizi.get(i)));
               // System.out.println(servizi.get(listacodservizi.get(i)).getNome());
            }
        }
        listaStanze.put(nome, new Stanza(nome, nospiti, prezzopernotte, dimensione, descrizione, listaserv));
        System.out.println("Inserimento stanza: "+ nome +"-> completato!");
    }

    public Map<String, Stanza> getListaStanze() {
        return listaStanze;
    }

}


