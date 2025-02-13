

import com.Roomify.Beb;
import com.Roomify.Stanza;
import com.Roomify.Struttura;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class TestBeB {
    Beb be;
    @Test

    public void testInserisciStanza() {
         be = new Beb(23, "Villa fontana", "Mi piaccciono le fontane", "Italia",
                "Acate", "RG",
                29, "VIA PISILLI 29");
        be.inserisciStanza("Buona la prima", 3, 21, 50, "Stanza accogliente", new ArrayList<>());
    }

}