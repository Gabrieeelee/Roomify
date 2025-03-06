import com.Roomify.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class TestPartnerAssicurativo {
    private PartnerAssicurativo pa;
    @Before
    public void initMet() {
        pa = new PartnerAssicurativo(67,"Mario", "Rossi", LocalDate.of(1985, 5, 20), "RSSMRA85E20H501Z", "3456789123", "mario.rossi@email.com", 12345);
        pa.inseriNuovaPolizza( "Casa allagata", "completa", 24, "Attiva");
        pa.inseriNuovaPolizza( "Problemi tecnici", "completa", 31, "Attiva");
        pa.confermaInserimento();

    }

    @Test
    public void confermaInserimento(){
        pa.inseriNuovaPolizza( "Volo sospeso", "parziale", 24, "Attiva");
        pa.inseriNuovaPolizza( "Beb rapito dagli alieni", "completa", 31, "Attiva");
        pa.confermaInserimento();
        assertEquals(4, pa.getListapolizze().size());
    }

    @Test
    public void mostraPolizze(){
        assertEquals(2, pa.mostraPolizze(14).size());
        assertEquals(0, pa.mostraPolizze(53).size());
    }

    @Test
    public void delPolizza(){
        Map<Integer, PolizzaAssicurativa> map = pa.getListapolizze();
        int id = 0;
        for (PolizzaAssicurativa pa : map.values()){
            id = pa.getId();
            break;
        }
        pa.selectPolizza(id);
        pa.delPolizza();
        assertEquals(1, pa.getListapolizze().size());
    }

}