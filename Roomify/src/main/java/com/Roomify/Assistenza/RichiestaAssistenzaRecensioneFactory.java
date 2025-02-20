package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaRecensioneFactory extends RichiestaAssistenzaFactory{
    @Override
    public RichiestaAssistenza creaRichiesta(int id,String descrizione,String stato, Utente utente, Prenotazione prenotazione, Recensione recensione) {
        if (recensione == null) {
            throw new IllegalArgumentException("Recensione non può essere nulla per questa richiesta");
        }
        return new RichiestaAssistenzaRecensione(id,descrizione, utente, recensione,stato);
    }
}
