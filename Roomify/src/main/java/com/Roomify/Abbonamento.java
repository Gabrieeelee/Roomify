package com.Roomify;

import java.time.LocalDate;

public class Abbonamento {
    int id;
    private String nome;
    private int durata;
    private float costo;

    public Abbonamento(int id,String nome, int durata,float costo){
        this.id = id;
        this.nome = nome;
        this.durata = durata;
        this.costo = costo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getNome() {
        return nome;
    }

    public int getDurata() {
        return durata;
    }

    public float getCosto() {
        return costo;
    }

    public int getId() {
        return id;
    }
}
