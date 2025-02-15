
import com.Roomify.CasaVacanze;
import com.Roomify.Cliente;
import com.Roomify.Roomify;
import com.Roomify.Struttura;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
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
        roomify.inserisciCasavacanza(1,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        assertNotNull(roomify.getcvCorrente());
    }

    @Test
    public void inserisciStanza(){
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        assertNotNull(roomify.getElencoStrutture());
    }
   /* @Test
    public void inserisciTariffa(){
        roomify.inserisciTariffa("estate",1,3.5f);
    }*/

    @Test
    public void testConfermaBeB(){
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
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

    @Test
    public void testPrenotaAlloggio(){
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        //le strutture nella città specificata, con il relativo numero di ospiti e nelle date indicate vengono restituite in una lista
        ArrayList<Struttura> lista=roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2);
        assertEquals("taormina",roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(1).getCitta());
        assertEquals("Un estate al mare",roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(1).getNome());
    }

    @Test
    public void testSelezionaStruttura(){
        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(2,roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2),cl);
        System.out.println(st);
        assertEquals("Un estate al mare",st.getNome());
    }

    @Test
    public void testSelezionaStanza(){
        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(1,roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2),cl);
        roomify.selezionaStanza(1,st,cl);
        assertEquals(1,roomify.getPrenotazioneCorrente().getStanza().getId());
    }

    @Test
    public void testConfermaPrenotazione(){
        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(1,roomify.prenotaAlloggio("taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2),cl);
        roomify.selezionaStanza(1,st,cl);
        roomify.confermaPrenotazione();
        assertNotNull(roomify.getListaprenotazioni());
    }
}
