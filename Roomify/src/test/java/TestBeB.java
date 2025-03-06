

import com.Roomify.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class TestBeB {
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
    public void testInserisciStanza() {
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        assertEquals(1,be.getListaStanze().size());
    }

    @Test
    public void testAddPrenotazione(){
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(2,"Buona la seconda", 10, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(3,"Buona la terza", 5, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        Stanza sz=be.selStanza(1);
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, be,sz, cl);
        be.addPrenotazione(pre);
        assertEquals(be.getListaPrenStanze().size(),1);
    }

    @Test
    public void testIsAvailable(){
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(2,"Buona la seconda", 10, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(3,"Buona la terza", 5, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        Stanza sz=be.selStanza(1);
        pre = new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo", 2, be,sz, cl);
        be.addPrenotazione(pre);
        assertEquals(2,be.isAvaible(LocalDate.of(2024,3,2),LocalDate.of(2024,3,20),2).size());
    }

    @Test
    public void testAggiornaStruttura(){
        assertEquals("Villa fontana",be.getNome());
        be.aggiornaStruttura("Villa bella",null);
        assertEquals("Villa bella",be.getNome());
    }

    @Test
    public void testmodStanza(){
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(2,"Buona la seconda", 10, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(3,"Buona la terza", 5, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.selStanza(1);
        be.modStanza("ciao",null,null,100f);
        assertEquals("ciao",be.getListaStanze().get("ciao").getNome());
    }

}