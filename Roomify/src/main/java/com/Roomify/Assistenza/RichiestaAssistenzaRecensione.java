package com.Roomify.Assistenza;

import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaRecensione extends RichiestaAssistenza {
    private Recensione recensione;

    public RichiestaAssistenzaRecensione(int id,String descrizione, Utente utente, Recensione recensione,String stato) {
        super(id,descrizione, utente,stato);
        this.recensione = recensione;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Richiesta Assistenza per Recensione");
        System.out.println("Descrizione: " + descrizione);
        System.out.println("Recensione ID: " + recensione.getId());
    }
}
