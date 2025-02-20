package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Utente;

public abstract class RichiestaAssistenzaFactory {
    public abstract RichiestaAssistenza creaRichiesta(int id,String descrizione,String stato, Utente utente, Prenotazione prenotazione, Recensione recensione);

    public static RichiestaAssistenzaFactory creaFactory(CategoriaAssistenza categoria) {
        switch (categoria) {
            case PRENOTAZIONE:
                return new RichiestaAssistenzaPrenotazioneFactory();
            case RECENSIONE:
                return new RichiestaAssistenzaRecensioneFactory();
            case ALTRI_MOTIVI:
                return new RichiestaAssistenzaGenericaFactory();
            default:
                throw new IllegalArgumentException("Categoria non valida");
        }
    }
}
