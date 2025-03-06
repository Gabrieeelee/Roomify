package com.Roomify.Assistenza;

import com.Roomify.*;

public abstract class RichiestaAssistenzaCreator {
    public abstract RichiestaAssistenza creaRichiesta(CategoriaAssistenza categoria, int id, String descrizione, String stato, Utente utente, Prenotazione prenotazione, Recensione recensione);
}
