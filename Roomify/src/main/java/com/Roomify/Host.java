package com.Roomify;

import java.time.LocalDate;

public class Host extends Utente{
    private String piva;
    private String paesediresidenza;
    private String sedefiscale;

    public Host(String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono, String piva, String paesediresidenza, String sedefiscale) {
        super(nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        this.piva = piva;
        this.paesediresidenza = paesediresidenza;
        this.sedefiscale = sedefiscale;
    }
}
