import com.Roomify.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;


public class TestStruttura {
    private Struttura st;
    private Host ho;
    private Cliente cl;
    @Before
    public void init(){
        cl = new Cliente(1,"Enricomaria", "Di Rosolini", LocalDate.of(1999, 1, 13), "DRS", "email@test.com", "23232");
        ho = new Host(2,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        st = new CasaVacanze(1,"Villa Sole", "Splendida villa con piscina e vista mare.", "Italia", "Palermo", "PA", 90100, "Via Roma, 10", 6, 4, 120.50f, 150, new ArrayList<Servizio>(), ho);
    }
    @Test
    public void testInserisciRecensione(){
        int sv = st.getListRecensioni().size();
        st.inserisciRecensione(3, "Test Struttura recensione", cl);
        assertEquals(sv+1,st.getListRecensioni().size());
    }

   
}
