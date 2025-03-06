import com.Roomify.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestUtente {
    private Prenotazione pre;
    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;
    private Recensione re;


    @Before
    public void initMet() {
        ho = new Host(2, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(), ho);
        cl = new Cliente(1, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        pre = new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
        re=new Recensione(1,5,"bella",cl,cv);
    }

    @Test
    public void richiestaAssistenza(){
        assertEquals(99,cl.richiestaAssistenza(99, "Test", "Test stato").getId());
    }

    @Test
    public void richiestaAssistenzaPrenotazione(){
        assertEquals(99,cl.richiestaAssistenzaPrenotazione(99, "Test", pre,"Test stato").getId());
    }

    @Test
    public void richiestaAssistenzaRecensione(){
        assertEquals(99,cl.richiestaAssistenzaRecensione(99, "Test", re,"Test stato").getId());
    }

    @Test
    public void confermaAssistenza(){
        cl.richiestaAssistenza(99, "Test", "Test stato").getId();
        cl.confermaAssistenza();

        cl.richiestaAssistenzaPrenotazione(98, "Test", pre,"Test stato").getId();
        cl.confermaAssistenza();

        cl.richiestaAssistenzaRecensione(97, "Test", re,"Test stato").getId();
        cl.confermaAssistenza();

        assertEquals(3, cl.getListaAssistenza().size());
    }

}