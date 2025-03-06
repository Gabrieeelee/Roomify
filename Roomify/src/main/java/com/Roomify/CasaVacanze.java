package com.Roomify;

import java.time.LocalDate;
import java.util.*;

public class CasaVacanze extends Struttura {

    private int nMaxOspiti;
    private int nVani;
    private float prezzoNotte;
    private int dimensione;
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

    public float getPrezzoNotte() {
        return prezzoNotte;
    }

    public ArrayList<Servizio> getListaServizi() {
        return listaServizi;
    }

    public Map<Integer, Prenotazione> getListaprenotazioni() {
        return listaprenotazioni;
    }

    public boolean isAvailable(LocalDate dataInizio, LocalDate dataFine, int nOspiti){
        if (this.getnMaxOspiti() >= nOspiti) {
            if(!listaprenotazioni.isEmpty()){
                for (Prenotazione pren : listaprenotazioni.values()) {
                    if (((dataInizio.isAfter(pren.getDatainizio()) || dataInizio.isEqual(pren.getDatainizio())) && (dataInizio.isBefore(pren.getDatafine())))|| dataFine.isEqual(pren.getDatafine())) {
                        return false;
                    }
                }
            }else  return true;
        }else {
            return false;
        }
        return true;
    }

    public int generaNumeroPrenotazione(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if (!listaprenotazioni.containsKey(randomNumber)){
                return randomNumber;
            }
        }
    }

    public void aggiornaStruttura(String nome, String descrizione, ArrayList<Servizio> listserv, float prezzoNotte){
        if(nome!=null)
        setNome(nome);

        if(descrizione!=null)
        setDescrizione(descrizione);

        if(listserv!=null){
            if (!listserv.isEmpty()){
                listaServizi.addAll(listserv);
            }
        }
        if(prezzoNotte != 0){
            this.prezzoNotte = prezzoNotte;
        }
    }


    public void addPrenotazione(Prenotazione pren){
        listaprenotazioni.put(pren.getId(),pren);
    }




}
