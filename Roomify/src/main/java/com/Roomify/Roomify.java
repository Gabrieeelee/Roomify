package com.Roomify;

import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;

import java.util.*;
import java.time.LocalDate;

public class Roomify {

    private static Roomify roomify;

    private Utente utenteCorrente;
    private Struttura stcorrente;
    private Prenotazione pre;
    private Map<Integer,Struttura> mapStrutture;
    private Map<Integer,Utente> listaUtenti=new HashMap<>();
    private Map<Integer,Utente> listaPartner = new HashMap<>();
    private HashMap<Integer, Servizio> servizi;
    private ArrayList<Abbonamento> listaAbbonamneto;
    private Map<Integer,Prenotazione> listaPrenotazioni;
    private Map<Integer, RichiestaAssistenza> listaAssistenza;
    private Recensione recScelta;
    private Struttura strutturascelta;
    private RichiestaAssistenza richiestaAssistenzaScelta;
    private RichiestaAssistenza richiestaAssistenzaCorrente;

    private Roomify() {
        this.mapStrutture = new HashMap<>();
        this.listaAssistenza = new HashMap<>();
        this.listaAbbonamneto = new ArrayList<>();
        this.listaPrenotazioni = new HashMap<>();
        servizi = new HashMap<>();
    }

    public static Roomify getInstance() {
        if (roomify == null)
            roomify = new Roomify();
        else
            System.out.println("Istanza già creata");

        return roomify;
    }
    
    public void populate() {
        //INSERIMENTO ABBONAMENTI
        listaAbbonamneto.add(new Abbonamento(1,"1 Mese", 1,10));
        listaAbbonamneto.add(new Abbonamento(2,"3 Mesi", 3, 20));
        listaAbbonamneto.add(new Abbonamento(3,"6 Mese", 6, 40));
        listaAbbonamneto.add(new Abbonamento(4,"12 Mesi", 12,50));

        //INSERIMENTO SERVIZI
        servizi.put(1, new Servizio(1, "Lavatrice", "Elettrodomestico utilizzato per lavare i vestiti"));
        servizi.put(2, new Servizio(2, "Microonde", "Elettrodomestico per riscaldare o cucinare velocemente"));
        servizi.put(3, new Servizio(3, "Lavastoviglie", "Elettrodomestico utilizzato per lavare i piatti"));
        servizi.put(4, new Servizio(4, "Vasca", "Struttura per il bagno e il relax"));
        servizi.put(5, new Servizio(5, "Aria Condizionata", "Sistema di climatizzazione"));
        servizi.put(6, new Servizio(6, "WiFi", "Connessione Internet senza fili"));
        servizi.put(7, new Servizio(7, "TV", "Televisione con canali via cavo"));
        servizi.put(8, new Servizio(8, "Piscina", "Piscina esterna per gli ospiti"));
        servizi.put(9, new Servizio(9, "Parcheggio gratuito", "Parcheggio privato incluso"));

        ArrayList<Integer> listcod1 = new ArrayList<>(Arrays.asList(1, 4, 6, 8));
        ArrayList<Integer> listcod2 = new ArrayList<>(Arrays.asList(2, 3, 5, 7));

        //INSERIMENTO HOST E CLIENTI
        listaUtenti.put(1, new Cliente(1,"Enricomaria", "Di Rosolini", LocalDate.of(1999, 1, 13), "DRS", "email@test.com", "23232"));
        listaUtenti.put(2, new Host(2,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II"));
        listaUtenti.put(3, new Cliente(3,"Giulia", "Bianchi", LocalDate.of(1995, 5, 20), "GBI", "giulia@test.com", "34567"));
        listaUtenti.put(4, new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12"));
        listaUtenti.put(5, new Cliente(5,"Luca", "Verdi", LocalDate.of(1990, 11, 8), "LVE", "luca@test.com", "78901"));
        listaUtenti.put(6, new Host(6,"Elena", "Neri", LocalDate.of(1985, 7, 2), "ENE", "elena.host@test.com", "89012", "3912345678", "IT", "Piazza Duomo 8"));
        listaUtenti.put(7, new Cliente(7,"Francesca", "Russo", LocalDate.of(1993, 3, 25), "FRU", "francesca@test.com", "90123"));
        listaUtenti.put(8, new Host(8,"Antonio", "Ferrari", LocalDate.of(1979, 9, 30), "AFE", "antonio.host@test.com", "67890", "3123456789", "IT", "Corso Italia 5"));

        //INSERIMENTO PARTNER ASSICURATIVI
        registrazionePartner("Mario", "Rossi", LocalDate.of(1985, 5, 20), "RSSMRA85E20H501Z", "3456789123", "mario.rossi@email.com", 12345);
        confermaRegistrazione();

        registrazionePartner("Luca", "Bianchi", LocalDate.of(1978, 8, 15), "BNCLCU78M15H501Y", "3467891234", "luca.bianchi@email.com", 67890);
        confermaRegistrazione();

        registrazionePartner("Giulia", "Verdi", LocalDate.of(1990, 2, 10), "VRDGLL90B10H501X", "3478902345", "giulia.verdi@email.com", 54321);
        confermaRegistrazione();

        registrazionePartner("Andrea", "Ferrari", LocalDate.of(1982, 11, 25), "FRRNDR82S25H501W", "3489013456", "andrea.ferrari@email.com", 98765);
        confermaRegistrazione();

        //INSERIMENTO ALLOGGI
        logIn(4);
        //1
        inserisciCasavacanza( "Villa Sole", "Splendida villa con piscina e vista mare.", "Italia", "Palermo", "PA", 90100, "Via Roma, 10", 6, 4, 120.50f, 150, listcod1 );
        inserisciTariffa("supersconti",1,4,(1/3));
        confermaCasaVacanze();
        logout();

        logIn(2);
        //2
        inserisciCasavacanza( "Casa Luna", "Accogliente casa nel centro storico", "Italia", "Roma", "RM", 00100, "Via dei Fori, 5", 4, 3, 80.00f, 90, listcod2);
        confermaCasaVacanze();

        //3
        inserisciBeB( "B&B Il Bosco", "Circondato dal verde per un totale relax", "Italia", "Torino", "TO", 10100, "Strada del Bosco 45");
        inserisciStanza( "Camera Rustica", 3, 70, 120, "Camera con arredamento rustico", listcod1);
        inserisciStanza( "Camera Family", 4, 90, 150, "Perfetta per famiglie numerose", listcod2);
        inserisciStanza( "Suite Romantica", 5, 130, 190, "Suite perfetta per coppie", listcod1);
        inserisciStanza( "Loft Moderno", 4, 115, 175, "Ampio loft con arredamento moderno", listcod2);
        confermaBeB();
        logout();

        logIn(6);
        //4
        inserisciBeB( "La Dolce Mela", "Un bellissimo B&B immerso nel verde", "Italia", "Taormina", "Messina", 98034, "Via Mazzini 12");
        inserisciTariffa("Promozione invernale",1,2,(1/4));
        inserisciStanza( "Camera Standard", 2, 60, 100, "Camera economica e accogliente", listcod1);
        inserisciStanza( "Camera Deluxe", 4, 100, 150, "Camera spaziosa e confortevole", listcod2);
        inserisciStanza( "Suite Panoramica", 3, 140, 200, "Vista mozzafiato sulla città", listcod1);
        confermaBeB();

        //5
        inserisciBeB( "La Dolce pera", "Un bellissimo B&B immerso nel verde", "Italia", "Taormina", "Messina", 98034, "Via Mazzini 12");
        inserisciStanza( " Standard", 2, 60, 100, "Camera economica e accogliente", listcod1);
        inserisciStanza( " Deluxe", 4, 100, 150, "Camera spaziosa e confortevole", listcod2);
        inserisciStanza( " Panoramica", 3, 140, 200, "Vista mozzafiato sulla città", listcod1);
        confermaBeB();

        //6
        inserisciBeB( "B&B Il Glicine", "Struttura romantica con colazione inclusa", "Italia", "Firenze", "FI", 50100, "Piazza della Signoria 7");
        inserisciStanza( "Suite Elegance", 3, 120, 180, "Camera lussuosa con vista panoramica", listcod1);
        inserisciStanza( "Camera Comfort", 3, 75, 110, "Perfetta per il relax", listcod2);
        inserisciStanza( "Junior Suite", 5, 140, 210, "Ampia suite con tutti i comfort", listcod1);
        inserisciStanza( "Mansarda Deluxe", 4, 110, 170, "Elegante mansarda con terrazza", listcod2);
        confermaBeB();
        logout();

        logIn(8);
        //7
        inserisciBeB( "B&B Sole e Mare", "Perfetto per chi ama il mare", "Italia", "Napoli", "NA", 80100, "Via Caracciolo 21");
        inserisciStanza( "Camera Marina", 3, 85, 130, "Camera con vista mare", listcod1);
        inserisciStanza( "Camera Blue", 4, 95, 140, "Camera con colori rilassanti", listcod2);
        inserisciStanza( "Penthouse Suite", 5, 200, 250, "Suite esclusiva con terrazza privata", listcod1);
        confermaBeB();

        //mi serve per precompilare il sistema
        int idpre;
        //8
        inserisciCasavacanza( "Villa Aurora", "Villa di lusso con ampio giardino", "Italia", "Milano", "MI", 20100, "Corso Magenta, 25", 8, 5, 200.00f, 180, listcod1);
        confermaCasaVacanze();
        logout();

        logIn(1);
        prenotaAlloggio("Taormina", LocalDate.of(2024, 5, 10), LocalDate.of(2024, 6, 13), 2);
        selezionaStruttura(4);
        selezionaStanza(1);
        pre.setDataPrenotazione(LocalDate.of(2024,1,10));
        idpre=pre.getId();
        confermaPrenotazione();

        //RECENSISCO LA STRUTTURA 4
        selezionaStrutturaRec(4);
        inserisciRecensione(1,"Sporca");

        //RICHIEDO ASSISTENZA RIGUARDANTE LA PRENOTAZIONE PRECEDENTE
        selCat("PRENOTAZIONE");
        selPrenotazione(idpre);
        richiestaAssistenza("Voglio i miei soldi indietro");
        confermaAssistenza();



        prenotaAlloggio("Torino", LocalDate.of(2020, 5, 10), LocalDate.of(2020, 6, 13), 2);
        selezionaStruttura(3);
        selezionaStanza(1);
        pre.setDataPrenotazione(LocalDate.of(2020, 01,27));
        confermaPrenotazione();

        //RECENSISCO LA STRUTTURA 3
        selezionaStrutturaRec(4);
        inserisciRecensione(5,"FANTASTICA");

        prenotaAlloggio("Taormina", LocalDate.of(2026, 5, 10), LocalDate.of(2026, 6, 13), 2);
        selezionaStruttura(4);
        selezionaStanza(1);
        idpre=pre.getId();
        confermaPrenotazione();


        selCat("PRENOTAZIONE");
        selPrenotazione(idpre);
        richiestaAssistenza("Non riesco ad annullare la prenotazione, mi da un errore");
        confermaAssistenza();

        logout();

        logIn(3);
        prenotaAlloggio("Firenze", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 15), 3);
        selezionaStruttura(5);
        selezionaStanza(2);
        pre.setDataPrenotazione(LocalDate.of(2020,1,10));
        confermaPrenotazione();

        selezionaStrutturaRec(5);
        inserisciRecensione(2,"non mi piace");


        logout();

        logIn(5);

        prenotaAlloggio("Roma",LocalDate.of(2026,10,10),LocalDate.of(2026,10,16),2);
        selezionaStruttura(2);
        pre.setDataPrenotazione(LocalDate.of(2025, 02,27));
        confermaPrenotazione();

        prenotaAlloggio("Napoli", LocalDate.of(2025, 8, 5), LocalDate.of(2025, 8, 12), 4);
        selezionaStruttura(6);
        selezionaStanza(3);
        pre.setDataPrenotazione(LocalDate.of(2025, 02,27));
        idpre=pre.getId();
        confermaPrenotazione();

        selCat("PRENOTAZIONE");
        selPrenotazione(idpre);
        richiestaAssistenza("prova numero 520543");
        confermaAssistenza();

        logout();


        logIn(7);
        prenotaAlloggio("Torino", LocalDate.of(2022, 9, 10), LocalDate.of(2022, 9, 20), 4);
        pre.setDataPrenotazione(LocalDate.of(2021,1,10));
        selezionaStruttura(3);
        selezionaStanza(3);

        confermaPrenotazione();


        selezionaStrutturaRec(8);
        inserisciRecensione(5,"mi piace");
        logout();

        //Commento recensioni da parte dell'host

        logIn(9);
        inseriNuovaPolizza( "Annulla il giorno stesso", "parziale", 12, "Attiva");
        inseriNuovaPolizza( "Catastrofe", "parziale", 24, "Disattivato");
        inseriNuovaPolizza( "Salute", "Completa", 10, "Attiva");
        confermaInserimento();

        logIn(11);
        inseriNuovaPolizza( "Annulla fino al giorno prima", "completa", 12, "Attiva");
        inseriNuovaPolizza( "Catastrofe", "completa", 240, "Attiva");
        inseriNuovaPolizza( "Salute", "parziale", 36, "Disattivato");
        confermaInserimento();
        logout();

    }

//UC1 GESTIONE STRUTTURA
    public void inserisciBeB( String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo){
        int id=generaidStruttura();
        int trovato=0;
        if(mapStrutture.size()!=0) {
            for(Struttura str: mapStrutture.values()) {
                if (str.getNome().equals(nome) && str.getPaese().equals(paese) && str.getCitta().equals(citta) && str.getProvincia().equals(provincia) && str.getIndirizzo().equals(indirizzo)) {
                    trovato=1;
                    break;
                }
            }
            if(trovato==0) {
                stcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
            }
        } else{
            stcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
        }
    }

    public void inserisciCasavacanza( String nome, String descrizione, String paese, String citta, String provincia, int  cap, String indirizzo,int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Integer> listaCodServizi){
        int id=generaidStruttura();
        int trovato=0;
        if(mapStrutture.size()!=0) {
            for (Struttura str : mapStrutture.values()) {
                    if (str.getNome().equals(nome) && str.getPaese().equals(paese) && str.getCitta().equals(citta) && str.getProvincia().equals(provincia) && str.getIndirizzo().equals(indirizzo)) {
                        trovato=1;
                        break;
                    }
            }
            if(trovato==0){
                    CasaVacanze cv = new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi), ((Host) utenteCorrente));
                    stcorrente = cv;
                   // aggiungiOsservatore(cv);
            }
        } else {
            CasaVacanze cv = new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi), ((Host) utenteCorrente));
            stcorrente = cv;
           // aggiungiOsservatore(cv);
        }
    }

    public void inserisciTariffa(String nome, int datainizio, int datafine, float fattoreMoltiplicativo){
        stcorrente.inserisciTariffa(nome,datainizio,datafine,fattoreMoltiplicativo);
    }

    public void inserisciStanza(String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        int id=generaidStanza();
        if(stcorrente != null){
            Stanza st=((Beb)stcorrente).inserisciStanza(id,nome,nospiti,prezzopernotte,dimensione,descrizione,getServices(listacodservizi),((Beb)stcorrente));
          //  aggiungiOsservatore(st);
        }else{
            System.out.println("Devi prima inserire un B&B");
        }
    }

    public void confermaCasaVacanze(){
        mapStrutture.put(stcorrente.getId(),stcorrente);
        ((Host)utenteCorrente).addStruttura(stcorrente);
        stcorrente=null;
    }

    public void confermaBeB(){
        mapStrutture.put(stcorrente.getId(), stcorrente);
        ((Host)utenteCorrente).addStruttura(stcorrente);
        stcorrente=null;
    }

//UC2 GESTIONE PRENOTAZIONE ALLOGGIO
    public ArrayList<Struttura> prenotaAlloggio(String citta, LocalDate dataInizio, LocalDate dataFine, int nOspiti) {
        pre=new Prenotazione(dataInizio,dataFine,nOspiti);
        ArrayList<Struttura> listaStruCorrenteDisp= new ArrayList<>();
        ArrayList<Stanza> listaStanzeDisp = new ArrayList<>();
        for (Struttura struttura : mapStrutture.values()) {
            if (struttura.equalsCitta(citta)) {
                if (struttura instanceof Beb) {
                    listaStanzeDisp = ((Beb)struttura).isAvaible(dataInizio, dataFine, nOspiti);
                    if (!listaStanzeDisp.isEmpty()) {
                        listaStruCorrenteDisp.add(struttura);
                    }
                } else if (struttura instanceof CasaVacanze) {
                    CasaVacanze cv = (CasaVacanze) struttura;
                    if(cv.isAvailable(dataInizio, dataFine, nOspiti))
                        listaStruCorrenteDisp.add(struttura);
                }
            }
        }
        return listaStruCorrenteDisp;
    }

    public Struttura selezionaStruttura(int id) {
        Struttura st = mapStrutture.get(id);
        if (st != null) {
            strutturascelta = st;
            if (st instanceof CasaVacanze) {
                pre.setId(generanumeroPrenotazione());
                pre.setStato("In prenotazione...");
                pre.setStruttu(st);
                pre.setCl((Cliente) utenteCorrente);
            }
            return st;
        }
        return null;
    }

    public ArrayList<Stanza> getStanzeDisp(){
        return ((Beb)strutturascelta).isAvaible(pre.getDatainizio(),pre.getDatafine(),pre.getnOspiti());
    }

    public void selezionaStanza(int id){
                    pre.setId(generanumeroPrenotazione());
                    pre.setStato("In prenotazione...");
                    pre.setSt(((Beb)strutturascelta).selStanza(id));
                    pre.setStruttu(strutturascelta);
                    pre.setCl((Cliente)utenteCorrente);
    }

    public void confermaPrenotazione(){
        if(pre!=null){
            pre.setStato("Prenotato");
            pre.setCostoTotale();
            ((Cliente)utenteCorrente).addPrenotazione(pre);
            if(strutturascelta instanceof CasaVacanze){
                ((CasaVacanze)strutturascelta).addPrenotazione(pre);
            }
            else{
                ((Beb)strutturascelta).addPrenotazione(pre);
            }
            listaPrenotazioni.put(pre.getId(),pre);
        } else {
            System.out.println("Prenotazione inesistente");
        }
    }

//UC3 VISUALIZZA PRENOTAZIONI SU STRUTTURA
    public Map<Integer,Struttura> visualizzaPrenotazioni(){
        return ((Host)utenteCorrente).getListaStrutture();
    }

    public Map<Integer,Prenotazione> selezionaStrutturaVis(int id){
        Struttura st = ((Host)utenteCorrente).getListaStrutture().get(id);
        Map<Integer,  Prenotazione> prenotazioni = new HashMap<>();
        if(st instanceof CasaVacanze){
           return ((CasaVacanze)st).getListaprenotazioni();
        }else{
            return ((Beb)st).getListaPrenStanze();
        }
    }

//UC4 VISUALIZZA PRENOTAZIONI EFFETTUATE

    public Map<Integer,Prenotazione> visualizzaPrenotazioniCliente( LocalDate dataInizio, LocalDate dataFine){
        Map<Integer, Prenotazione> map = ((Cliente)utenteCorrente).getListaPrenotazioniClienti(dataInizio, dataFine);
        return map;
    }

//UC5 GESTIONE ANNULLAMENTO PRENOTAZIONE
    public Map<Integer, Prenotazione> annullaPrenotazione(){
        return ((Cliente)utenteCorrente).getListaPrenotazioniClientiAnnullabili();
    }

    public boolean selezionaPrenotazione(int id){
            pre = ((Cliente)utenteCorrente).getPrenotazione(id);
        if (pre != null){
            pre.setStato("Prenotazione annullata");
            return true;
        }else{
            return false;
        }
    }

//UC6 RECENSIONE STRUTTURA

    public Map<Integer, Struttura> nuovaRecensione(){
        return ((Cliente)utenteCorrente).nuovaRecensione();
    }

    public Struttura selezionaStrutturaRec(int id){
        strutturascelta = mapStrutture.get(id);
        return mapStrutture.get(id);
    }

    public void inserisciRecensione(int valutazione, String commento){
        int id=generanumeroRecensione();
       Recensione re =  ((Cliente)utenteCorrente).inserisciRecensione(id,valutazione, commento,(Cliente)utenteCorrente,strutturascelta);
       strutturascelta.addRecensione(re);
       strutturascelta = null;
    }

//UC8 CREAZIONE ACCOUNT UTENTE

    public boolean registrazioneHost(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email, String piva, String sFiscale, String residenza){
        for(Utente ut: listaUtenti.values()){
           if(ut.getCodicefiscale().equals(cf) && ut instanceof Host){
               return false;
           }
       }
        utenteCorrente = new Host(getId(),nome, cognome, dataNascita, cf,nTelefono, email, piva, sFiscale, residenza);
        return true;
    }

    public boolean registrazioneCliente(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email){
        for(Utente ut: listaUtenti.values()){
            if(ut.getCodicefiscale().equals(cf) && ut instanceof Cliente){
                return false;
            }
        }
        utenteCorrente = new Cliente(getId(), nome, cognome, dataNascita, cf, nTelefono, email);
        return true;
    }

    public boolean registrazionePartner(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email, int nlicenza){
        for (Utente ut: listaPartner.values()){
            if (ut.getCodicefiscale().equals(cf) && ut instanceof PartnerAssicurativo){
                return false;
            }
        }


        utenteCorrente = new PartnerAssicurativo(getId(), nome, cognome, dataNascita, cf, nTelefono, email, nlicenza);
        return true;
    }

    public ArrayList<Abbonamento> getListaAbbonamneto() {
        return listaAbbonamneto;
    }

    public void selezionaAbbonamento(int id){
        for(int i=0;i<listaAbbonamneto.size();i++){
            if(listaAbbonamneto.get(i).getId()==id){
                ((Host)utenteCorrente).setSottoscrizione(listaAbbonamneto.get(i));
                break;
            }
        }
    }

    public void confermaRegistrazione(){
        if(utenteCorrente instanceof PartnerAssicurativo){
            listaPartner.put(utenteCorrente.getId(), utenteCorrente);
        }else{
            listaUtenti.put(utenteCorrente.getId(), utenteCorrente);
            System.out.println("Account Inserito\n");
        }

    }

//UC13 COMMENTA RECENSIONE
    public Map<Integer, Struttura> commentaRecensione(){
        return ((Host)utenteCorrente).getListaStrutture();
    }

    public Struttura selStrut(int id){
        strutturascelta=mapStrutture.get(id);
        return mapStrutture.get(id);
    }

    public ArrayList<Recensione> getRecensioniComm(){
        ArrayList<Recensione> listaRecComm = new ArrayList<>();
        listaRecComm=strutturascelta.getRecComm();
        return listaRecComm;
    }

    public void inserisciCommento(String commentoHost,int id){
        strutturascelta.inserisciCommentoHost(commentoHost,id);
        strutturascelta = null;
    }

//UC11 RICHIESTA ASSSITENZA
    private CategoriaAssistenza catcorrente;

    public ArrayList<CategoriaAssistenza> assistenza() {
        ArrayList<CategoriaAssistenza> listaCat = new ArrayList<>();
        for (CategoriaAssistenza cat : CategoriaAssistenza.values()) {
            listaCat.add(cat);
        }
        return listaCat;
    }


    public CategoriaAssistenza selCat(String cat){
        catcorrente=CategoriaAssistenza.valueOf(cat);
        return catcorrente;
    }

    public Map<Integer, Prenotazione> getListaPrenotazioni(){
        if (utenteCorrente instanceof Cliente){
            return (((Cliente) utenteCorrente).getListaPrenotazioni());
        } else if (utenteCorrente instanceof Host) {
            return ((Host)utenteCorrente).getListaPrenotazioni();
        }else{
            return null;
        }
    }

    public void selPrenotazione(int idPren){
        if (utenteCorrente instanceof Cliente){
            pre = ((Cliente)utenteCorrente).getListaPrenotazioniClienti(idPren);
        }else if(utenteCorrente instanceof Host){
            pre = ((Host)utenteCorrente).getListPrenotazioneDisp(idPren);
        }
    }

    public ArrayList<Recensione> getListaRec(){
        if (utenteCorrente instanceof Cliente){
            return ((Cliente)utenteCorrente).getListaRecensioni();
        }else if(utenteCorrente instanceof Host){
            return ((Host)utenteCorrente).getListaRecensioni();
        }
        return null;
    }

    public void selRecensione(int idRec){
        if (utenteCorrente instanceof Cliente){
            recScelta = ((Cliente)utenteCorrente).selRecensione(idRec);
        } else if (utenteCorrente instanceof Host) {
            recScelta = ((Host)utenteCorrente).selRecensione(idRec);
        }
    }

   public void richiestaAssistenza(String descrizione){
       String stato="In elaborazione";

       switch (catcorrente.name()){
           case "PRENOTAZIONE":
               int id = pre.getId();
               if(utenteCorrente instanceof Cliente || utenteCorrente instanceof Host){
                       richiestaAssistenzaCorrente = (utenteCorrente).richiestaAssistenzaPrenotazione(generaNumeroAssistenza(),descrizione, pre,stato);
               }
               pre = null;
               break;
           case "RECENSIONE":
               if(utenteCorrente instanceof Cliente || utenteCorrente instanceof Host){
                   richiestaAssistenzaCorrente = (utenteCorrente).richiestaAssistenzaRecensione(generaNumeroAssistenza(),descrizione,recScelta,stato);
               }
               recScelta = null;
               break;
           case "ALTRI_MOTIVI":
               richiestaAssistenzaCorrente = utenteCorrente.richiestaAssistenza(generaNumeroAssistenza(),descrizione,stato);
       }

   }

    public void confermaAssistenza(){
        if(richiestaAssistenzaCorrente != null){
            listaAssistenza.put(richiestaAssistenzaCorrente.getId(), richiestaAssistenzaCorrente);
            utenteCorrente.confermaAssistenza();
        }
        richiestaAssistenzaCorrente = null;
        catcorrente = null;
    }

//UC12 GESTISCI RICHIESTA ASSISTENZA

    public Map<Integer,RichiestaAssistenza> risolviAssistenza(){
        Map<Integer,RichiestaAssistenza> listaAssRis=new HashMap<>();
        for(RichiestaAssistenza ra: listaAssistenza.values()){
            if(ra.isntRisolta())
            listaAssRis.put(ra.getId(),ra);
        }
        return listaAssRis;
    }

    public RichiestaAssistenza selezionaRAssistenza(int id){
        richiestaAssistenzaScelta = listaAssistenza.get(id);
        return richiestaAssistenzaScelta;
    }

    public void generaMessaggio(String descrizioneSo){
        Messaggio mess=new Messaggio(richiestaAssistenzaScelta.getListaMessaggi().size()+1,descrizioneSo);
        richiestaAssistenzaScelta.addMessaggio(mess);
    }

    public void confermaMessaggio(){
        if(richiestaAssistenzaScelta != null) richiestaAssistenzaScelta.setStato("Chiuso");
        richiestaAssistenzaScelta = null;
    }

    //UC07
    public void inseriNuovaPolizza( String tipo, String copertura, int durata, String stato){
        ((PartnerAssicurativo)utenteCorrente).inseriNuovaPolizza( tipo, copertura, durata, stato);
    }

    public void confermaInserimento(){
        ((PartnerAssicurativo)utenteCorrente).confermaInserimento();
    }

    public void annullaInserimento(){
        ((PartnerAssicurativo)utenteCorrente).annullaInserimento();

    }


    //UC9

    public ArrayList<Recensione> visualizzaRecensioni(){
        return ((Host)utenteCorrente).visualizzaRecensioni();
    }

    //UC10

    public ArrayList<Prenotazione> aggiungiAssicurazione(){
        return ((Cliente)utenteCorrente).getPrenotazioniAss();
    }

    public Prenotazione selPren(int id){
        pre = ((Cliente)utenteCorrente).selezionaPrenotazione(id);
        return pre;
    }

    public ArrayList<PolizzaAssicurativa> mostraPolizze(){
        int durata = pre.getDurata();
        ArrayList<PolizzaAssicurativa> listaPolizze = new ArrayList<>();
        for (Utente ut : listaPartner.values()){
            ArrayList<PolizzaAssicurativa> sv = ((PartnerAssicurativo)ut).mostraPolizze(durata);
           if (!sv.isEmpty()){
               listaPolizze.addAll(sv);
           }
        }
        return listaPolizze;
    }

    public void selezionaPolizza(int idPartner, int idPolizza){
        PartnerAssicurativo pa = (PartnerAssicurativo) listaPartner.get(idPartner);
        PolizzaAssicurativa po = pa.getPolizza(idPolizza);
       pre.addPolizza(po);
    }

    public void confermaAssicurazione(){
        pre.setStato("Prenotazione Assicurata");
        pre = null;
    }

    //UC1 ESTENSIONI
    public Map<Integer, Struttura> modificaStruttura(){
        return ((Host)utenteCorrente).getListaStrutture();
    }

    //Struttura selezionata

    public void modificaCV(String nome, String descrizione, ArrayList<Integer> listcodserv, float prezzoNotte){
        if(listcodserv!=null)
        ((CasaVacanze)strutturascelta).aggiornaStruttura(nome, descrizione, getServices(listcodserv), prezzoNotte);
        else
            ((CasaVacanze)strutturascelta).aggiornaStruttura(nome, descrizione, null, 0);
    }

    public void modificaBeb(String nome, String descrizione){
        ((Beb)strutturascelta).aggiornaStruttura(nome, descrizione);
    }

    public Map<String, Stanza> getStanze(){
        return ((Beb)strutturascelta).getListaStanze();
    }

    public Stanza selStanza(String id){
        return ((Beb)strutturascelta).selStanza(id);
    }

    public void modStanza(String nome, String descrizione, ArrayList<Integer> listcodserv, float prezzopernotte){
        if(listcodserv!=null)
        ((Beb)strutturascelta).modStanza(nome, descrizione, getServices(listcodserv), prezzopernotte);
        else
            ((Beb)strutturascelta).modStanza(nome, descrizione, null, prezzopernotte);
    }

//UC7 ESTENSIONE ELIMINA E DISATTIVA
    public Map<Integer,PolizzaAssicurativa> deldisPolizza(){
       return ( (PartnerAssicurativo)utenteCorrente).getListapolizze();
    }

    public PolizzaAssicurativa selPolizza(int id){
        return ( (PartnerAssicurativo)utenteCorrente).selectPolizza(id);
    }

    public boolean delPolizza(){
        return ( (PartnerAssicurativo)utenteCorrente).delPolizza();
    }

    public boolean disPolizza(){
        return ( (PartnerAssicurativo)utenteCorrente).disPolizza();
    }

//metodi secondari

    public Beb getBebCorrente(){
        return (Beb)stcorrente;
    }

    public CasaVacanze getcvCorrente(){
        return (CasaVacanze)stcorrente ;
    }

    public Struttura getStcorrente() {
        return stcorrente;
    }

    public Map<Integer,Struttura> getElencoStrutture(){
        return mapStrutture;
    }

    public Prenotazione getPrenotazioneCorrente(){
        return pre;
    }

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    private int generaNumeroAssistenza(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if (!listaAssistenza.containsKey(randomNumber)){
                return randomNumber;
            }
        }
    }

    public Map<Integer, Utente> getListaPartner() {
        return listaPartner;
    }

    private int generaidStruttura(){
       return mapStrutture.values().size()+1;
    }

    private int generaidStanza(){
        return ((Beb)stcorrente).getListaStanze().size()+1;
    }

    public Utente getUtente(int id) {
        if (listaUtenti.containsKey(id)){
            return listaUtenti.get(id);
        }else{
            return listaPartner.get(id);
        }
    }

    public Map<Integer, Prenotazione> getListaPrenotazioniroom(){
        return listaPrenotazioni;
    }

    public void logIn(int id){
        if (listaUtenti.containsKey(id)){
            utenteCorrente=listaUtenti.get(id);
        }else{
            utenteCorrente = listaPartner.get(id);
        }
    }

    public void logout() {
        utenteCorrente = null;
    }

    public int getId(){
        return listaUtenti.size()+listaPartner.size()+1;
    }

    private ArrayList<Servizio> getServices(ArrayList<Integer> serv){
        ArrayList<Servizio> sv = new ArrayList<>();
        for(int i = 0; i < serv.size(); i++){
            sv.add(servizi.get(serv.get(i)));
        }
        return sv;
    }

    public Map<Integer, Struttura> getMapStrutture() {
        return mapStrutture;
    }

    public Map<Integer, Utente> getListaUtenti() {
        return listaUtenti;
    }

    public Map<Integer, RichiestaAssistenza> getListaAssistenza() {
        return listaAssistenza;
    }

    public Struttura getStrutturascelta() {
        return strutturascelta;
    }

    public void setStcorrente(Struttura stcorrente) {
        this.stcorrente = stcorrente;
    }

    public HashMap<Integer, Servizio> getServizi() {
        return servizi;
    }

    public Map<Integer, RichiestaAssistenza> visualizzaAssistenza(){
        return utenteCorrente.getListaAssistenza();
    }


    public int generanumeroPrenotazione(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if(!listaPrenotazioni.isEmpty()) {
                if (!listaPrenotazioni.containsKey(randomNumber)) {
                    return randomNumber;
                }
            }else
                return randomNumber;
        }
    }

    public int generanumeroRecensione(){
        Random random=new Random();
        boolean var1=false;

        while(true){
            int randomNumber=random.nextInt(200000);
            for(Struttura strut: mapStrutture.values()){
                if(!strut.getListRecensioni().isEmpty()) {
                    for (int i = 0; i < strut.getListRecensioni().size(); i++) {
                        if (strut.getListRecensioni().get(i).getId() != randomNumber) {
                            var1 = true;
                        }
                    }
                }else
                    var1=true;
            }
            if(var1){
                return randomNumber;
            }
        }

    }

    //ASSISTENZA
    public Map<Integer, RichiestaAssistenza> visualizzaMessaggi(){
        return utenteCorrente.getListaAssistenza();
    }

    public ArrayList<Messaggio> visualizzaRisposte(int id){
        return utenteCorrente.getListaAssistenza().get(id).getListaMessaggi();
    }

    //risposta messaggio
    public ArrayList<Recensione> visualizzaRecensioniCommentati(){
       return ((Cliente)utenteCorrente).getRecensioniCommentati();
    }

    public String visualizzaRispostaHost(int id){
        return ((Cliente)utenteCorrente).visualizzaRispostaHost(id);
    }
}


