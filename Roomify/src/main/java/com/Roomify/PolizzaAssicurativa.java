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

    public String getStato() {
        return stato;
    }

    public PartnerAssicurativo getPartnerAssicurativo() {
        return partnerAssicurativo;
    }

    public boolean isOk(int durata) {
        if (durata <= this.durata && !stato.equals("Disattivato")){
            return true;
        }

        return false;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Polizza Assicurativa\n" +
                "|ID=" + id +
                "\n|Tipo='" + tipo + '\'' +
                "\n|Copertura='" + copertura + '\'' +
                "\n|Durata=" + durata + '\'';
    }
}
