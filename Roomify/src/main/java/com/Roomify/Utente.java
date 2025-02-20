package com.Roomify;
import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
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

    public RichiestaAssistenza richiestaAssistenza(int idAss, String descrizione, String stato){

        RichiestaAssistenzaFactory factoryPrenotazione = RichiestaAssistenzaFactory.creaFactory(CategoriaAssistenza.ALTRI_MOTIVI);
        RichiestaAssistenza richiesta1 = factoryPrenotazione.creaRichiesta(idAss,descrizione, stato,this, null, null);
        listaAssistenza.put(richiesta1.getId(),richiesta1);
        richiestaAssistenzacorr=richiesta1;
        return richiesta1;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }


    public void confermaAssistenza(){
        listaAssistenza.put(richiestaAssistenzacorr.getId(), richiestaAssistenzacorr);
        richiestaAssistenzacorr = null;
    }

    public void setRichiestaAssistenzacorr(RichiestaAssistenza richiestaAssistenzacorr) {
        this.richiestaAssistenzacorr = richiestaAssistenzacorr;
    }
}
