package com.Roomify;

import java.util.*;
import java.time.LocalDate;

public class Roomify {

    private static Roomify roomify;
    private Beb bebcorrente;
    private CasaVacanze cvcorrente;
    private Struttura stcorrente;
    private Map<Integer,Struttura> mapStrutture;
    private HashMap<Integer, Servizio> servizi;
    private Map<Integer,Prenotazione> listaprenotazioni;
    private List<PrenotazioniObserve> osservatori = new ArrayList<>();
    private LocalDate corrDataInizio;
    private LocalDate corrDataFine;
    private int corrNumOspiti;
    private Prenotazione pre;
    private Map<Integer,Utente> listaUtenti=new HashMap<>();
    private Utente utenteCorrente;
    private ArrayList<Abbonamento> listaAbbonamneto;
    private Struttura strutturascelta;

    private Roomify() {
        this.mapStrutture = new HashMap<>();
        this.listaprenotazioni = new HashMap<>();
        populate();
    }

    private void populate() {

        this.listaAbbonamneto = new ArrayList<>();
        listaAbbonamneto.add(new Abbonamento(1,"1 Mese", 1,10));
        listaAbbonamneto.add(new Abbonamento(2,"3 Mesi", 3, 20));
        listaAbbonamneto.add(new Abbonamento(3,"6 Mese", 6, 40));
        listaAbbonamneto.add(new Abbonamento(4,"12 Mesi", 12,50));


        servizi = new HashMap<>();
        servizi.put(1, new Servizio(1, "Lavatrice", "Elettrodomestico utilizzato per lavare i vestiti"));
        servizi.put(2, new Servizio(2, "Microonde", "Elettrodomestico per riscaldare o cucinare velocemente"));
        servizi.put(3, new Servizio(3, "Lavastoviglie", "Elettrodomestico utilizzato per lavare i piatti"));
        servizi.put(4, new Servizio(4, "Vasca", "Struttura per il bagno e il relax"));
        servizi.put(5, new Servizio(5, "Aria Condizionata", "Sistema di climatizzazione"));
        servizi.put(6, new Servizio(6, "WiFi", "Connessione Internet senza fili"));
        servizi.put(7, new Servizio(7, "TV", "Televisione con canali via cavo"));
        servizi.put(8, new Servizio(8, "Piscina", "Piscina esterna per gli ospiti"));
        servizi.put(9, new Servizio(9, "Parcheggio gratuito", "Parcheggio privato incluso"));

        listaUtenti = new HashMap<>();


        listaUtenti.put(1, new Cliente(1,"Enricomaria", "Di Rosolini", LocalDate.of(1999, 1, 13), "DRS", "email@test.com", "23232"));
        listaUtenti.put(2, new Host(2,"Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II"));
        listaUtenti.put(3, new Cliente(3,"Giulia", "Bianchi", LocalDate.of(1995, 5, 20), "GBI", "giulia@test.com", "34567"));
        listaUtenti.put(4, new Host(4,"Marco", "Rossi", LocalDate.of(1988, 2, 14), "MRO", "marco.host@test.com", "56789", "3498765432", "IT", "Via Veneto 12"));
        listaUtenti.put(5, new Cliente(5,"Luca", "Verdi", LocalDate.of(1990, 11, 8), "LVE", "luca@test.com", "78901"));
        listaUtenti.put(6, new Host(6,"Elena", "Neri", LocalDate.of(1985, 7, 2), "ENE", "elena.host@test.com", "89012", "3912345678", "IT", "Piazza Duomo 8"));
        listaUtenti.put(7, new Cliente(7,"Francesca", "Russo", LocalDate.of(1993, 3, 25), "FRU", "francesca@test.com", "90123"));
        listaUtenti.put(8, new Host(8,"Antonio", "Ferrari", LocalDate.of(1979, 9, 30), "AFE", "antonio.host@test.com", "67890", "3123456789", "IT", "Corso Italia 5"));

        ArrayList<Integer> listcod1 = new ArrayList<>(Arrays.asList(1, 4, 6, 8));
        ArrayList<Integer> listcod2 = new ArrayList<>(Arrays.asList(2, 3, 5, 7));

        login(listaUtenti.get(4));
        inserisciCasavacanza( "Villa Sole", "Splendida villa con piscina e vista mare.", "Italia", "Palermo", "PA", 90100, "Via Roma, 10", 6, 4, 120.50f, 150, listcod1 );
        confermaCasaVacanze();
        logout();

        login(listaUtenti.get(2));
        inserisciCasavacanza( "Casa Luna", "Accogliente casa nel centro storico", "Italia", "Roma", "RM", 00100, "Via dei Fori, 5", 4, 3, 80.00f, 90, listcod2);
        confermaCasaVacanze();

        inserisciCasavacanza( "Villa Aurora", "Villa di lusso con ampio giardino", "Italia", "Milano", "MI", 20100, "Corso Magenta, 25", 8, 5, 200.00f, 180, listcod1);
       confermaCasaVacanze();
        logout();

        login(listaUtenti.get(6));
        inserisciBeB( "La Dolce Mela", "Un bellissimo B&B immerso nel verde", "Italia", "Taormina", "Messina", 98034, "Via Mazzini 12");
        inserisciStanza( "Camera Standard", 2, 60, 100, "Camera economica e accogliente", listcod1);
        inserisciStanza( "Camera Deluxe", 4, 100, 150, "Camera spaziosa e confortevole", listcod2);
        inserisciStanza( "Suite Panoramica", 3, 140, 200, "Vista mozzafiato sulla città", listcod1);
        confermaBeB();

        inserisciBeB( "La Dolce pera", "Un bellissimo B&B immerso nel verde", "Italia", "Taormina", "Messina", 98034, "Via Mazzini 12");
        inserisciStanza( " Standard", 2, 60, 100, "Camera economica e accogliente", listcod1);
        inserisciStanza( " Deluxe", 4, 100, 150, "Camera spaziosa e confortevole", listcod2);
        inserisciStanza( " Panoramica", 3, 140, 200, "Vista mozzafiato sulla città", listcod1);
        confermaBeB();

        inserisciBeB( "B&B Il Glicine", "Struttura romantica con colazione inclusa", "Italia", "Firenze", "FI", 50100, "Piazza della Signoria 7");
        inserisciStanza( "Suite Elegance", 3, 120, 180, "Camera lussuosa con vista panoramica", listcod1);
        inserisciStanza( "Camera Comfort", 3, 75, 110, "Perfetta per il relax", listcod2);
        inserisciStanza( "Junior Suite", 5, 140, 210, "Ampia suite con tutti i comfort", listcod1);
        inserisciStanza( "Mansarda Deluxe", 4, 110, 170, "Elegante mansarda con terrazza", listcod2);
        confermaBeB();
        logout();

        login(listaUtenti.get(8));
        inserisciBeB( "B&B Sole e Mare", "Perfetto per chi ama il mare", "Italia", "Napoli", "NA", 80100, "Via Caracciolo 21");
        inserisciStanza( "Camera Marina", 3, 85, 130, "Camera con vista mare", listcod1);
        inserisciStanza( "Camera Blue", 4, 95, 140, "Camera con colori rilassanti", listcod2);
        inserisciStanza( "Penthouse Suite", 5, 200, 250, "Suite esclusiva con terrazza privata", listcod1);
        confermaBeB();

        inserisciBeB( "B&B Il Bosco", "Circondato dal verde per un totale relax", "Italia", "Torino", "TO", 10100, "Strada del Bosco 45");
        inserisciStanza( "Camera Rustica", 3, 70, 120, "Camera con arredamento rustico", listcod1);
        inserisciStanza( "Camera Family", 4, 90, 150, "Perfetta per famiglie numerose", listcod2);
        inserisciStanza( "Suite Romantica", 5, 130, 190, "Suite perfetta per coppie", listcod1);
        inserisciStanza( "Loft Moderno", 4, 115, 175, "Ampio loft con arredamento moderno", listcod2);
        confermaBeB();
        logout();

        Cliente cliente1 = (Cliente) listaUtenti.get(1);
        Cliente cliente2 = (Cliente) listaUtenti.get(3);
        Cliente cliente3 = (Cliente) listaUtenti.get(5);
        Cliente cliente4 = (Cliente) listaUtenti.get(7);

        // Prenotazione per un B&B a Taormina
        login(cliente1);
        ArrayList<Struttura> listaDisp1 = prenotaAlloggio("Taormina", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 13), 2);
        Struttura st1 = selezionaStruttura(4);
        selezionaStanza(1, st1);
        confermaPrenotazione();

        //la ricerca funziona ma la struttura scelta non rispecchia la ricerca -- prima era Taormina
        ArrayList<Struttura> listaDisp11 = prenotaAlloggio("Torino", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 13), 2);
        Struttura st11 = selezionaStruttura(8);
        selezionaStanza(1, st11);
        confermaPrenotazione();
        stcorrente = st11;
        inserisciRecensione(3, "CIAO GAB <3");
        stcorrente = null;

        ArrayList<Struttura> listaDisp111 = prenotaAlloggio("Taormina", LocalDate.of(2026, 5, 10), LocalDate.of(2026, 6, 13), 2);
        Struttura st111 = selezionaStruttura(4);
        selezionaStanza(1, st111);
        confermaPrenotazione();
        cliente3.addRecensione(new Recensione(1, 3, "CIAO st1", cliente3, st1));

        logout();

        // Prenotazione per un B&B a Firenze
        login(cliente2);
        ArrayList<Struttura> listaDisp2 =prenotaAlloggio("Firenze", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 15), 3);
        Struttura st2 = selezionaStruttura(5);
        selezionaStanza(2, st2);
        confermaPrenotazione();
        cliente3.addRecensione(new Recensione(2, 3, "CIAO st2", cliente3, st2));
        logout();

        // Prenotazione per un B&B a Napoli
        login(cliente3);
        ArrayList<Struttura> listaDisp3 = prenotaAlloggio("Napoli", LocalDate.of(2025, 8, 5), LocalDate.of(2025, 8, 12), 4);
        Struttura st3 = selezionaStruttura(6);
        selezionaStanza(3, st3);
        confermaPrenotazione();
        //cliente3.addRecensione(new Recensione(3, 3, "CIAO st3", cliente3, st3));

        logout();

        // Prenotazione per un B&B a Torino
        login(cliente4);
        ArrayList<Struttura> listaDisp4 = prenotaAlloggio("Torino", LocalDate.of(2025, 9, 10), LocalDate.of(2025, 9, 20), 4);
        Struttura st4 = selezionaStruttura(8);
        selezionaStanza(3, st4);
        confermaPrenotazione();
        stcorrente = st4;
        inserisciRecensione(3, "CIAO");
        stcorrente = null;
        logout();
    }

    public static Roomify getInstance() {
        if (roomify == null)
            roomify = new Roomify();
        else
            System.out.println("Istanza già creata");

        return roomify;
    }

//UC1 GESTIONE STRUTTURA
    public void inserisciBeB( String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo){
        int id=generaidStruttura();
        int trovato=0;
        if(mapStrutture.size()!=0) {
            for(Struttura str: mapStrutture.values()) {
                if (str.getNome().equals(nome) && str.getPaese().equals(paese) && str.getCitta().equals(citta) && str.getProvincia().equals(provincia) && str.getIndirizzo().equals(indirizzo)) {
                    trovato=1;
                    System.out.println("La struttura è già presente nel sistema");
                    break;
                }
            }
            if(trovato==0) {
                this.bebcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
                System.out.println("B&B inserito nella struttura corrente");
            }
        } else{
            this.bebcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
            System.out.println("B&B inserito nella struttura corrente");
        }
    }

    public void inserisciCasavacanza( String nome, String descrizione, String paese, String citta, String provincia, int  cap, String indirizzo,int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Integer> listaCodServizi){
        int id=generaidStruttura();
        int trovato=0;
        if(mapStrutture.size()!=0) {
            for (Struttura str : mapStrutture.values()) {
                    if (str.getNome().equals(nome) && str.getPaese().equals(paese) && str.getCitta().equals(citta) && str.getProvincia().equals(provincia) && str.getIndirizzo().equals(indirizzo)) {
                        trovato=1;
                        System.out.println("La struttura è già presente nel sistema");
                        break;
                    }
            }
            if(trovato==0){
                    CasaVacanze cv = new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi), ((Host) utenteCorrente));
                    this.cvcorrente = cv;
                    System.out.println("Casa Vacanza inserita nella struttura corrente");
                    aggiungiOsservatore(cv);
            }
        } else {
            CasaVacanze cv = new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi), ((Host) utenteCorrente));
            this.cvcorrente = cv;
            System.out.println("Casa Vacanza inserita nella struttura corrente");
            aggiungiOsservatore(cv);
        }
    }

    public void inserisciStanza(String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        int id=generaidStanza();
        if(bebcorrente != null){
            Stanza st=this.bebcorrente.inserisciStanza(id,nome,nospiti,prezzopernotte,dimensione,descrizione,getServices(listacodservizi),bebcorrente);
            aggiungiOsservatore(st);
            System.out.println("Stanza inserita in beb"+bebcorrente.getNome());
        }else{
            System.out.println("Devi prima inserire un B&B");
        }
    }

    public void confermaCasaVacanze(){
        mapStrutture.put(cvcorrente.getId(),cvcorrente);
        Map<Integer,Struttura> listastru=((Host)utenteCorrente).getListaStrutture();
        listastru.put(cvcorrente.getId(),cvcorrente);
        ((Host)utenteCorrente).setListaStrutture(listastru);
        cvcorrente=null;
        stcorrente=null;
    }

    public void confermaBeB(){
        mapStrutture.put(bebcorrente.getId(), bebcorrente);
        Map<Integer,Struttura> listastru=((Host)utenteCorrente).getListaStrutture();
        listastru.put(bebcorrente.getId(),bebcorrente);
        ((Host)utenteCorrente).setListaStrutture(listastru);
        bebcorrente=null;
        stcorrente=null;
    }

//UC2 GESTIONE PRENOTAZIONE ALLOGGIO
    public ArrayList<Struttura> prenotaAlloggio(String città, LocalDate dataInizio, LocalDate dataFine, int nOspiti) {
        corrDataFine = dataFine;
        corrDataInizio = dataInizio;
        corrNumOspiti = nOspiti;
        ArrayList<Struttura> listaStruCorrenteDisp= new ArrayList<>();
        ArrayList<Stanza> listaStanzeDisp = new ArrayList<>();
        for (Struttura struttura : mapStrutture.values()) {
            if (struttura.getCitta().equals(città)) {
                if (struttura instanceof Beb) {
                    listaStanzeDisp = ((Beb)struttura).isAvaible(dataInizio, dataFine, nOspiti);
                    if (!listaStanzeDisp.isEmpty()) {
                        listaStruCorrenteDisp.add(struttura);
                    }
                } else if (struttura instanceof CasaVacanze) {
                    CasaVacanze cv = (CasaVacanze) struttura;
                    if (cv.getnMaxOspiti() >= nOspiti) {
                        if(!(((CasaVacanze) cv).getListaprenotazioni().isEmpty())){
                        for (Prenotazione pren : ((CasaVacanze) cv).getListaprenotazioni().values()) {
                            if (!(dataInizio.isAfter(pren.getDatainizio()) && dataInizio.isBefore(pren.getDatafine()))) {
                                listaStruCorrenteDisp.add(struttura);
                                }
                            }
                        }else  listaStruCorrenteDisp.add(struttura);
                    }
                }
            }
        }
        return listaStruCorrenteDisp;
    }

    public Struttura selezionaStruttura(int id){
       for (Struttura st : mapStrutture.values()){
           if (st.getId() == id){
               strutturascelta=st;
               if(st instanceof CasaVacanze) {
                   pre= new Prenotazione();
                   pre.setId(generaNumeroPrenotazione());
                   pre.setDatainizio(corrDataInizio);
                   pre.setDatafine(corrDataFine);
                   pre.setStato("In prenotazione...");
                   pre.setStruttu(st);
                   pre.setCl((Cliente)utenteCorrente);
               }
               return st;
           }
       }
       return null;
    }

    public void selezionaStanza(int id, Struttura str){
        if (strutturascelta instanceof Beb){
            ArrayList<Stanza> st = ((Beb)strutturascelta).isAvaible(corrDataInizio, corrDataFine, corrNumOspiti);
            for(int i = 0; i < st.size(); i++){
                if (st.get(i).getId() == id){
                    pre= new Prenotazione();
                    pre.setId(generaNumeroPrenotazione());
                    pre.setDatainizio(corrDataInizio);
                    pre.setDatafine(corrDataFine);
                    pre.setStato("In prenotazione...");
                    pre.setSt(st.get(i));
                    pre.setStruttu(str);
                    pre.setCl((Cliente)utenteCorrente);
                }
            }
        }
    }

    public void confermaPrenotazione(){
        if(pre!=null){
            pre.setStato("Prenotato");
            ((Cliente)utenteCorrente).addPrenotazione(pre);
            listaprenotazioni.put(pre.getId(), pre);
            System.out.println("Inserimento completato, buon viaggio");
            notificaOsservatori();
        } else {
            System.out.println("Prenotazione inesistente");
        }
    }

//UC3 VISUALIZZA PRENOTAZIONI STRUTTURA
    public void visualizzaPrenotazioni(){
        for(Struttura struttura : ((Host)utenteCorrente).getListaStrutture().values()){
            System.out.println(struttura.toString());
        }
    }

    public void selezionaStrutturaVis(int id){
        Struttura st = ((Host)utenteCorrente).getListaStrutture().get(id);
        if(st instanceof CasaVacanze){
            for(Prenotazione pren: ((CasaVacanze)st).getListaprenotazioni().values()){
                System.out.println(pren.toString());
            }
        }else{
            Map<String, Stanza> stn = ((Beb)st).getListaStanze();
            for(Stanza stnn : stn.values()){
                for(Prenotazione pren : stnn.getListaprenotazioni().values()){
                    System.out.println(pren.toString());
                }
            }
        }
    }

//UC4 VISUALIZZA PRENOTAZIONI EFFETTUATE
    public Map<Integer, Utente> getListaUtenti() {
        return listaUtenti;
    }

    public void visualizzaPrenotazioni( LocalDate dataInizio, LocalDate dataFine){
        Map<Integer, Prenotazione> map = ((Cliente)utenteCorrente).getListaPrenotazioniClienti(dataInizio, dataFine);
        if (!map.isEmpty()){
            System.out.println("Ecco le prenotazioni: \n");
            for (Prenotazione pre : map.values()){
                System.out.println(pre.toString());
            }
        }else{
            System.out.println("Nessuna prenotazione nel periodo selezionato.");
        }
    }

//UC5 GESTIONE ANNULLAMENTO PRENOTAZIONE
    public Map<Integer, Prenotazione> annullaPrenotazione(){
        return ((Cliente)utenteCorrente).getListaPrenotazioniClienti();
    }

    public void selezionaPrenotazione(int id){
        pre = ((Cliente)utenteCorrente).getListaPrenotazioniClienti().get(id);
        if (LocalDate.now().isBefore(pre.getDatainizio())){
            pre.setStato("Prenotazione annullata");
            System.out.println("Prenotazione annullata");
        }else{
            System.out.println("Stato non modificato, la prenotazione è già avvenuta.");
        }
    }

//UC6 RECENSIONE STRUTTURA
    public Map<Integer, Struttura> nuovaRecensione(){
       Map<Integer, Struttura> listStruRecensibili = new HashMap<>();
       int trovato=0;
        for (Prenotazione pre: ((Cliente) utenteCorrente).getListaPrenotazioniClienti().values()){
            ArrayList<Recensione> listRec= ((Cliente)utenteCorrente).getListaRecensioni();
            if ((LocalDate.now().isAfter(pre.getDatafine()))){
                for(int i=0;i<listRec.size();i++){
                    if(pre.getStruttu().getId()==listRec.get(i).getId()){
                        trovato=1;
                        break;
                    }
                }
                if(trovato==0){
                    listStruRecensibili.put(pre.getStruttu().getId(), pre.getStruttu());
                }
            }
        }
        return listStruRecensibili;
    }

    public Struttura selezionaStrutturaRec(int id){
        stcorrente = mapStrutture.get(id);
        return mapStrutture.get(id);
    }

    public void inserisciRecensione(int valutazione, String commento){
       Recensione re =  stcorrente.inserisciRecensione(valutazione, commento,(Cliente)utenteCorrente);
       ((Cliente)utenteCorrente).addRecensione(re);
       stcorrente = null;
    }

//UC8 CREAZIONE ACCOUNT UTENTE

    public void registrazioneHost(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email, String piva, String sFiscale, String residenza){
       int trovato=0;
        for(Utente ut: listaUtenti.values()){
           if(ut.getCodicefiscale().equals(cf)){
               trovato=1;
           }
       }
        if(trovato==0){
            utenteCorrente = new Host(getId(),nome, cognome, dataNascita, cf,nTelefono, email, piva, sFiscale, residenza);
        }
    }

    public void registrazioneCliente(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email){
        int trovato=0;
        for(Utente ut: listaUtenti.values()){
            if(ut.getCodicefiscale().equals(cf)){
                trovato=1;
            }
        }
        if(trovato==0){
            utenteCorrente = new Cliente(getId(), nome, cognome, dataNascita, cf, nTelefono, email);
        }

    }

    public void selezionaAbbonamento(int id){
        ((Host)utenteCorrente).setAbbonamento(listaAbbonamneto.get(id));
    }

    public void confermaRegistrazione(){
        listaUtenti.put(utenteCorrente.getId(), utenteCorrente);
        System.out.println("Account Inserito\n");
    }

//UC13 COMMENTA RECENSIONE
    public Map<Integer, Struttura> commentaRecensione(){
        return ((Host)utenteCorrente).getListaStrutture();
    }

    public ArrayList<Recensione> getRecensioni(int id){
        ArrayList<Recensione> listaRecComm = new ArrayList<>();
        stcorrente = mapStrutture.get(id);
        ArrayList<Recensione> re = mapStrutture.get(id).getListRecensioni();
        for (int i = 0; i < re.size(); i++){
            if (!re.get(i).getStato().equals("Completato")){
                listaRecComm.add(re.get(i));
            }
        }
        return listaRecComm;
    }

    public void inserisciCommento(String commentoHost,int id){
        stcorrente.inserisciCommentoHost(commentoHost,id);
    }



//metodi secondari

    public Beb getBebCorrente(){
        return bebcorrente;
    }

    public CasaVacanze getcvCorrente(){
        return cvcorrente;
    }

    public Map<Integer,Prenotazione> getListaprenotazioni(){
        return listaprenotazioni;
    }

    public Map<Integer,Struttura> getElencoStrutture(){
        return mapStrutture;
    }

    public Prenotazione getPrenotazioneCorrente(){
        return pre;
    }

    public void aggiungiOsservatore(PrenotazioniObserve o) {
        osservatori.add(o);
    }

    public void rimuoviOsservatore(PrenotazioniObserve o) {
        osservatori.remove(o);
    }

    private void notificaOsservatori() {
        for (PrenotazioniObserve o : osservatori) {
            o.aggiorna(pre);
        }
    }

    public Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    private int generaNumeroPrenotazione(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if (!listaprenotazioni.containsKey(randomNumber)){
                return randomNumber;
            }
        }
    }


    private int generaidStruttura(){
       return mapStrutture.values().size()+1;
    }

    private int generaidStanza(){
        return bebcorrente.getListaStanze().size()+1;
    }

    public Utente getUtente(int id) {
        return listaUtenti.get(id);
    }

    public void login(Utente utente){
        utenteCorrente=utente;
    }

    public void logIn(int id){
        utenteCorrente=listaUtenti.get(id);
    }

    public int signUpLogIn(Utente utente) {
        listaUtenti.put(utente.getId(), utente);
        utenteCorrente = utente;
        return utente.getId();
    }

    public void logout() {
        utenteCorrente = null;
    }

    public void setUtenteCorrente(Utente utenteCorrente) {
        this.utenteCorrente = utenteCorrente;
    }

    public int getId(){
        return listaUtenti.values().size()+1;
    }

    public int getIdStrutture(){
        return mapStrutture.values().size()+1;
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

    public HashMap<Integer, Servizio> getServizi() {
        return servizi;
    }

    public ArrayList<Abbonamento> getListaAbbonamneto() {
        return listaAbbonamneto;
    }

}


