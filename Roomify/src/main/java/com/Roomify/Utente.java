package com.Roomify;
import java.time.LocalDate;

public class Utente {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String codicefiscale;
    private String email;
    private String telefono;

    public Utente(String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codicefiscale = codicefiscale;
        this.email = email;
        this.telefono = telefono;
    }


}
