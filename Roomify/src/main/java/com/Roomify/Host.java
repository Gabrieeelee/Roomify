package com.Roomify;

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

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }
}
