package com.Roomify.Assistenza;

import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaGenericaFactory extends RichiestaAssistenzaFactory {
    @Override
    public RichiestaAssistenza creaRichiesta(int id,String descrizione,String stato, Utente utente, Prenotazione prenotazione, Recensione recensione) {
        return new RichiestaAssistenzaGenerica(id,descrizione, utente,stato);
    }
}
