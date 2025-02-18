

import com.Roomify.Beb;

import com.Roomify.Cliente;
import com.Roomify.Host;
import com.Roomify.Stanza;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class TestBeB {
    Beb be;
    @Test
    public void testInserisciStanza() {

        Host ho=new  Host(1,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia", "Acate", "RG", 29, "VIA PISILLI 29",ho);
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
    }

    @Test
    public void testIsAvailable(){
        Host ho=new  Host(1,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia", "Acate", "RG", 29, "VIA PISILLI 29",ho);
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(2,"Buona la seconda", 1, 21, 50, "Stanza accogliente", new ArrayList<>(),be);
        be.inserisciStanza(3,"Buona la terza", 5, 21, 50, "Stanza accogliente", new ArrayList<>(),be);

        assertEquals("Buona la terza",be.isAvaible(LocalDate.of(2020,10,10),LocalDate.of(2020,10,10),2).get(1).getNome());

    }

}