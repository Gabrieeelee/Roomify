package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaFactory extends RichiestaAssistenzaCreator{
    @Override
    public  RichiestaAssistenza creaRichiesta(CategoriaAssistenza categoria, int id, String descrizione, String stato, Utente utente, Prenotazione prenotazione, Recensione recensione) {
        switch (categoria) {
            case PRENOTAZIONE:
                if (prenotazione == null) {
                    throw new IllegalArgumentException("Prenotazione non può essere nulla per questa richiesta");
                }
                return new RichiestaAssistenzaPrenotazione(id, descrizione, utente, prenotazione, stato);

            case RECENSIONE:
                if (recensione == null) {
                    throw new IllegalArgumentException("Recensione non può essere nulla per questa richiesta");
                }
                return new RichiestaAssistenzaRecensione(id, descrizione, utente, recensione, stato);

            case ALTRI_MOTIVI:
                return new RichiestaAssistenzaGenerica(id, descrizione, utente, stato);

            default:
                throw new IllegalArgumentException("Categoria non valida");
        }
    }


}

