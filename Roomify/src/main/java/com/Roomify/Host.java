package com.Roomify;

import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenzaCreator;
import com.Roomify.Assistenza.RichiestaAssistenzaFactory;

import java.time.LocalDate;
import java.util.*;

public class Host extends Utente{
    private String piva;
    private String paesediresidenza;
    private String sedefiscale;
    private Map<Integer,Struttura> listaStrutture;
    private Sottoscrizione sottoscrizione;
    private Map<Integer,Prenotazione> listPrenotazioneDisp;
    private ArrayList<Recensione> listaRecensioniDisp;

    public Host(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono, String piva, String paesediresidenza, String sedefiscale) {
        super(id,nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        this.piva = piva;
        this.paesediresidenza = paesediresidenza;
        this.sedefiscale = sedefiscale;
        this.listaStrutture=new HashMap<>();
        sottoscrizione=null;
    }

    public void addStruttura(Struttura struttura){
        listaStrutture.put(struttura.getId(),struttura);
    }

    public ArrayList<Recensione> visualizzaRecensioni(){
        ArrayList<Recensione> lis = new ArrayList<>();
        for (Struttura st : listaStrutture.values()){
            lis.addAll(st.getListRecensioni());
        }

        return lis;
    }

    public Map<Integer,Struttura> getListaStrutture() {
        return listaStrutture;
    }

    public void setListaStrutture(Map<Integer,Struttura> listaStrutture) {
        this.listaStrutture = listaStrutture;
    }

    public void setSottoscrizione(Abbonamento abbonamento) {
       sottoscrizione=new Sottoscrizione(abbonamento.getId(), abbonamento.getNome(), LocalDate.now(), LocalDate.now().plusMonths(abbonamento.getDurata()), abbonamento);
    }

    public Sottoscrizione getSottoscrizione(){
        return this.sottoscrizione;
    }

    public Map<Integer, Prenotazione> getListaPrenotazioni(){
        listPrenotazioneDisp = new HashMap<>();
        for (Struttura st : listaStrutture.values()){
            if (st instanceof CasaVacanze){
                listPrenotazioneDisp.putAll(((CasaVacanze)st).getListaprenotazioni());
            }else if (st instanceof Beb){
                Map<String, Stanza> mapStanze= ((Beb)st).getListaStanze();
                for (Stanza stn : mapStanze.values()){
                    listPrenotazioneDisp.putAll(stn.getListaprenotazioni());
                }

            }
        }
        return listPrenotazioneDisp;
    }

    public Prenotazione getListPrenotazioneDisp(int idPren) {
        return listPrenotazioneDisp.get(idPren);
    }

    public ArrayList<Recensione> getListaRecensioni(){
        listaRecensioniDisp = new ArrayList<>();
        for (Struttura st : listaStrutture.values()){
            listaRecensioniDisp.addAll(st.getListRecensioni());
        }
        return listaRecensioniDisp;
    }

    public Recensione selRecensione(int idRec) {
        Recensione re = null;
        for (int i = 0; i < listaRecensioniDisp.size(); i++){
            if (listaRecensioniDisp.get(i).getId() == idRec){
                re = listaRecensioniDisp.get(i);
            }
        }
        listaRecensioniDisp = null;
        return re;
    }

}
