import com.Roomify.Beb;
import com.Roomify.CasaVacanze;
import com.Roomify.Host;
import com.Roomify.TariffaStagionale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCasaVacanze {

    @Test
    public void testInserisciCasaVacanze(){
        Host ho=new  Host(1,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        CasaVacanze cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(),ho);
        cv.inserisciTariffa("Estiva", 3, 3.15f );
    }
}
