package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaPrenotazioneFactory extends RichiestaAssistenzaFactory{
    @Override
    public RichiestaAssistenza creaRichiesta(int id,String descrizione,String stato, Utente utente, Prenotazione prenotazione, Recensione recensione) {
        if (prenotazione == null) {
            throw new IllegalArgumentException("Prenotazione non può essere nulla per questa richiesta");
        }
        return new RichiestaAssistenzaPrenotazione(id,descrizione, utente, prenotazione,stato);
    }
}
