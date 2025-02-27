package com.Roomify;

public class PolizzaAssicurativa {
    private int id;
    private String tipo;
    private String copertura;
    private int durata;
    private String stato;

    public PolizzaAssicurativa(int id, String tipo, String copertura, int durata, String stato) {
        this.id = id;
        this.tipo = tipo;
        this.copertura = copertura;
        this.durata = durata;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCopertura() {
        return copertura;
    }

    public int getDurata() {
        return durata;
    }

    public String getStato() {
        return stato;
    }
}
