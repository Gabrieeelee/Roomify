package com.Roomify;

import java.util.ArrayList;
import java.util.List;

public class CasaVacanze extends Struttura {

    private int nMaxOspiti;
    private int nVani;
    private float prezzoNotte;
    private int dimensione;
    private TariffaStagionale tf;
    private ArrayList<Servizio> listaServizi;


    public CasaVacanze(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo, int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Servizio> listaServizi) {
        super(id, nome, descrizione, paese, citta, provincia, cap, indirizzo);
        this.nMaxOspiti = nMaxOspiti;
        this.nVani = nVani;
        this.prezzoNotte = prezzoNotte;
        this.dimensione = dimensione;

        this.listaServizi = listaServizi;
    }

    public void inserisciTariffa(String nome, int periodo, float fattoreMoltiplicativo){
        this.tf = new TariffaStagionale(nome, periodo, fattoreMoltiplicativo);
        System.out.println("Inizializzati i parametri della tariffa stagionale!");

    }


}
