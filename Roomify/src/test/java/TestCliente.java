import com.Roomify.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCliente {
    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;
    private Prenotazione pre;

    @Before
    public void initMet() {
        ho = new Host(2, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(), ho);
        cl = new Cliente(1, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
    }

    @Test
    public void getListaPrenotazioniClienti() {
        assertEquals(1, cl.getListaPrenotazioniClienti(LocalDate.of(2020, 01, 01), LocalDate.of(2026, 01, 01)).size());
    }

    @Test
    public void getListaPrenotazioniClientiAnnullabili() {
        assertEquals(0, cl.getListaPrenotazioniClientiAnnullabili().size());
    }

    @Test
    public void nuovaRecensione() {
        assertEquals(1, cl.nuovaRecensione().size());
    }

    @Test
    public void inserisciRecensione() {
        cl.inserisciRecensione(0, 4, "Commento test", cl, cv);
        assertEquals(0, cl.nuovaRecensione().size());
    }

    @Test
    public void getPrenotazioniAss() {
        cl.addPrenotazione(new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl));
        assertEquals(1, cl.getPrenotazioniAss().size());
    }




    @Test
    public void getListaPrenotazioniClientiAnnullabilTest(){
        cl.addPrenotazione(new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl));
        assertEquals(1, cl.getListaPrenotazioniClientiAnnullabili().size());
    }


    @Test
    public void getRecensioniCommentati(){
        cl.addPrenotazione(new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl));
        Recensione re =cl.inserisciRecensione(99, 5, "Test recensione", cl, cv);
        cv.addRecensione(re);
        cv.inserisciCommentoHost("Test Host", 99);
        assertEquals(1, cl.getRecensioniCommentati().size());

    }

    @Test
    public void visualizzaRispostaHost(){
        cl.addPrenotazione(new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl));
        Recensione re =cl.inserisciRecensione(99, 5, "Test recensione", cl, cv);
        cv.addRecensione(re);
        cv.inserisciCommentoHost("Test Host", 99);
        assertEquals("Test Host", cl.visualizzaRispostaHost(99));
    }
}