

import com.Roomify.Beb;

import com.Roomify.Stanza;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class TestBeB {
    Beb be;
    @Test
    public void testInserisciStanza() {
         be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia", "Acate", "RG", 29, "VIA PISILLI 29");
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>());
    }

    @Test
    public void testIsAvailable(){
        be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia", "Acate", "RG", 29, "VIA PISILLI 29");
        be.inserisciStanza(1,"Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>());
        be.inserisciStanza(2,"Buona la seconda", 1, 21, 50, "Stanza accogliente", new ArrayList<>());
        be.inserisciStanza(3,"Buona la terza", 5, 21, 50, "Stanza accogliente", new ArrayList<>());

        assertEquals("Buona la terza",be.isAvaible(LocalDate.of(2020,10,10),LocalDate.of(2020,10,10),2).get(1).getNome());

    }

}