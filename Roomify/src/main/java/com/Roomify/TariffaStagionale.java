package com.Roomify;

public class TariffaStagionale {
    private String nome;
    private int periodo;
    private float fattoreMoltiplicativo;

    public TariffaStagionale(String nome, int periodo, float fattoreMoltiplicativo) {
        this.nome = nome;
        this.periodo = periodo;
        this.fattoreMoltiplicativo = fattoreMoltiplicativo;
    }
}
