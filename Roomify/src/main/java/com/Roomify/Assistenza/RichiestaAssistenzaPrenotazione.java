package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Utente;

public class RichiestaAssistenzaPrenotazione extends RichiestaAssistenza{
    private Prenotazione prenotazione;

    public RichiestaAssistenzaPrenotazione(int id,String descrizione, Utente utente, Prenotazione prenotazione,String stato) {
        super(id,descrizione, utente,stato);
        this.prenotazione = prenotazione;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Richiesta Assistenza per Prenotazione");
        System.out.println("Descrizione: " + descrizione);
        System.out.println("Prenotazione ID: " + prenotazione.getId());
    }
}
