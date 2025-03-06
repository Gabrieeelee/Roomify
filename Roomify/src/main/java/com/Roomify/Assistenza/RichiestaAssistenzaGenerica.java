package com.Roomify.Assistenza;

import com.Roomify.Utente;

public class    RichiestaAssistenzaGenerica extends RichiestaAssistenza {
    public RichiestaAssistenzaGenerica(int id,String descrizione, Utente utente,String stato) {
        super(id,descrizione, utente,stato);
    }

    @Override
    public void mostraDettagli() {
        System.out.print("\nRichiesta Assistenza generica");
        System.out.print("\n|ID" + id);
        System.out.print("\n|Descrizione: " + descrizione);
        System.out.print("\n|Stato: "+ stato);
    }
}
