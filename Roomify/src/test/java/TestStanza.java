import com.Roomify.Beb;
import com.Roomify.Cliente;
import com.Roomify.Prenotazione;
import com.Roomify.Stanza;
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
    public void testAddPrenotaazione(){
        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>());
        Prenotazione pre= new Prenotazione(1,LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),"prenotata",stanza,cl);

        stanza.addPrenotazione(pre);
        assertNotNull(stanza.getListaprenotazioni());
    }

    @Test
    public void testIsAvailable(){

        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        Stanza stanza = new Stanza(1,"luxury1",3,111,100,"bellissima",new ArrayList<>());
        Prenotazione pre= new Prenotazione(1,LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),"prenotata",stanza,cl);
        stanza.addPrenotazione(pre);

        Stanza stanzanuova = new Stanza(2,"luxury4",30,111,100,"bellissima",new ArrayList<>());

        Stanza stanza2=stanza.isAvailable(LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),3);
        Stanza stanza3=stanzanuova.isAvailable(LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),5);
        assertEquals("luxury4",stanza3.getNome());
        assertNull(stanza2);


    }
}
