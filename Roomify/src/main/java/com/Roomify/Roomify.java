package com.Roomify;

import java.util.*;
import java.time.LocalDate;

public class Roomify {

    private static Roomify roomify;
    private Beb bebcorrente;
    private CasaVacanze cvcorrente;
    private Map<Integer,Struttura> mapStrutture;
    private HashMap<Integer, Servizio> servizi;
    private Map<Integer,Prenotazione> listaprenotazioni;
    private List<PrenotazioniObserve> osservatori = new ArrayList<>();
    private LocalDate corrDataInizio;
    private LocalDate corrDataFine;
    private int corrNumOspiti;
    private Prenotazione pre;
    private Cliente clienteCorr=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");


    private Roomify() {
        this.mapStrutture = new HashMap<>();
        this.listaprenotazioni = new HashMap<>();
    //    this.listaStruCorrenteDisp = new ArrayList<>();
        loadServices();
    }



    public HashMap<Integer, Servizio> getServizi() {
        return servizi;
    }

    private void loadServices(){
        servizi = new HashMap<>();
        servizi.put(1, new Servizio(1, "Lavatrice", "Elettrodomestico utilizzato per lavare i vestiti"));
        servizi.put(2, new Servizio(2,"Microonde", "Elettrodomestico per riscaldare o cucinare velocemente"));
        servizi.put(3, new Servizio(3, "lavastoviglie", "Elettrodomestico utilizzato per lavare i piatti"));
        servizi.put(4, new Servizio(4, "vasca", "Elettrodomestico utilizzato per lavare i piatti"));
        System.out.println("Caricamento servizi completato");
    }

    private ArrayList<Servizio> getServices(ArrayList<Integer> serv){
       ArrayList<Servizio> sv = new ArrayList<>();
        for(int i = 0; i < serv.size(); i++){
            sv.add(servizi.get(serv.get(i)));
        }
        return sv;
    }
    public static Roomify getInstance() {
        if (roomify == null)
            roomify = new Roomify();
        else
            System.out.println("Istanza già creata");

        return roomify;
    }

    public void inserisciBeB(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo){
        this.bebcorrente = new Beb(id,nome, descrizione, paese, citta, provincia, cap, indirizzo);
        System.out.println("B&B inserito nella struttura corrente");
    }

    public void inserisciCasavacanza(int id, String nome, String descrizione, String paese, String citta, String provincia, int  cap, String indirizzo,int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Integer> listaCodServizi){
        CasaVacanze cv=new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, getServices(listaCodServizi));
        this.cvcorrente=cv;
        System.out.println("Casa Vacanza inserita nella struttura corrente");
        aggiungiOsservatore(cv);
    }
/*
    public void inserisciTariffa(String nome, int periodo, float fattoreMoltiplicativo){
        if(bebcorrente != null){

        }else if(c/*vcorrente != null){

        }else { System.out.println("Struttura non inizializzata");}
    }*/

    public void inserisciStanza(int id,String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        if(bebcorrente != null){
            Stanza st=this.bebcorrente.inserisciStanza(id,nome,nospiti,prezzopernotte,dimensione,descrizione,getServices(listacodservizi));
            aggiungiOsservatore(st);
            System.out.println("Stanza inserita in beb"+bebcorrente.getNome());
        }else{
            System.out.println("Struttura non inizializzata");
        }
    }

    public void confermaCasaVacanze(){

        mapStrutture.put(cvcorrente.getId(),cvcorrente);
    }

    public void confermaBeB(){
        mapStrutture.put(bebcorrente.getId(), bebcorrente);
    }

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

    public void aggiungiOsservatore(PrenotazioniObserve o) {
        osservatori.add(o);
    }

    public void rimuoviOsservatore(PrenotazioniObserve o) {
        osservatori.remove(o);
    }

    private void notificaOsservatori() {
        for (PrenotazioniObserve o : osservatori) {
            o.aggiorna(listaprenotazioni);
        }
    }

    // versione mia modificata
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



    public Struttura selezionaStruttura(int id, ArrayList<Struttura>listaStruCorrenteDisp, Cliente cl){
       for (int i = 0; i < listaStruCorrenteDisp.size(); i++){
           if (listaStruCorrenteDisp.get(i).getId() == id){
               if(listaStruCorrenteDisp.get(i) instanceof CasaVacanze) {
                   pre= new Prenotazione();
                   pre.setId(generaNumeroPrenotazione());
                   pre.setDatainizio(corrDataInizio);
                   pre.setDatafine(corrDataFine);
                   pre.setStato("In prenotazione...");
                   pre.setStruttu(listaStruCorrenteDisp.get(i));
                   pre.setCl(cl);
               }
               return listaStruCorrenteDisp.get(i);
           }
       }
       return null;
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

    public void selezionaStanza(int id, Struttura str, Cliente cl){
        if (str instanceof Beb){
            ArrayList<Stanza> st = ((Beb)str).isAvaible(corrDataInizio, corrDataFine, corrNumOspiti);
            for(int i = 0; i < st.size(); i++){
                if (st.get(i).getId() == id){
                    pre= new Prenotazione();
                    pre.setId(generaNumeroPrenotazione());
                    pre.setDatainizio(corrDataInizio);
                    pre.setDatafine(corrDataFine);
                    pre.setStato("In prenotazione...");
                    pre.setSt(st.get(i));
                    pre.setStruttu(str);
                    pre.setCl(cl);
                }
            }
        }
    }

    public Prenotazione getPrenotazioneCorrente(){
        return pre;
    }

    public void confermaPrenotazione(){
        if(pre!=null){
            clienteCorr.addPrenotazione(pre);
            listaprenotazioni.put(pre.getId(), pre);
            System.out.println("Inserimento completato, buon viaggio");
            notificaOsservatori();
        } else {
            System.out.println("Prenotazione inesistente");
        }
    }




   /* public void visualizzaprenotazionistruesta(){
        for(Struttura struttura : mapStrutture.values()){
            System.out.println("Struttura con nome: " + struttura.getNome());
            if(struttura instanceof Beb){
                for(Stanza stanza : ((Beb) struttura).getListaStanze().values()){
                    System.out.println("Stanza: "+ stanza.getId());
                    for(Prenotazione pre: stanza.getListaprenotazioni().values()){
                        System.out.println("Prenotazione: "+pre.getId()+"data inizio "+pre.getDatainizio()+"data fine "+pre.getDatafine()+"Stanza: "+pre.getStanza().getId());
                    }
                }
            } else{
                for(Prenotazione pre: ((CasaVacanze)struttura).getListaprenotazioni().values()){
                    System.out.println("Prenotazione: "+pre.getId()+"data inizio "+pre.getDatainizio()+"data fine "+pre.getDatafine()+"Struttura: "+pre.getStruttu().getNome());
                }
            }
        }
    }*/
}



 /*public void prenotaAlloggio(String città, LocalDate dataInizio, LocalDate dataFine, int nOspiti){
        corrDataFine=dataFine;
        corrDataInizio=dataInizio;
        corrNumOspiti=nOspiti;
        for (Struttura struttura : mapStrutture.values()) {
            if (struttura.getCitta().equals(città)){
                Struttura sv = struttura.isAvaible(dataInizio, dataFine, nOspiti, struttura);

                if (sv != null){
                    listaStruCorrenteDisp.add(sv);
                }
            }
        }
    }*/