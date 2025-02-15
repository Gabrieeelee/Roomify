package com.Roomify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public abstract class Struttura {
    private int id;
    private String nome;
    private String descrizione;
    private String paese;
    private String citta;
    private String provincia;
    private int cap;
    private String indirizzo;

    public Struttura(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.paese = paese;
        this.citta = citta;
        this.provincia = provincia;
        this.cap = cap;
        this.indirizzo = indirizzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {

        this.indirizzo = indirizzo;
    }


  /*  public ArrayList<Stanza> isAvaible(LocalDate dataInizio, LocalDate dataFine, int nOspiti) {

            ArrayList<Stanza> listStanzeDisp = new ArrayList<>();
            Map<String, Stanza> stnBeb = ((Beb) this).getListaStanze();
            for (Stanza stanza : stnBeb.values()) {
                Stanza sv = stanza.isAvailable(dataInizio, dataFine, nOspiti);
                if (sv != null) {
                    listStanzeDisp.add(sv);
                }
            }
            return listStanzeDisp;
    }*/



        //ho sostituito str con this versione fatta con enrico
 /*   public Struttura isAvaible(LocalDate dataInizio, LocalDate dataFine, int nOspiti, Struttura str){
        Struttura strutDisp = null;
            if (this instanceof Beb){
                 ArrayList<Stanza> listStanzeDisp = new ArrayList<>();
                 Map<String, Stanza> stnBeb=  ((Beb) this).getListaStanze();
                 for(Stanza stanza : stnBeb.values()){
                     Stanza sv =  stanza.isAvailable(dataInizio, dataFine, nOspiti);
                     if (sv != null){
                         strutDisp = this;
                         break;
                     }
                 }
            }else{
                CasaVacanze cv = (CasaVacanze) this;
                if (cv.getnMaxOspiti()>= nOspiti){
                    for(Prenotazione pren : ((CasaVacanze) this).getListaprenotazioni().values()){
                        if(!(dataInizio.isAfter(pren.getDatainizio())  && dataInizio.isBefore(pren.getDatafine()))){
                            strutDisp = this;
                         }
                     }
                 }
             }
        System.out.println("Lista strutture disponibili: "+strutDisp);
        return strutDisp;

         }

*/


}

/* public void isAvaible(LocalDate dataInizio, LocalDate dataFine, int nOspiti, Struttura str){
             ArrayList<Struttura> listStrutDisp = new ArrayList<>();
             if (str instanceof Beb){
                 ArrayList<Stanza> listStanzeDisp = new ArrayList<>();
                 Map<String, Stanza> stnBeb=  ((Beb) str).getListaStanze();
                 for(Stanza stanza : stnBeb.values()){
                     Stanza sv =  stanza.isAvailable(dataInizio, dataFine, nOspiti);
                     if (sv != null){
                         listStanzeDisp.add(sv);
                     }
                 }
                 if(listStanzeDisp.size() != 0){
                     listStrutDisp.add(this);
                 }
             }else{
                 CasaVacanze cv = (CasaVacanze) str;
                 if (cv.getnMaxOspiti()>= nOspiti){
                     Map<Integer,Prenotazione> pre = Roomify.getInstance().getListaprenotazioni();
                     for(Prenotazione pren : pre.values()){
                         if(!(dataInizio.isAfter(pren.getDatainizio())  && dataInizio.isBefore(pren.getDatafine()))){
                             listStrutDisp.add(this);
                         }
                     }
                 }
             }

         }*/