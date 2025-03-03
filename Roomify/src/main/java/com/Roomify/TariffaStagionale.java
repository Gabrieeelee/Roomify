package com.Roomify;

import java.time.LocalDate;

public class TariffaStagionale {
    private String nome;
    private int meseinizio;
    private int mesefine;
    private float fattoreMoltiplicativo;

    public TariffaStagionale(String nome, int meseinizio,int mesefine, float fattoreMoltiplicativo) {
        this.nome = nome;
        this.meseinizio = meseinizio;
        this.mesefine = mesefine;
        this.fattoreMoltiplicativo = fattoreMoltiplicativo;
    }

    public float getFattoreMoltiplicativo() {
        return fattoreMoltiplicativo;
    }

    public int getmesefine() {
        return mesefine;
    }

    public int getmeseinizio() {
        return meseinizio;
    }

    public String getNome() {
        return nome;
    }
}
