package com.Roomify;

import java.time.LocalDate;

public class Cliente extends Utente {
    public Cliente(String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        super(nome, cognome, dataDiNascita, codicefiscale, email, telefono);
    }
}
