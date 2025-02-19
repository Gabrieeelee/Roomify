package com.Roomify;
import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenzaFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utente {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String codicefiscale;
    private String email;
    private String telefono;
    private int id;

    public Utente(int id,String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.codicefiscale = codicefiscale;
        this.email = email;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public String getCodicefiscale() {
        return codicefiscale;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }
}
