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
        System.out.print("\nRichiesta Assistenza per Prenotazione");
        System.out.print("\n|ID" + id);
        System.out.print("\n|Descrizione: " + descrizione);
        System.out.print("\n|Stato: "+ stato);
        System.out.print("\n|Prenotazione ID: " + prenotazione.getId()+"\n");
    }
}
