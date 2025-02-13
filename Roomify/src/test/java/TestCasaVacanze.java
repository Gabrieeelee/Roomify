import com.Roomify.Beb;
import com.Roomify.CasaVacanze;
import com.Roomify.TariffaStagionale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCasaVacanze {

    @Test
    public void testInserisciCasaVacanze(){
        CasaVacanze cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>());
        cv.inserisciTariffa("Estiva", 3, 3.15f );
    }
}
