package com.Roomify;
import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenzaCreator;
import com.Roomify.Assistenza.RichiestaAssistenzaFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utente {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String codicefiscale;
    private String email;
    private String telefono;
    private int id;
    private Map<Integer,RichiestaAssistenza> listaAssistenza;
    private RichiestaAssistenza richiestaAssistenzacorr;

    public Utente(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codicefiscale = codicefiscale;
        this.email = email;
        this.telefono = telefono;
        this.listaAssistenza=new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Integer, RichiestaAssistenza> getListaAssistenza() {
        return listaAssistenza;
    }

    public String getNome() {
        return nome;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public RichiestaAssistenza richiestaAssistenza(int idAss, String descrizione, String stato){
        RichiestaAssistenzaCreator creator = new RichiestaAssistenzaFactory();
        RichiestaAssistenza richiesta1 = creator.creaRichiesta(CategoriaAssistenza.ALTRI_MOTIVI,idAss,descrizione, stato,this, null, null);
        richiestaAssistenzacorr=richiesta1;
        return richiesta1;
    }

    public RichiestaAssistenza richiestaAssistenzaPrenotazione(int idAss,String descrizione,Prenotazione prenotazione,String stato){
        RichiestaAssistenzaCreator creator = new RichiestaAssistenzaFactory();
        RichiestaAssistenza richiesta1 = creator.creaRichiesta(CategoriaAssistenza.PRENOTAZIONE,idAss,descrizione,stato,this,prenotazione,null );
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }

    public RichiestaAssistenza richiestaAssistenzaRecensione(int idAss,String descrizione, Recensione recensione,String stato){
        RichiestaAssistenzaCreator creator = new RichiestaAssistenzaFactory();
        RichiestaAssistenza richiesta1 = creator.creaRichiesta(CategoriaAssistenza.RECENSIONE,idAss,descrizione,stato, this, null, recensione);
        setRichiestaAssistenzacorr(richiesta1);
        return richiesta1;
    }


    public void confermaAssistenza(){
        listaAssistenza.put(richiestaAssistenzacorr.getId(), richiestaAssistenzacorr);
        richiestaAssistenzacorr = null;
    }

    public void setRichiestaAssistenzacorr(RichiestaAssistenza richiestaAssistenzacorr) {
        this.richiestaAssistenzacorr = richiestaAssistenzacorr;
    }

    @Override
    public String toString() {
        return "Utente:" +
                "\n|ID=" + id +
                "\n|Nome='" + nome + '\'' +
                "\n|Cognome='" + cognome + '\'' +
                "\n|Data Di Nascita=" + dataDiNascita +
                "\n|Codice fiscale='" + codicefiscale + '\'' +
                "\n|Email='" + email + '\'' +
                "\n|Telefono='" + telefono + '\'' ;
    }
}
