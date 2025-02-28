package com.Roomify;

public class PolizzaAssicurativa {
    private int id;
    private String tipo;
    private String copertura;
    private int durata;
    private String stato;
    private PartnerAssicurativo partnerAssicurativo;

    public PolizzaAssicurativa(int id, String tipo, String copertura, int durata, String stato, PartnerAssicurativo partnerAssicurativo) {
        this.id = id;
        this.tipo = tipo;
        this.copertura = copertura;
        this.durata = durata;
        this.stato = stato;
        this.partnerAssicurativo = partnerAssicurativo;
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

    public PartnerAssicurativo getPartnerAssicurativo() {
        return partnerAssicurativo;
    }

    public boolean isOk(int durata) {
        if (durata <= this.durata){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "PolizzaAssicurativa{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", copertura='" + copertura + '\'' +
                ", durata=" + durata +
                ", stato='" + stato + '\'' +
                ", partnerAssicurativo=" + partnerAssicurativo +
                '}';
    }
}
