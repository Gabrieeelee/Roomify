package com.Roomify;

import java.time.LocalDate;

public class TariffaStagionale {
    private String nome;
    private LocalDate datainizio;
    private LocalDate datafine;
    private float fattoreMoltiplicativo;

    public TariffaStagionale(String nome, LocalDate datainizio,LocalDate datafine, float fattoreMoltiplicativo) {
        this.nome = nome;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.fattoreMoltiplicativo = fattoreMoltiplicativo;
    }
}
