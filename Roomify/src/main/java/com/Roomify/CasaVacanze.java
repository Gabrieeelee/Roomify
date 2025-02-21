package com.Roomify;

import java.util.*;

public class CasaVacanze extends Struttura {

    private int nMaxOspiti;
    private int nVani;
    private float prezzoNotte;
    private int dimensione;
    private TariffaStagionale tf;
    private ArrayList<Servizio> listaServizi;
    private Map<Integer,Prenotazione> listaprenotazioni;



    public CasaVacanze(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo, int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Servizio> listaServizi,Host proprietario) {
        super(id, nome, descrizione, paese, citta, provincia, cap, indirizzo,proprietario);
        this.nMaxOspiti = nMaxOspiti;
        this.nVani = nVani;
        this.prezzoNotte = prezzoNotte;
        this.dimensione = dimensione;
        this.listaServizi = listaServizi;
        this.listaprenotazioni = new HashMap<>();
    }

    public int getnMaxOspiti() {
        return nMaxOspiti;
    }

    public int getnVani() {
        return nVani;
    }

    public float getPrezzoNotte() {
        return prezzoNotte;
    }

    public int getDimensione() {
        return dimensione;
    }

    public TariffaStagionale getTf() {
        return tf;
    }

    public ArrayList<Servizio> getListaServizi() {
        return listaServizi;
    }

    public Map<Integer, Prenotazione> getListaprenotazioni() {
        return listaprenotazioni;
    }

    public void inserisciTariffa(String nome, int periodo, float fattoreMoltiplicativo){
        this.tf = new TariffaStagionale(nome, periodo, fattoreMoltiplicativo);
        System.out.println("Inizializzati i parametri della tariffa stagionale!");

    }

//era per il pattern observe
/*    public void aggiorna(Prenotazione prenotazione) {
            if(prenotazione.getStruttu() instanceof CasaVacanze){
                if (prenotazione.getStruttu().getId() == this.getId()) {
                    listaprenotazioni.put(prenotazione.getId(), prenotazione);
                }
            }
        }
*/
    public int generaNumeroPrenotazione(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if (!listaprenotazioni.containsKey(randomNumber)){
                return randomNumber;
            }
        }
    }
}
