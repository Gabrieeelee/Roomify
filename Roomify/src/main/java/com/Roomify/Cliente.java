package com.Roomify;

import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenzaFactory;
import com.Roomify.Assistenza.RichiestaAssistenzaPrenotazione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Cliente extends Utente  {

    private Map<Integer,Prenotazione> listaPrenotazioniClienti;
    private ArrayList<Recensione> listaRecensioni;
    private ArrayList<RichiestaAssistenza> listaAssistenza;

    public Cliente(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        super(id,nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        listaPrenotazioniClienti= new HashMap<>();
        listaRecensioni = new ArrayList<>();
    }

    public Map<Integer,Struttura> nuovaRecensione(){
        Map<Integer,Struttura> listStruRecensibili= new HashMap<>();
        for (Prenotazione pre: listaPrenotazioniClienti.values()){
            boolean flag = false;
            if(pre.isRecensibile()!=null){
                for(int i = 0; i < listaRecensioni.size(); i++){
                    //AGGIUNTO GETST a recensione
                    if (listaRecensioni.get(i).getSt().getId() == pre.getStruttu().getId()){
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    listStruRecensibili.put(pre.isRecensibile().getId(),pre.isRecensibile());
                }
            }


        }
        return listStruRecensibili;
    }

    public void addPrenotazione(Prenotazione pre){
        listaPrenotazioniClienti.put(pre.getId(), pre);
        System.out.println("Inserito prenotazione: "+pre.toString());
    }

    public Map<Integer, Prenotazione> getListaPrenotazioniClienti(LocalDate dataInizio, LocalDate dataFine) {
        Map<Integer,Prenotazione> listaPrenotazioniPeriodo=new HashMap<>();
        for(Prenotazione pren: listaPrenotazioniClienti.values()){
            if ((dataInizio.isBefore(pren.getDatainizio()) || dataInizio.isEqual(pren.getDatainizio())) &&
                    (dataFine.isAfter(pren.getDatafine()) || dataFine.isEqual(pren.getDatafine()))) {
                listaPrenotazioniPeriodo.put(pren.getId(), pren);
            }
        }
        return listaPrenotazioniPeriodo;
    }

    public Map<Integer, Prenotazione> getListaPrenotazioniClienti(){
        return listaPrenotazioniClienti;
    }


    public Recensione inserisciRecensione(int valutazione, String commento,Cliente cliente, Struttura st){
        Recensione re = new Recensione(listaRecensioni.size()+1,valutazione, commento, cliente,st );
        re.setStato("Non convalidata");
        listaRecensioni.add(re);
        return  re;
    }

    public ArrayList<Recensione> getListaRecensioni() {
        return listaRecensioni;
    }

    public RichiestaAssistenza richiestaAssistenzaPrenotazione(int idAss,String descrizione, int id,String stato){
        Map<Integer,Prenotazione> listapren=getListaPrenotazioniClienti();
        Prenotazione prenotazione=listapren.get(id);

        RichiestaAssistenzaFactory factoryPrenotazione = RichiestaAssistenzaFactory.creaFactory(CategoriaAssistenza.PRENOTAZIONE);
        RichiestaAssistenza richiesta1 = factoryPrenotazione.creaRichiesta(idAss,descrizione,stato, this, prenotazione, null);
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }

    public RichiestaAssistenza richiestaAssistenzaRecensione(int idAss,String descrizione, int id,String stato){
        ArrayList<Recensione> listarec=getListaRecensioni();
        Recensione rec=null;
        for(int i = 0; i < listarec.size(); i++){
            if(listarec.get(i).getId() == id){
                rec = listarec.get(i);
                break;
            }
        }

        RichiestaAssistenzaFactory factoryRecensione = RichiestaAssistenzaFactory.creaFactory(CategoriaAssistenza.RECENSIONE);
        RichiestaAssistenza richiesta1 = factoryRecensione.creaRichiesta(idAss,descrizione,stato, this, null, rec);
        //addListaAssistenza(richiesta1);
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }


}
