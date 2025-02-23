package com.Roomify.UI;

import com.Roomify.Beb;
import com.Roomify.Exception.LogException;
import com.Roomify.Prenotazione;
import com.Roomify.Recensione;
import com.Roomify.Struttura;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHost extends Menu{
    @Override
    void displayMenu() {

    }

    @Override
    void processaScelta(int scelta) throws Exception {

    }

  /*  protected void displayMenu() {
        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Inserisci un B&B");
        System.out.println("2. Inserisci una casa vacanze");
        System.out.println("7. Logout");
    }

    @Override
    protected void processaScelta(int scelta) throws Exception {
        switch (scelta) {
            case 1:
                //inserisciBeb();
                break;
            case 2:
              //  inserisciCasaVacanze();
                break;
            case 3:
             //   visualizzaPrenotazioni();
                break;
            case 4:
               // getRecensioniComm();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Opzione non valida");
        }
    }*/

  /*  private void inserisciBeb(){
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci nuovo B&B :");
        System.out.println("Inserisci il nome del B&B");
        String nome = input.nextLine();

        System.out.println("Inserisci descrizione del B&B");
        String descrizione = input.nextLine();

        System.out.println("Inserisci il paese del B&B");
        String paese = input.nextLine();

        System.out.println("Inserisci la citta del B&B");
        String citta = input.nextLine();

        System.out.println("Inserisci la provincia del B&B");
        String provincia = input.nextLine();

        System.out.println("Inserisci il cap del B&B");
        int cap = input.nextInt();

        System.out.println("Inserisci l'indirizzo' del B&B");
        String indirizzo = input.nextLine();


        try{
            sistema.inserisciBeB(nome,descrizione,paese,citta,provincia,cap,indirizzo);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Inserisci una stanza");
        System.out.println("2. Termina");
        while (!input.hasNextInt()) {
            System.out.println("Inserisci un numero valido.");
            input.next();
        }
        int inp=input.nextInt();

        switch (inp) {
            case 1:
                inserisciStanza();
                break;
            case 2:
                confermaBeb();
                break;
            case 0:
                logout();
                break;
            default:
                System.out.println("Opzione non valida");
        }

        System.out.println("Premi invio per terminare l'inserimento del B&B");
        input.nextLine();


    }
    private void inserisciStanza(){
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci nuovo Stanza :");

        System.out.println("Inserisci il nome del Stanza");
        String nome = input.nextLine();

        System.out.println("Inserisci descrizione del Stanza");
        String descrizione = input.nextLine();

        System.out.println("Inserisci il numero di ospiti della Stanza");
        int ospiti = input.nextInt();

        System.out.println("Inserisci il prezzo per notte della Stanza");
        float prezzopernotte = input.nextFloat();

        System.out.println("Inserisci la dimensione della Stanza");
        int dimensione= input.nextInt();

        System.out.println("Inserisci il cap del Stanza");
        String cap = input.nextLine();

        System.out.println("Seleziona i servizi della stanza tra quelli elencati");


        System.out.println("Inserisci l'indirizzo' del Stanza");
        String indirizzo = input.nextLine();
    }
    private void inserisciCasaVacanze() throws LogException {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci una nuova Stanza:");

        System.out.print("Inserisci il nome della Stanza: ");
        String nome = input.nextLine();

        System.out.print("Inserisci la descrizione della Stanza: ");
        String descrizione = input.nextLine();

        System.out.print("Inserisci il paese della Stanza: ");
        String paese = input.nextLine();

        System.out.print("Inserisci la città della Stanza: ");
        String citta = input.nextLine();

        System.out.print("Inserisci la provincia della Stanza: ");
        String provincia = input.nextLine();

        System.out.print("Inserisci il CAP della Stanza: ");
        int cap = input.nextInt();
        input.nextLine(); // Consuma il newline dopo il nextInt

        System.out.print("Inserisci l'indirizzo della Stanza: ");
        String indirizzo = input.nextLine();

        System.out.print("Inserisci il numero massimo di ospiti: ");
        int nMaxOspiti = input.nextInt();

        System.out.print("Inserisci il numero di vani della Stanza: ");
        int nVani = input.nextInt();

        System.out.print("Inserisci il prezzo per notte della Stanza: ");
        float prezzoNotte = input.nextFloat();

        System.out.print("Inserisci la dimensione della Stanza (in mq): ");
        int dimensione = input.nextInt();

        int inp=input.nextInt();

        switch (inp) {
            case 1:
                inserisciStanza();
                break;
            case 2:
                logout();
                break;
            default:
                System.out.println("Opzione non valida");
        }
    }

    public void confermaCasaVacanze(){
        try {
            sistema.confermaCasaVacanze();
            System.out.println("Casa Vacanze Inserito");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    public void confermaBeb(){
        //inserire che se non ha stanze non puoi confermare il beb
        try {
            sistema.confermaBeB();
            System.out.println("Casa Vacanze Inserito");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public void visualizzaPrenotazioni(){
        Map<Integer, Struttura> map = sistema.visualizzaPrenotazioni();
        Scanner scanner = new Scanner(System.in);
        for(Struttura st : map.values()){
            System.out.println(st.toString());
        }
        System.out.println("\nScegli la struttura");
        int sv = scanner.nextInt();

        Map<Integer, Prenotazione> prenotazioni = sistema.selezionaStrutturaVis(sv);

        for (Prenotazione pre : prenotazioni.values()){
            System.out.println("\n");
            System.out.println(pre.toString());
        }
        System.out.println("\nCosa vuoi fare?\n0 - Ritorna al menu principale");
        sv = scanner.nextInt();
        switch (sv){
            case 0:
                try {
                    menu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Numero inserito non valido");
        }

        /*
        Scanner input = new Scanner(System.in);
        String sv;
        LocalDate dataInizio = null;
        LocalDate dataFine = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<Integer, Prenotazione> map = new HashMap<>();


        System.out.println("Inserisci data inizio yyyy-MM-dd");
        sv = input.nextLine();
        try{
            dataInizio = LocalDate.parse(sv, formatter);
        }catch(Exception e){
            System.out.println("Formato non valido");
        }


        System.out.println("Inserisci data inizio yyyy-MM-dd");
        sv = input.nextLine();
        try{
            dataFine = LocalDate.parse(sv, formatter);
        }catch(Exception e){
            System.out.println("Formato non valido");
        }
        if(dataInizio != null && dataFine != null){
            map = sistema.visualizzaPrenotazioniCliente(dataInizio, dataFine);
        }
        for(Prenotazione pre : map.values()){
            System.out.println(pre.toString());
        }
        int num;
        System.out.println("Scegli una prenotazione:");
        num = input.nextInt();

        if (map.containsKey(num)){

        }*/
  //  }

/*    public void commentaRecensione(){
        Map<Integer, Struttura> map = sistema.commentaRecensione();
        Scanner scanner = new Scanner(System.in);
        Struttura st;
        for (Struttura stn : map.values()){
            System.out.println("\n");
            System.out.println(stn.toString());
        }

    }

    public void selStrut() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        System.out.println("\nInserisci id");
        id = scanner.nextInt();
        if (id != 0){
            sistema.selStrut(id);
            System.out.println("Struttura selezionata");
            getRecensioniComm();
        }

    }

    public  void getRecensioniComm() throws Exception {
        ArrayList<Recensione> listRec = sistema.getRecensioniComm();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < listRec.size(); i++){
            System.out.println("\n");
            System.out.println(listRec.get(i).toString());
        }
        System.out.println("\nSeleziona recensione da commentare:");
        int id = scanner.nextInt();
        for(int i = 0; i< listRec.size(); i++){
            if(listRec.get(i).getId() == id){
                inserisciCommento(id);
            }
        }

    }

    public void inserisciCommento(int id) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInserisci commento:");
        String commento = scanner.nextLine();
        sistema.inserisciCommento(commento, id);
        menu();
    }

   /* public void richiestaAssistenza(){
        Scanner scanner = new Scanner(System.in);
        String scelta = "";
        System.out.println("\nInserisci la tipologia di assistenza.\n1 -  Prenotazione\n2 - Recensione\n3 - Generale");
        int sv = scanner.nextInt();

        System.out.println("\n");
        int sv = scanner.nextInt();

        switch (sv){
            case 1:

                break;
        }
    }*/

}
