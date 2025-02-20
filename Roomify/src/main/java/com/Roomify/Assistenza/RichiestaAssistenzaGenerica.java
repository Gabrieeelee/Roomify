package com.Roomify.Assistenza;

import com.Roomify.Utente;

public class RichiestaAssistenzaGenerica extends RichiestaAssistenza {
    public RichiestaAssistenzaGenerica(int id,String descrizione, Utente utente,String stato) {
        super(id,descrizione, utente,stato);
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Richiesta Assistenza Generica");
        System.out.println("Descrizione: " + descrizione);
    }
}
