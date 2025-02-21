
import com.Roomify.*;
import com.Roomify.Assistenza.RichiestaAssistenza;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

public class TestRoomify {
    static Roomify roomify;

    @BeforeClass
    public static void initTest(){
        roomify=Roomify.getInstance();
    }

//UC1
    @Test
    public void inserisciBeb(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");
        roomify.logIn(4);

        //inserisco una nuova struttura  nel sistema
        roomify.inserisciBeB("la dolce melaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        assertNotNull(roomify.getBebCorrente());
        //inserisco una struttura già presente nel sistema
        roomify.inserisciBeB( "La Dolce pera", "Un bellissimo B&B immerso nel verde", "Italia", "Taormina", "Messina", 98034, "Via Mazzini 12");
        assertEquals("la dolce melaaaaa",roomify.getBebCorrente().getNome());
    }

    @Test
    public void inserisciCasavacanza(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");
        roomify.logIn(4);
        roomify.inserisciCasavacanza("Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        assertNotNull(roomify.getcvCorrente());

        roomify.inserisciCasavacanza( "Villa Aurora", "Villa di lusso con ampio giardino", "Italia", "Milano", "MI", 20100, "Corso Magenta, 25", 8, 5, 200.00f, 180, new ArrayList<>());
        assertEquals("Un estate al mare",roomify.getcvCorrente().getNome());
    }

    @Test
    public void inserisciStanza(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");
        roomify.logIn(4);

        //inserisco una nuova struttura  nel sistema
        roomify.inserisciBeB("la dolce melaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        assertNotNull(roomify.getBebCorrente());
        roomify.inserisciStanza("luxury",3,111,100,"bellissima",new ArrayList<>());
        assertNotNull(roomify.getElencoStrutture());
    }

    @Test
    public void testConfermaBeB(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");
        roomify.logIn(4);
        roomify.inserisciBeB( "la dolce mela", "un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza( "luxury", 3, 111, 100, "bellissima", new ArrayList<>());
        roomify.confermaBeB();
        assertEquals(9, roomify.getElencoStrutture().size());
        assertNotNull(roomify.getElencoStrutture());
    }

    @Test
    public void testConfermaCasavacanza(){
        Host host=new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12");

        roomify.logIn(4);
        roomify.inserisciCasavacanza("Un estate al mare", "casa al mare", "italia", "taormina","messina",98034, "via dei licantropi", 2, 2, 300, 100, new ArrayList<>());
        roomify.confermaCasaVacanze();
        assertEquals(9, roomify.getElencoStrutture().size());
        assertNotNull(roomify.getElencoStrutture());
        roomify.logout();
    }

//UC2
    @Test
    public void testPrenotaAlloggio(){

        roomify.logIn(1);

        //le strutture nella città specificata, con il relativo numero di ospiti e nelle date indicate vengono restituite in una lista
        ArrayList<Struttura> lista=roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2);

        //verifico che la lista contiene una struttura con sede a taormina
        assertEquals("Taormina",roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(1).getCitta());
        //verifico che il primo elemento della lista sia la dolce mela, in base a come ho popolato la lista
        assertEquals("La Dolce Mela",roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2).get(0).getNome());
        roomify.logout();
    }

    @Test
    public void testSelezionaStruttura(){

        roomify.logIn(1);
        ArrayList<Struttura> lista=roomify.prenotaAlloggio("Taormina", LocalDate.of(2023,10,10),LocalDate.of(2023,10,15),2);
        Struttura st=roomify.selezionaStruttura(4);
        System.out.println(st);
        assertEquals("La Dolce Mela",st.getNome());
    }

    @Test
    public void testSelezionaStanza(){
        roomify.logIn(1);
        ArrayList<Struttura> lista=roomify.prenotaAlloggio("Taormina", LocalDate.of(2020,10,10),LocalDate.of(2020,10,15),2);

        Struttura st=roomify.selezionaStruttura(5);
        roomify.selezionaStanza(1);
        assertEquals(1,roomify.getPrenotazioneCorrente().getStanza().getId());
    }

    @Test
    public void testConfermaPrenotazione(){
        roomify.logIn(1);

        ArrayList<Struttura> lista=roomify.prenotaAlloggio("Taormina", LocalDate.of(2023,10,10),LocalDate.of(2023,10,15),2);
        Struttura st=roomify.selezionaStruttura(5);
        roomify.selezionaStanza(1);
        roomify.confermaPrenotazione();
      //  assertEquals(7,roomify.getListaprenotazioni().size());
    }


//TEST UC6
    @Test
    public void testNuovaRecensione(){
       roomify.logIn(1);
       //Il numero di strutture recensibili è pari a 0 perchè ancora non è avvenuta la pernottazione
       assertEquals(0,roomify.nuovaRecensione().size());
       roomify.logout();

        roomify.logIn(3);
        //Il numero di strutture recensibili è pari a 1
        assertEquals(1,roomify.nuovaRecensione().size());
        roomify.logout();
    }

    @Test
    public void testSelezionaStrutturaRec(){
        roomify.logIn(3);
        Struttura st = roomify.selezionaStrutturaRec(5);
        assertNotNull(st);
        roomify.logout();
    }

    @Test
    public void testInserisciRecensione(){
        roomify.logIn(3);
        Struttura st = roomify.selezionaStrutturaRec(5);
        roomify.inserisciRecensione(2, "Non mi è piaciuta l'esperienza");

        assertEquals(1,st.getListRecensioni().size());
        roomify.logout();
    }

    //TEST UC8
    @Test
    public void testRegistrazioneHost(){
        roomify.registrazioneHost("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        //un utente già registrato con un codice fiscale non può registrarsi nuovamente
        assertNull( roomify.getUtenteCorrente());

        roomify.registrazioneHost("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLRqwe", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        //se la registrazione avviene, la lista viene incrementata
        assertNotNull( roomify.getUtenteCorrente());
    }

    @Test
    public void testRegistrazioneCliente(){
        roomify.registrazioneCliente("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        //un utente già registrato con un codice fiscale non può registrarsi nuovamente
        assertNull( roomify.getUtenteCorrente());

        roomify.registrazioneCliente("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228");
        //un utente già registrato con un codice fiscale non può registrarsi nuovamente
        assertNull( roomify.getUtenteCorrente());
    }

    @Test
    public void testSelezionaAbbonamento(){
        roomify.registrazioneHost("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLRqqq", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        roomify.selezionaAbbonamento(2);

        assertEquals(roomify.getListaAbbonamneto().get(2).getNome(), ((Host)roomify.getUtenteCorrente()).getAbbonamento().getNome());
        roomify.logout();
    }

    @Test
    public void testConfermaRegistrazione(){
        int siz=roomify.getListaUtenti().size();
        roomify.registrazioneHost("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLRsadagfe", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        roomify.confermaRegistrazione();
        assertEquals(siz+1,roomify.getListaUtenti().values().size());
        roomify.logout();
    }

    //TEST UC13
    @Test
    public void testCommentaRecensione(){
        roomify.logIn(8);
        assertNotNull((roomify.commentaRecensione()));
        roomify.logout();
    }


    @Test
    public void testGetRecensioni(){
        roomify.logIn(8);
        assertNotNull(roomify.getRecensioni(8));
        roomify.logout();
    }

    @Test
    public void testInserisciCommento(){
        roomify.logIn(8);
        //mi serve per selezionare la struttura corrente.
        ArrayList<Recensione> listRec = roomify.getRecensioni(8);
        int sv = listRec.size();
        roomify.inserisciCommento("Test", 1);
        assertEquals("Test", listRec.get(1).getCommentoHost());
        roomify.logout();
    }

//TEST UC3
    @Test
    public void testVisualizzaPrenotazioni(){
        roomify.logIn(6);
        assertNotNull(roomify.visualizzaPrenotazioni());
        assertEquals(3, roomify.visualizzaPrenotazioni().size());
    }

    @Test
    public void testSelezionaStrutturaVis(){
        roomify.logIn(6);
        assertNotNull(roomify.selezionaStrutturaVis(5));
        assertEquals(2, roomify.selezionaStrutturaVis(4).size());
    }

//TEST UC4
    @Test
    public void testVisualizzaPrenotazioniCliente(){
        roomify.logIn(1);
        assertEquals(2,roomify.visualizzaPrenotazioniCliente(LocalDate.of(2025,01,01),LocalDate.of(2025,12,31)).size());
    }

//TEST UC5
    @Test
    public void testAnnullaPrenotazione(){
       roomify.logIn(1);
       assertNotNull(((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti());
       assertEquals(3, ((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti().size());
    }

    @Test
    public void testSelezioaPrenotazione(){
        roomify.logIn(1);
        Map<Integer,Prenotazione> listapren=((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti();
        int id=0;
        for(Prenotazione p: listapren.values()){
            id=p.getId();
            break;
        }
        assertTrue(roomify.selezionaPrenotazione(id));
        assertEquals("Prenotazione annullata",((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti().get(id).getStato());
    }

//TEST UC11
    @Test
    public void testRichiestaAssistenza(){
        roomify.logIn(7);
        Map<Integer, Prenotazione> sv = ((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti();
        int n= roomify.getUtenteCorrente().getListaAssistenza().size();
        int nu = roomify.getListaAssistenza().size();
        int id = 0;
        for(Prenotazione pre : sv.values()){
            id = pre.getId();
            break;
        }
        roomify.richiestaAssistenza("TESTUC11", id, "Prenotazione");
        assertEquals(n+1, roomify.getUtenteCorrente().getListaAssistenza().size());
        assertEquals(0, roomify.getListaAssistenza().size());
        roomify.logout();
    }


    @Test
    public void testConfermaAssistenza(){
        roomify.logIn(7);
        Map<Integer, Prenotazione> sv = ((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti();
        int n= roomify.getListaAssistenza().size();
        int id = 0;
        for(Prenotazione pre : sv.values()){
            id = pre.getId();
            break;
        }
        roomify.richiestaAssistenza("TESTUC11", id, "Prenotazione");
        roomify.confermaAssistenza();
        assertEquals(n+1, roomify.getListaAssistenza().size());

    }

//TEST UC12
    @Test
    public void testSelezionaRAssistenza(){
        assertNull(roomify.selezionaRAssistenza(0));
    }

    @Test
    public void testGeneraMessaggio(){
        roomify.logIn(7);
        Map<Integer, Prenotazione> sv = ((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti();
        int n= roomify.getListaAssistenza().size();
        int id = 0;
        for(Prenotazione pre : sv.values()){
            id = pre.getId();
            break;
        }
        roomify.richiestaAssistenza("TESTUC11", id, "Prenotazione");
        roomify.confermaAssistenza();

        Map<Integer, RichiestaAssistenza> sva = roomify.getListaAssistenza();
        int id2 =0 ;
        for(RichiestaAssistenza ra : sva.values()){
            id2 = ra.getId();
            break;
        }
        RichiestaAssistenza ra = roomify.selezionaRAssistenza(id2);
        roomify.generaMessaggio("TESTO PROVA");
       assertEquals("TESTO PROVA",ra.getListaMessaggi().get(ra.getListaMessaggi().size()-1).getDescrizione());
    }
    @Test
    public void testConfermaMessaggio(){
        roomify.logIn(7);
        Map<Integer, Prenotazione> sv = ((Cliente)roomify.getUtenteCorrente()).getListaPrenotazioniClienti();
        int n= roomify.getListaAssistenza().size();
        int id = 0;
        for(Prenotazione pre : sv.values()){
            id = pre.getId();
            break;
        }
        roomify.richiestaAssistenza("TESTUC11", id, "Prenotazione");
        roomify.confermaAssistenza();

        Map<Integer, RichiestaAssistenza> sva = roomify.getListaAssistenza();
        int id2 =0 ;
        for(RichiestaAssistenza ra : sva.values()){
            id2 = ra.getId();
            break;
        }
        RichiestaAssistenza ra = roomify.selezionaRAssistenza(id2);
        roomify.generaMessaggio("TESTO PROVA");
        roomify.confermaMessaggio();
        assertEquals("Chiuso", ra.getStato());
    }
}
