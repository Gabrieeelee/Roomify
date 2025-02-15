package com.Roomify;

import java.time.LocalDate;
import java.util.*;

public class Stanza implements PrenotazioniObserve {

    private int id;
    private String nome;
    private int nospiti;
    private float prezzopernotte;
    private int dimensione;
    private String descrizione;
    private ArrayList<Servizio> listaservizi;
    private Map<Integer,Prenotazione> listaprenotazioni;

    public Stanza(int id,String nome, int nospiti, float prezzopernotte, int dimensione, String descrizione, ArrayList<Servizio> listaservizi) {
        this.id = id;
        this.nome = nome;
        this.nospiti = nospiti;
        this.prezzopernotte = prezzopernotte;
        this.dimensione = dimensione;
        this.descrizione = descrizione;
        this.listaservizi = listaservizi;
        this.listaprenotazioni = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public int getNopsiti() {
        return nospiti;
    }

    public float getPrezzopernotte() {
        return prezzopernotte;
    }

    public int getDimensione() {
        return dimensione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<Servizio> getListaservizi() {
        return listaservizi;
    }

    public int getId() {
        return id;
    }

    public int getNospiti() {
        return nospiti;
    }

    public Map<Integer, Prenotazione> getListaprenotazioni() {
        return listaprenotazioni;
    }


    public void aggiorna(Map<Integer, Prenotazione> listaprenotazioni) {
        Map<Integer,Prenotazione> listaaggiornata=new HashMap<>();
        for(Prenotazione pren: listaprenotazioni.values()){
            if(pren.getStanza() == this ){
                listaaggiornata.put(pren.getId(), pren);
            }
        }
        this.listaprenotazioni = new HashMap<>(listaaggiornata);
       /* System.out.println("lista aggiornata contenente solo le prenotazioni di stanza "+ this.id);
        for(Prenotazione pren: listaaggiornata.values()){
            System.out.println("Prenotazione id "+pren.getId()+ "riguardante la stanza "+pren.getStanza().getId());
        }

        System.out.println("Lista prenotazioni aggiornata in Stanza.");
        for(Prenotazione pren: listaprenotazioni.values()){
           // System.out.println("Prenotazione per stanza "+this.id+" "+this.nome+"con id: "+pren.getId()+" riguardante la stanza "+ pren.getStanza().getId());
        }*/
    }

    public void addPrenotazione(Prenotazione pre){
        listaprenotazioni.put(pre.getId(),pre);
    }

    public Stanza isAvailable(LocalDate dataInizio, LocalDate dataFine, int nOspiti){
        int flag=0;
        if (this.nospiti >= nOspiti){
            if(!listaprenotazioni.isEmpty()) {
                for (Prenotazione pren : listaprenotazioni.values()) {
                    if (pren.getStanza().getId() == this.getId()) {
                        if (dataFine.isBefore(pren.getDatainizio()) || dataInizio.isAfter(pren.getDatafine()) || !(dataInizio.isEqual(pren.getDatainizio())))
                        {
                            flag=1;
                            System.out.println("la stanza è libera per la data "+dataInizio+" " + this);

                        }
                        else {
                            flag=0;
                            System.out.println("la stanza è occupata per la data "+dataInizio);
                            break;
                        }
                    }
                }
                if(flag==1) return this;
                else return null;
            }else{
                return this;
            }
        }
        return null;
    }


}
