package com.Roomify;

import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.Exception.LoginClienteException;

import java.util.*;
import java.time.LocalDate;

public class Roomify {

    private static Roomify roomify;

   // private Beb bebcorrente;
   // private CasaVacanze cvcorrente;

    private Utente utenteCorrente;
    private Struttura stcorrente;
    private Prenotazione pre;
    private Map<Integer,Struttura> mapStrutture;
    private Map<Integer,Utente> listaUtenti=new HashMap<>();
    private Map<Integer,Utente> listaPartner = new HashMap<>();
    private HashMap<Integer, Servizio> servizi;
    private ArrayList<Abbonamento> listaAbbonamneto;
    private Map<Integer, RichiestaAssistenza> listaAssistenza;

    // private Map<Integer,Prenotazione> listaprenotazioni;
   // private LocalDate corrDataInizio;
   // private LocalDate corrDataFine;
   // private int corrNumOspiti;
    private String categoriaCorr;

    private Struttura strutturascelta;
    private RichiestaAssistenza richiestaAssistenzaScelta;
    private RichiestaAssistenza richiestaAssistenzaCorrente;

    private Roomify() {
        this.mapStrutture = new HashMap<>();
      //  this.listaprenotazioni = new HashMap<>();
        this.listaAssistenza = new HashMap<>();
        //populate();
    }

    public static Roomify getInstance() {
        if (roomify == null)
            roomify = new Roomify();
        else
            System.out.println("Istanza già creata");

        return roomify;
    }
    
    public void populate() {

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

        logIn(4);
        inserisciCasavacanza( "Villa Sole", "Splendida villa con piscina e vista mare.", "Italia", "Palermo", "PA", 90100, "Via Roma, 10", 6, 4, 120.50f, 150, listcod1 );
        confermaCasaVacanze();
        logout();

        logIn(2);
        inserisciCasavacanza( "Casa Luna", "Accogliente casa nel centro storico", "Italia", "Roma", "RM", 00100, "Via dei Fori, 5", 4, 3, 80.00f, 90, listcod2);
        confermaCasaVacanze();

        inserisciCasavacanza( "Villa Aurora", "Villa di lusso con ampio giardino", "Italia", "Milano", "MI", 20100, "Corso Magenta, 25", 8, 5, 200.00f, 180, listcod1);
       confermaCasaVacanze();
        logout();

        logIn(6);
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

        logIn(8);
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


        // Prenotazione per un B&B a Taormina
        logIn(1);
        ArrayList<Struttura> listaDisp1 = prenotaAlloggio("Taormina", LocalDate.of(2024, 5, 10), LocalDate.of(2024, 6, 13), 2);
        Struttura st1 = selezionaStruttura(4);
        selezionaStanza(1);
        confermaPrenotazione();

        //la ricerca funziona ma la struttura scelta non rispecchia la ricerca -- prima era Taormina
        ArrayList<Struttura> listaDisp11 = prenotaAlloggio("Torino", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 6, 13), 2);
        Struttura st11 = selezionaStruttura(8);
        selezionaStanza(1);
        confermaPrenotazione();


        ArrayList<Struttura> listaDisp111 = prenotaAlloggio("Taormina", LocalDate.of(2026, 5, 10), LocalDate.of(2026, 6, 13), 2);
        Struttura st111 = selezionaStruttura(4);
        selezionaStanza(1);
        confermaPrenotazione();
        logout();

        // Prenotazione per un B&B a Firenze
        logIn(3);
        ArrayList<Struttura> listaDisp2 =prenotaAlloggio("Firenze", LocalDate.of(2020, 7, 1), LocalDate.of(2020, 7, 15), 3);
        Struttura st2 = selezionaStruttura(5);
        selezionaStanza(2);
        confermaPrenotazione();
        selezionaStrutturaRec(5);
        inserisciRecensione(2,"non mi piace");
        logout();

        // Prenotazione per un B&B a Napoli
        logIn(5);
        ArrayList<Struttura> listaDisp3 = prenotaAlloggio("Napoli", LocalDate.of(2025, 8, 5), LocalDate.of(2025, 8, 12), 4);
        Struttura st3 = selezionaStruttura(6);
        selezionaStanza(3);
        confermaPrenotazione();

        logout();

        // Prenotazione per un B&B a Torino
        logIn(7);
        ArrayList<Struttura> listaDisp4 = prenotaAlloggio("Torino", LocalDate.of(2022, 9, 10), LocalDate.of(2022, 9, 20), 4);
        Struttura st4 = selezionaStruttura(8);
        selezionaStanza(3);
        confermaPrenotazione();
        selezionaStrutturaRec(8);
        inserisciRecensione(5,"mi piace");
        logout();

        //Commento recensioni da parte dell'host
        logIn(8);
        Map<Integer,Struttura> strutComm=commentaRecensione();
/*
        System.out.println("Strutt comm");
        for(Struttura st: strutComm.values()){
            System.out.println("ID:"+st.getId());
        }
        selStrut(8);
        System.out.println("Lista recensioni: ");
        ArrayList<Recensione> listarec=getRecensioniComm();
        for(int i=0;i<listarec.size();i++){
            System.out.println("Recensione con id:"+listarec.get(i).getId());
        }
        inserisciCommento("sei maleducato",1);*/
        logout();

        listaUtenti.put(listaUtenti.size()+1,new PartnerAssicurativo(listaUtenti.size()+1, "Erma", "Florio", LocalDate.of(1999,01,13), "DIDSA", "eree", "323232323", 332));
        logIn(listaUtenti.size());
        inseriNuovaPolizza(2, "Utente", "Danni accidentali", 23, "CONCLUSO");
        confermaInserimento();
        logout();
        registrazionePartner("Erma", "Florio", LocalDate.of(1999,01,13), "DIDSA", "eree", "323232323", 332);
        confermaRegistrazione();

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
                stcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
                System.out.println("B&B inserito nella struttura corrente");
            }
        } else{
            stcorrente = new Beb(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, ((Host) utenteCorrente));
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
                    stcorrente = cv;
                    System.out.println("Casa Vacanza inserita nella struttura corrente");
                   // aggiungiOsservatore(cv);
            }
        } else {
            CasaVacanze cv = new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi), ((Host) utenteCorrente));
            stcorrente = cv;
            System.out.println("Casa Vacanza inserita nella struttura corrente");
           // aggiungiOsservatore(cv);
        }
    }

    public void inserisciTariffa(String nome, LocalDate datainizio,LocalDate datafine, float fattoreMoltiplicativo){
        stcorrente.inserisciTariffa(nome,datainizio,datafine,fattoreMoltiplicativo);
    }

    public void inserisciStanza(String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        int id=generaidStanza();
        if(stcorrente != null){
            Stanza st=((Beb)stcorrente).inserisciStanza(id,nome,nospiti,prezzopernotte,dimensione,descrizione,getServices(listacodservizi),((Beb)stcorrente));
          //  aggiungiOsservatore(st);
            System.out.println("Stanza inserita in beb"+((Beb)stcorrente).getNome());
        }else{
            System.out.println("Devi prima inserire un B&B");
        }
    }

    public void confermaCasaVacanze(){
        mapStrutture.put(stcorrente.getId(),stcorrente);
        Map<Integer,Struttura> listastru=((Host)utenteCorrente).getListaStrutture();
        listastru.put(stcorrente.getId(),stcorrente);
        ((Host)utenteCorrente).setListaStrutture(listastru);
        stcorrente=null;
    }

    public void confermaBeB(){
        mapStrutture.put(stcorrente.getId(), stcorrente);
        Map<Integer,Struttura> listastru=((Host)utenteCorrente).getListaStrutture();
        listastru.put(stcorrente.getId(),stcorrente);
        ((Host)utenteCorrente).setListaStrutture(listastru);
        stcorrente=null;
    }

//UC2 GESTIONE PRENOTAZIONE ALLOGGIO
    public ArrayList<Struttura> prenotaAlloggio(String città, LocalDate dataInizio, LocalDate dataFine, int nOspiti) {
        pre=new Prenotazione(dataInizio,dataFine,nOspiti);
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

                  // pre= new Prenotazione();
                   pre.setId(((CasaVacanze)st).generaNumeroPrenotazione());
                   //pre.setDatainizio(corrDataInizio);
                   //pre.setDatafine(corrDataFine);
                   pre.setStato("In prenotazione...");
                   pre.setStruttu(st);
                   pre.setCl((Cliente)utenteCorrente);
               }
               return st;
           }
       }
       return null;
    }

    public void selezionaStanza(int id){
        if (strutturascelta instanceof Beb){
            ArrayList<Stanza> st = ((Beb)strutturascelta).isAvaible(pre.getDatainizio(),pre.getDatafine(),pre.getnOspiti());
            for(int i = 0; i < st.size(); i++){
                if (st.get(i).getId() == id){
                    //pre= new Prenotazione();
                    pre.setId(st.get(i).generaNumeroPrenotazione());
                    //pre.setDatainizio(corrDataInizio);
                    //pre.setDatafine(corrDataFine);
                    pre.setStato("In prenotazione...");
                    pre.setSt(st.get(i));
                    pre.setStruttu(strutturascelta);
                    pre.setCl((Cliente)utenteCorrente);
                }
            }
        }
    }

    public void confermaPrenotazione(){
        if(pre!=null){
            pre.setStato("Prenotato");
            ((Cliente)utenteCorrente).addPrenotazione(pre);

         //   listaprenotazioni.put(pre.getId(), pre);

            if(pre.getStruttu() instanceof CasaVacanze)
                ((CasaVacanze)strutturascelta).getListaprenotazioni().put(pre.getId(), pre);
            else {
               ((Beb) strutturascelta).getListaStanze().get(pre.getStanza().getNome()).addPrenotazione(pre);
            }

            System.out.println("Inserimento completato, buon viaggio");
           // notificaOsservatori();
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
            Map<String, Stanza> stn = ((Beb)st).getListaStanze();
            for(Stanza stnn : stn.values()){
                for(Prenotazione pren : stnn.getListaprenotazioni().values()){
                    prenotazioni.put(pren.getId(), pren);
                }
            }
            return prenotazioni;
        }
    }

//UC4 VISUALIZZA PRENOTAZIONI EFFETTUATE

    public Map<Integer,Prenotazione> visualizzaPrenotazioniCliente( LocalDate dataInizio, LocalDate dataFine){
        Map<Integer, Prenotazione> map = ((Cliente)utenteCorrente).getListaPrenotazioniClienti(dataInizio, dataFine);
        if (!map.isEmpty()){
            System.out.println("Ecco le prenotazioni: \n");
            return map;
        }else{
            return null;
        }
    }

//UC5 GESTIONE ANNULLAMENTO PRENOTAZIONE
    public Map<Integer, Prenotazione> annullaPrenotazione(){
        return ((Cliente)utenteCorrente).getListaPrenotazioniClienti();
    }

    public boolean selezionaPrenotazione(int id){
        pre = ((Cliente)utenteCorrente).getListaPrenotazioniClienti().get(id);
        if (LocalDate.now().isBefore(pre.getDatainizio())){
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
       Recensione re =  ((Cliente)utenteCorrente).inserisciRecensione(valutazione, commento,(Cliente)utenteCorrente,strutturascelta);
       strutturascelta.addRecensione(re);
       strutturascelta = null;
    }

//UC8 CREAZIONE ACCOUNT UTENTE

    public boolean registrazioneHost(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email, String piva, String sFiscale, String residenza){
        for(Utente ut: listaUtenti.values()){
           if(ut.getCodicefiscale().equals(cf)){
               return false;
           }
       }
        utenteCorrente = new Host(getId(),nome, cognome, dataNascita, cf,nTelefono, email, piva, sFiscale, residenza);
        return true;
    }

    public boolean registrazioneCliente(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email){
        for(Utente ut: listaUtenti.values()){
            if(ut.getCodicefiscale().equals(cf)){
                return false;
            }
        }
        utenteCorrente = new Cliente(getId(), nome, cognome, dataNascita, cf, nTelefono, email);
        return true;
    }

    public boolean registrazionePartner(String nome, String cognome, LocalDate dataNascita, String cf,String nTelefono, String email, int nlicenza){
        for (Utente ut: listaPartner.values()){
            if (ut.getCodicefiscale().equals(cf)){
                return false;
            }
        }
        utenteCorrente = new PartnerAssicurativo(listaUtenti.size()+1, nome, cognome, dataNascita, cf, nTelefono, email, nlicenza);
        return true;
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

    //nel menu poi facciamo che l'utente visualizza le sue prenotazioni e poi scegliere quale fare
    public void richiestaAssistenza(String descrizione,int id,String scelta){
        String stato="In elaborazione";
        switch (scelta){
            case "Prenotazione":
                if(utenteCorrente instanceof Cliente){
                    richiestaAssistenzaCorrente = ((Cliente)utenteCorrente).richiestaAssistenzaPrenotazione(generaNumeroAssistenza(),descrizione,id,stato);
                }else{
                    richiestaAssistenzaCorrente = ((Host)utenteCorrente).richiestaAssistenzaPrenotazione(generaNumeroAssistenza(),descrizione,id,stato);
                }
                break;
            case "Recensione":
                if(utenteCorrente instanceof Cliente){
                    richiestaAssistenzaCorrente = ((Cliente)utenteCorrente).richiestaAssistenzaRecensione(generaNumeroAssistenza(),descrizione,id,stato);
                }else{
                    richiestaAssistenzaCorrente = ((Host)utenteCorrente).richiestaAssistenzaRecensione(generaNumeroAssistenza(),descrizione,id,stato);
                }
                break;
            default:
                richiestaAssistenzaCorrente = utenteCorrente.richiestaAssistenza(generaNumeroAssistenza(),descrizione,stato);
        }

    }

    public void confermaAssistenza(){
        if(richiestaAssistenzaCorrente != null){
            listaAssistenza.put(richiestaAssistenzaCorrente.getId(), richiestaAssistenzaCorrente);
            utenteCorrente.confermaAssistenza();
        }
        richiestaAssistenzaCorrente = null;
    }



//UC12 GESTISCI RICHIESTA ASSISTENZA
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
    public void inseriNuovaPolizza(int id, String tipo, String copertura, int durata, String stato){
        ((PartnerAssicurativo)utenteCorrente).inseriNuovaPolizza(id, tipo, copertura, durata, stato);
    }

    public void confermaInserimento(){
        ((PartnerAssicurativo)utenteCorrente).confermaInserimento();
    }

    public void annullaInserimento(){
        ((PartnerAssicurativo)utenteCorrente).annullaInserimento();

    }


    //UC14

    public ArrayList<Recensione> visualizzaRecensioni(){
        return ((Host)utenteCorrente).visualizzaRecensioni();
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

    private int generaidStruttura(){
       return mapStrutture.values().size()+1;
    }

    private int generaidStanza(){
        return ((Beb)stcorrente).getListaStanze().size()+1;
    }

    public Utente getUtente(int id) {
        if (listaUtenti.containsKey(id)){
            return listaUtenti.get(id);
        }
         return listaPartner.get(id);
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

    public int getId(){
        return listaUtenti.values().size()+1;
    }

    private ArrayList<Servizio> getServices(ArrayList<Integer> serv){
        ArrayList<Servizio> sv = new ArrayList<>();
        for(int i = 0; i < serv.size(); i++){
            sv.add(servizi.get(serv.get(i)));
        }
        return sv;
    }

    public Map<Integer, Utente> getListaUtenti() {
        return listaUtenti;
    }

    public ArrayList<Abbonamento> getListaAbbonamneto() {
        return listaAbbonamneto;
    }

    public Map<Integer, RichiestaAssistenza> getListaAssistenza() {
        return listaAssistenza;
    }

    public HashMap<Integer, Servizio> getServizi() {
        return servizi;
    }
}


