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
    private Cliente cl,cl1;
    private Recensione re,re2;
    @Before
    public void init(){
        cl = new Cliente(1,"Enricomaria", "Di Rosolini", LocalDate.of(1999, 1, 13), "DRS", "email@test.com", "23232");
        cl1 = new Cliente(1,"Enrico", "Di Florio", LocalDate.of(1999, 1, 13), "DRSasadad", "email@test.com", "23232");
        ho = new Host(2,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        st = new CasaVacanze(1,"Villa Sole", "Splendida villa con piscina e vista mare.", "Italia", "Palermo", "PA", 90100, "Via Roma, 10", 6, 4, 120.50f, 150, new ArrayList<Servizio>(), ho);
        re=new Recensione(1,5,"bella",cl,st);
        st.addRecensione(re);
        re2=new Recensione(2,5,"bella",cl1,st);
        st.addRecensione(re2);
    }

    @Test
    public void testequalsCitta(){
        assertTrue(st.equalsCitta("Palermo"));
    }

    @Test
    public void testgetRecComm(){
        assertEquals(2,st.getRecComm().size());
        st.inserisciCommentoHost("prova",1);
        assertEquals(1,st.getRecComm().size());
    }

    @Test
    public void testinserisciCommentoHost(){
        st.inserisciCommentoHost("provaa",1);
        assertEquals(re.getCommentoHost(),"provaa");
    }

    @Test
    public void testinserisciTariffa(){
        st.inserisciTariffa("tariffa1",2,3,0.5f);
        assertEquals(1,st.getListatf().size());
    }

}
