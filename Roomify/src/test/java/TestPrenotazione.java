import com.Roomify.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestPrenotazione {
    private Prenotazione pre;
    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;


    @Before
    public void initMet() {
        ho = new Host(2, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(), ho);
        cl = new Cliente(1, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        pre = new Prenotazione(123, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
    }

    @Test
    public void isRecensibile(){
        assertNull(pre.isRecensibile());
    }

    @Test
    public void isAssicurabile(){
        assertTrue(pre.isAssicurabile());
        pre.setDataPrenotazione(LocalDate.of(2025,01,01));
        assertFalse(pre.isAssicurabile());
    }

    @Test
    public void isAnnullabile(){
        assertTrue(pre.isAnnullabile());
    }

    @Test
    public void setCostoTotale(){
        pre.setCostoTotale();
        assertEquals(42,pre.getCostoTotale(),0.01f);
    }
}