package com.Roomify;

import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenzaFactory;

import java.time.LocalDate;
import java.util.*;

public class Host extends Utente{
    private String piva;
    private String paesediresidenza;
    private String sedefiscale;
    private Map<Integer,Struttura> listaStrutture;
    private Abbonamento abbonamento;
    private LocalDate inizioAbbonamento;
    private LocalDate fineAbbonamento;

    public Host(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono, String piva, String paesediresidenza, String sedefiscale) {
        super(id,nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        this.piva = piva;
        this.paesediresidenza = paesediresidenza;
        this.sedefiscale = sedefiscale;
        this.listaStrutture=new HashMap<>();
    }

    public Map<Integer,Struttura> getListaStrutture() {
        return listaStrutture;
    }

    public void setListaStrutture(Map<Integer,Struttura> listaStrutture) {
        this.listaStrutture = listaStrutture;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        inizioAbbonamento = LocalDate.now();
        this.abbonamento = abbonamento;
        fineAbbonamento = LocalDate.now().plusMonths(12);
    }

    public RichiestaAssistenza richiestaAssistenzaPrenotazione(int idAss,String descrizione, int id,String stato){
        Prenotazione pre=null;
        for(Struttura struttura : listaStrutture.values()){
            if(struttura instanceof  CasaVacanze){
                if(((CasaVacanze) struttura).getListaprenotazioni().get(id) != null){
                    pre = ((CasaVacanze) struttura).getListaprenotazioni().get(id);
                    break;
                }
            } else {
              Map<String, Stanza> stn =  ((Beb)struttura).getListaStanze();
              for(Stanza st : stn.values()){
                  if(st.getListaprenotazioni().get(id) != null){
                      pre = st.getListaprenotazioni().get(id);
                      break;
                  }
              }
            }
        }
        RichiestaAssistenzaFactory factoryPrenotazione = RichiestaAssistenzaFactory.creaFactory(CategoriaAssistenza.PRENOTAZIONE);
        Random random=new Random();

        RichiestaAssistenza richiesta1 = factoryPrenotazione.creaRichiesta(idAss,descrizione,stato, this, pre, null);
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }

    public RichiestaAssistenza richiestaAssistenzaRecensione(int idAss,String descrizione, int id,String stato){
        Recensione rec=null;
        for(Struttura struttura : listaStrutture.values()){
           ArrayList<Recensione>listRe =struttura.getListRecensioni();
           for (int i = 0; i < listRe.size(); i++){
               if(listRe.get(i).getId() == id){
                   rec = listRe.get(i);
               }
           }
        }
        RichiestaAssistenzaFactory factoryPrenotazione = RichiestaAssistenzaFactory.creaFactory(CategoriaAssistenza.RECENSIONE);
        RichiestaAssistenza richiesta1 = factoryPrenotazione.creaRichiesta(idAss,descrizione,stato, this, null, rec);
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }
}
