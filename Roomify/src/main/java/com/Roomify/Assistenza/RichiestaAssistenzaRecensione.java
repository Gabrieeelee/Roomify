package com.Roomify.Assistenza;

import com.Roomify.Recensione;
import com.Roomify.Utente;

public class RichiestaAssistenzaRecensione extends RichiestaAssistenza {
    private Recensione recensione;

    public RichiestaAssistenzaRecensione(int id,String descrizione, Utente utente, Recensione recensione,String stato) {
        super(id,descrizione, utente,stato);
        this.recensione = recensione;
    }

    @Override
    public void mostraDettagli() {
        System.out.print("\nRichiesta Assistenza per Recensione");
        System.out.print("\n|ID:" + id);
        System.out.print("\n|Descrizione: " + descrizione);
        System.out.print("\n|Stato: "+ stato);
        System.out.print("\n|Recensione ID: " + recensione.getId()+"\n");
    }
}
