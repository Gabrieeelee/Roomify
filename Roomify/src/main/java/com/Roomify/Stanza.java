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
        if (this.nospiti < nOspiti) {
            return null;
        }

        if (listaprenotazioni.isEmpty()) {
            return this;
        }
        for (Prenotazione pren : listaprenotazioni.values()) {
            LocalDate prenInizio = pren.getDatainizio();
            LocalDate prenFine = pren.getDatafine();

            if (!(dataFine.isBefore(prenInizio) || dataInizio.isAfter(prenFine))) {
                if(pren.getStato().equals("Prenotazione annullata"))
                    return this;
                return null;
            }
        }

        return this;
    }

    public void setInfo(String nome, String descrizione, ArrayList<Servizio>listaserv, float prezzopernotte) {
        if (nome != null)
            this.nome = nome;
        if (descrizione != null)
            this.descrizione = descrizione;
        if (listaserv != null) {
            if (!listaserv.isEmpty())
                this.listaservizi.addAll(listaserv);
        }
        if(prezzopernotte != 0){
            this.prezzopernotte = prezzopernotte;
        }
    }

    public String toStrings() {
        return "Stanza:" +
                "\n|ID=" + id +
                "\n|Nome='" + nome + '\'' +
                "\n|Numero ospiti=" + nospiti +
                "\n|Prezzopernotte=" + prezzopernotte +
                "\n|Dimensione=" + dimensione +
                "\n|Descrizione='" + descrizione + '\'' +
                "\n|B&B=" + beb.getNome();
    }
}
