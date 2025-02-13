
import com.Roomify.CasaVacanze;
import com.Roomify.Roomify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestRoomify {
    static Roomify roomify;

    @BeforeClass
    public static void initTest(){
        roomify=Roomify.getInstance();
    }

    @Test
    public void inserisciBeb(){
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        assertNotNull(roomify.getBebCorrente());
    }

    @Test
    public void inserisciCasavacanza(){
        roomify.inserisciCasavacanza(1,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, null);
        assertNotNull(roomify.getcvCorrente());
    }

    @Test
    public void inserisciStanza(){
        roomify.inserisciStanza("luxury",3,111,100,"bellissima",new ArrayList<>());
        assertNotNull(roomify.getElencoStrutture());
    }
    @Test
    public void inserisciTariffa(){
        roomify.inserisciTariffa("estate",1,3.5f);
    }

    @Test
    public void testConfermaBeB(){
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza("luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();
       assertEquals(1, roomify.getElencoStrutture().size());
        assertNotNull(roomify.getElencoStrutture());
    }

    @Test
    public void testConfermaCasavacanza(){
        roomify.inserisciCasavacanza(1,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();
        assertEquals(1, roomify.getElencoStrutture().size());
        assertNotNull(roomify.getElencoStrutture());
    }
}
