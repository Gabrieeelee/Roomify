package com.Roomify;

import java.time.LocalDate;

public class Sottoscrizione {
    private int id;
    private String nome;
    private LocalDate datainizio;
    private LocalDate datafine;
    private Abbonamento abbonamento;

    public Sottoscrizione(int id, String nome,LocalDate datainizio, LocalDate datafine, Abbonamento abbonamento) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.abbonamento = abbonamento;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome(){
        return this.nome;
    }
}
