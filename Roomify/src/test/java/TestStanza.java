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

    @Test
    public void testAddPrenotazione(){

        Cliente cl=new Cliente(1,"Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");


        Beb beb=new Beb( 5, "B&B Il Glicine", "Struttura romantica con colazione inclusa", "Italia", "Firenze", "FI", 50100, "Piazza della Signoria 7",new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12"));
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>(),beb);
        Prenotazione pre= new Prenotazione(1,LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),"prenotata",stanza,cl);

        stanza.addPrenotazione(pre);
        assertNotNull(stanza.getListaprenotazioni());
    }

    @Test
    public void testIsAvailable(){

        Cliente cl=new Cliente(1,"Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        Beb beb=new Beb( 5, "B&B Il Glicine", "Struttura romantica con colazione inclusa", "Italia", "Firenze", "FI", 50100, "Piazza della Signoria 7",new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12"));
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>(),beb);
        Prenotazione pre= new Prenotazione(1,LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),"prenotata",stanza,cl);
        stanza.addPrenotazione(pre);

        Stanza stanzanuova = new Stanza(2,"luxury13",30,111,100,"bellissima",new ArrayList<>(),beb);


        Stanza stanza2=stanza.isAvailable(LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),3);
        Stanza stanza3=stanzanuova.isAvailable(LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),5);
        assertEquals("luxury4",stanza3.getNome());
        assertNull(stanza2);


    }
}
