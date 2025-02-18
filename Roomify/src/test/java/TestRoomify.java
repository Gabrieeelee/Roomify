
import com.Roomify.*;
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
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");

        roomify.logIn(4);
        roomify.inserisciBeB(100,"la dolce melaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        assertNotNull(roomify.getBebCorrente());
    }

    @Test
    public void inserisciCasavacanza(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");

        roomify.logIn(4);
        roomify.inserisciCasavacanza(100,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        assertNotNull(roomify.getcvCorrente());
    }

    @Test
    public void inserisciStanza(){
        roomify.inserisciStanza(100,"luxury",3,111,100,"bellissima",new ArrayList<>());
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
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");

        roomify.logIn(4);
        roomify.inserisciCasavacanza(100,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();
        assertEquals(9, roomify.getElencoStrutture().size());
        assertNotNull(roomify.getElencoStrutture());
        roomify.logout();
    }

    @Test
    public void testPrenotaAlloggio(){

        Cliente cl = new Cliente(1,"Enricomaria", "Di Rosolini", LocalDate.of(1999, 1, 13), "DRS", "email@test.com", "23232");

        roomify.logIn(1);

        //le strutture nella città specificata, con il relativo numero di ospiti e nelle date indicate vengono restituite in una lista
        ArrayList<Struttura> lista=roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2);
        assertEquals("Taormina",roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(1).getCitta());
        assertEquals("La Dolce Mela",roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(1).getNome());
        roomify.logout();
    }

    @Test
    public void testSelezionaStruttura(){
        Cliente cl=new Cliente(1,"Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.logIn(cl.getId());
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(2);
        System.out.println(st);
        assertEquals("Un estate al mare",st.getNome());
    }

    @Test
    public void testSelezionaStanza(){
        Cliente cl=new Cliente(1,"Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.logIn(cl.getId());
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(1);
        roomify.selezionaStanza(1,st);
        assertEquals(1,roomify.getPrenotazioneCorrente().getStanza().getId());
    }

    @Test
    public void testConfermaPrenotazione(){
        Cliente cl=new Cliente(1,"Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.logIn(cl.getId());
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxury",3,111,100,"bellissima",new ArrayList<>());
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(2,"Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 20, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();

        Struttura st=roomify.selezionaStruttura(1);
        roomify.selezionaStanza(1,st);
        roomify.confermaPrenotazione();
        assertNotNull(roomify.getListaprenotazioni());
    }
}
