import com.Roomify.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


public class TestHost {
    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;
    private Prenotazione pre;
    private Recensione re;

    @Before
    public void initMet() {
        ho = new Host(20, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(), ho);
        cl = new Cliente(19, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
        cv.addPrenotazione(pre);
        pre = new Prenotazione(124, LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
        cv.addPrenotazione(pre);
        pre = new Prenotazione(125, LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
        cv.addPrenotazione(pre);
        re=new Recensione(1,4,"bello",cl,cv);
        cv.addRecensione(re);
    }

    @Test
    public void testaddStruttura(){
        assertTrue(ho.getListaStrutture().isEmpty());
        ho.addStruttura(cv);
        assertEquals(1, ho.getListaStrutture().size());
    }

    @Test
    public void testvisualizzaRecensioni(){
        ho.addStruttura(cv);
        assertEquals(1,ho.visualizzaRecensioni().size());
    }

    @Test
    public void testgetListaPrenotazioni(){
        ho.addStruttura(cv);
        assertEquals(3,ho.getListaPrenotazioni().size());
    }

    @Test
    public void testselRecensione(){
        ho.addStruttura(cv);
        ho.getListaRecensioni();
        Recensione re=ho.selRecensione(1);
        assertEquals(1,re.getId());
    }


}
