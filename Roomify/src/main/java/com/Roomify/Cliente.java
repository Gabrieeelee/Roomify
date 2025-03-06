package com.Roomify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends Utente  {

    private Map<Integer,Prenotazione> listaPrenotazioniClienti;
    private ArrayList<Recensione> listaRecensioni;

    public Cliente(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        super(id,nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        listaPrenotazioniClienti= new HashMap<>();
        listaRecensioni = new ArrayList<>();
    }

    public void addPrenotazione(Prenotazione pre){
        listaPrenotazioniClienti.put(pre.getId(), pre);
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

    public Map<Integer, Prenotazione> getListaPrenotazioniClientiAnnullabili(){
        Map<Integer, Prenotazione> map = new HashMap<>();
        for (Prenotazione pre : listaPrenotazioniClienti.values()){
            if(pre.isAnnullabile()){
                map.put(pre.getId(), pre);
            }
        }
        return map;
    }

    public Prenotazione getPrenotazione(int id){
        if (listaPrenotazioniClienti.get(id)!=null){
            return listaPrenotazioniClienti.get(id);
        }
        return null;
    }

    public Map<Integer,Struttura> nuovaRecensione(){
        Map<Integer,Struttura> listStruRecensibili= new HashMap<>();
        for (Prenotazione pre: listaPrenotazioniClienti.values()){
            boolean flag = false;
            if(pre.isRecensibile()!=null){
                if(listaRecensioni!=null)
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

    public Recensione inserisciRecensione(int id,int valutazione, String commento,Cliente cliente, Struttura st){
        Recensione re = new Recensione(id,valutazione, commento, cliente,st );
        re.setStato("Non convalidata");
        listaRecensioni.add(re);
        return  re;
    }

    public ArrayList<Recensione> getListaRecensioni() {
        return listaRecensioni;
    }



    //UC10
    public ArrayList<Prenotazione> getPrenotazioniAss(){
        ArrayList<Prenotazione> listaPrenAss = new ArrayList<>();
        for (Prenotazione pre : listaPrenotazioniClienti.values()){
            if (pre.isAssicurabile()){
                listaPrenAss.add(pre);
            }
        }

        return listaPrenAss;
    }

    public Prenotazione selezionaPrenotazione(int id){
        return listaPrenotazioniClienti.get(id);
    }

    public Recensione selRecensione(int idRec) {
        Recensione re = null;
        for(int i = 0; i < listaRecensioni.size(); i++){
            if (listaRecensioni.get(i).getId() == idRec){
                re = listaRecensioni.get(i);
            }
        }
        return re;
    }

    public Map<Integer,Prenotazione> getListaPrenotazioni(){
        return listaPrenotazioniClienti;
    }

    public Prenotazione getListaPrenotazioniClienti(int idPren){
        return listaPrenotazioniClienti.get(idPren);
    }

    public ArrayList<Recensione> getRecensioniCommentati(){
        ArrayList<Recensione> list = new ArrayList<>();
        for (int i = 0; i < listaRecensioni.size(); i++){
            if(listaRecensioni.get(i).isCommentato()){
                list.add(listaRecensioni.get(i));
            }
        }

        return list;
    }

    public String visualizzaRispostaHost(int id){
        for (int i = 0; i <listaRecensioni.size(); i++){
            if(listaRecensioni.get(i).getId() == id){
                return listaRecensioni.get(i).getCommentoHost();
            }
        }

        return null;
    }


}
