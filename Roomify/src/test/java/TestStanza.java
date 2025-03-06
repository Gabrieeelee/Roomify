import com.Roomify.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestStanza {

    private Beb be;
    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;
    private Prenotazione pre;
    @Before
    public void initMet() {
        be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia", "Acate", "RG", 29, "VIA PISILLI 29",ho);
        ho = new Host(2, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        cl = new Cliente(1, "Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, cv, cl);
        cl.addPrenotazione(pre);
    }

    @Test
    public void testAddPrenotazione(){
       Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>(),be);
       pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, be,stanza, cl);
       stanza.addPrenotazione(pre);
       assertEquals(1,stanza.getListaprenotazioni().size());
    }

    @Test
    public void testIsAvailable(){
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>(),be);
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, be,stanza, cl);
        stanza.addPrenotazione(pre);
        assertNull(stanza.isAvailable( LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 10),2));
        assertNotNull(stanza.isAvailable( LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 10),2));
        assertNull(stanza.isAvailable( LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 10),10));
    }

    @Test
    public void testsetInfo(){
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>(),be);
        assertEquals("luxury1",stanza.getNome());
        stanza.setInfo("prova",null,null,0);
        assertEquals("prova",stanza.getNome());
    }
}
