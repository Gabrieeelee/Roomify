package com.Roomify;

import java.time.LocalDate;
import java.util.*;

public class Stanza  {

    private int id;
    private String nome;
    private int nospiti;
    private float prezzopernotte;
    private int dimensione;
    private String descrizione;
    private ArrayList<Servizio> listaservizi;
    private Map<Integer,Prenotazione> listaprenotazioni;
    private Beb beb;

    public Stanza(int id,String nome, int nospiti, float prezzopernotte, int dimensione, String descrizione, ArrayList<Servizio> listaservizi, Beb beb) {
        this.id = id;
        this.nome = nome;
        this.nospiti = nospiti;
        this.prezzopernotte = prezzopernotte;
        this.dimensione = dimensione;
        this.descrizione = descrizione;
        this.listaservizi = listaservizi;
        this.listaprenotazioni = new HashMap<>();
        this.beb = beb;
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
                            return this;
                        }
                        else {
                           return null;
                        }
                    }
                }
            }else{
                return this;
            }
        }
        return null;
    }

    public String toStrings() {
        return "Stanza{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nospiti=" + nospiti +
                ", prezzopernotte=" + prezzopernotte +
                ", dimensione=" + dimensione +
                ", descrizione='" + descrizione + '\'' +
                ", listaservizi=" + listaservizi +
                ", listaprenotazioni=" + listaprenotazioni +
                ", beb=" + beb +
                '}';
    }
}
