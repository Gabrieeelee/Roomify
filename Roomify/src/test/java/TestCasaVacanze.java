import com.Roomify.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCasaVacanze {

    private CasaVacanze cv;
    private Cliente cl;
    private Host ho;
    private Prenotazione pre;

    @Before
    public void initMet(){
        ho = new Host(2,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");

        cl = new  Cliente(1,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(),ho);
        pre =new Prenotazione(123, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 15), "Attivo",cv ,cl);

    }


    @Test
    public void testInserisciCasaVacanze(){
        Host ho=new  Host(1,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        CasaVacanze cv = new CasaVacanze(34, "Milopoli", "Casa in via Milo", "Italia", "Catania", "CT", 95125, "Via Milo 54", 5, 4, 3, 54, new ArrayList<>(),ho);
        cv.inserisciTariffa("Estiva", 3, 3.15f );
    }


}
