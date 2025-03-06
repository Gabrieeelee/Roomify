package com.Roomify.UI;

import com.Roomify.*;
import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuHost extends Menu{
    protected void displayMenu() {
        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Inserisci un B&B");
        System.out.println("2. Inserisci una casa vacanze");
        System.out.println("3. Visualizza prenotazioni");
        System.out.println("4. Rispondi recensioni");
        System.out.println("5. Richiedi Assistenza");
        System.out.println("6. Visualizza Recensioni");
        System.out.println("7. Modifica Struttura");
        System.out.println("8. Visualizza assistenze");
        System.out.println("9. Visualizza Messaggi Assistenza");
        System.out.println("99. Logout");
    }

    @Override
    protected void processaScelta(int scelta) {
        switch (scelta) {
            case 1:
                inserisciBeb();
                break;
            case 2:
                inserisciCasaVacanze();
                break;
            case 3:
                visualizzaPrenotazioni();
                break;
            case 4:
                commentaRecensione();
                break;
            case 5:
                assistenza();
                break;
            case 6:
                visualizzaRecensioni();
                break;
            case 7:
                modificaStruttura();
                break;
            case 8:
                visualizzaAssistenza();
                break;
            case 9:
                visualizzaMessaggi();
                break;
            case 99:
                logout();
                Menu menu = new LoginMenu();
                menu.menu();
                break;
            default:
                System.out.println("Opzione non valida");
        }
    }

    private void visualizzaAssistenza() {
        Map<Integer, RichiestaAssistenza> map =sistema.visualizzaAssistenza();
        if (map.isEmpty()){
            goToMenu("Non hai assistenze");
        }else{
            for (RichiestaAssistenza ra : map.values()){
                ra.mostraDettagli();
            }
        }
        goToMenu("Torno al menu");
    }

    private void assistenza() {
        ArrayList<CategoriaAssistenza> list = sistema.assistenza();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < list.size(); i++){
            System.out.println(i+" - "+list.get(i).name());
        }
        System.out.println("Seleziona supporto");
        int id = scanner.nextInt();

        for (int i = 0; i < list.size(); i++){
            if (i == id){
                selCat(list.get(i).name());
                break;
            }
        }


    }

    private void selCat(String id) {
        CategoriaAssistenza cat = sistema.selCat(id);
        switch (cat.name()){
            case "PRENOTAZIONE":
                getListaPrenotazioni();
                richiestaAssistenza();
                confermaAsisstenza();
                break;
            case "RECENSIONE":
                getListaRec();
                richiestaAssistenza();
                confermaAsisstenza();
                break;
            case "ALTRI_MOTIVI":
                richiestaAssistenza();
                confermaAsisstenza();
                break;
        }
    }

    //MEtodo del sistema da togliere, non serve a nulla, a meno che non abbiamo una recensione corrente sull'utente
    private void selRecensione(int id) {
        sistema.selRecensione(id);
    }

    private void getListaRec() {
        ArrayList<Recensione> list = sistema.getListaRec();
        Scanner scanner = new Scanner(System.in);

        if (!list.isEmpty()){
            for(int i = 0; i < list.size(); i++){
                System.out.println("\n"+list.get(i).toString());
            }
        }else{
            goToMenu("Non hai recensioni");
        }

        int id = 0;
        boolean trv = true;
        while (trv){
            System.out.println("Inserisci id: ");
            id = scanner.nextInt();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getId() == id){
                    trv = false;
                    break;
                }
            }
            if (!trv){
                break;
            }
            System.out.println("ID inserito non corretto");
        }

        selRecensione(id);
    }

    private void getListaPrenotazioni() {
        Map<Integer, Prenotazione> map = sistema.getListaPrenotazioni();
        Scanner scanner = new Scanner(System.in);

        if (!map.isEmpty()){
            for (Prenotazione pre : map.values()){
                System.out.println("\n"+pre.toString());
            }
        }else{
            goToMenu("Non hai prenotazioni");
        }

        int id = 0;

        while (true){
            System.out.println("Inserisci id: ");
            id = scanner.nextInt();
            if (map.containsKey(id)){
                break;
            }
            System.out.println("ID inserito non corretto");
        }
        selPrenotazione(id);
    }

    private void selPrenotazione(int id) {
        sistema.selPrenotazione(id);

    }

    public void richiestaAssistenza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci descrizione");
        String descrizione = scanner.nextLine();
        sistema.richiestaAssistenza(descrizione);
    }


    private void goToMenu(String msg){
        System.out.println("\n"+msg);
        try {
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void visualizzaMessaggi() {
        Map<Integer, RichiestaAssistenza> map = sistema.visualizzaMessaggi();
        Scanner scanner = new Scanner(System.in);
        if (!map.isEmpty()){
            for (RichiestaAssistenza ra : map.values()){
                ra.mostraDettagli();
            }
        }else{
            goToMenu("Non hai richieste d'assistenza");
        }
        int id = 0;
        while (true){
            System.out.println("Inserisci id:");
            id = scanner.nextInt();
            if(map.containsKey(id)){
                break;
            }
            System.out.println("Id inserito non corretto");
        }
        visualizzaRisposte(id);
    }

    private void visualizzaRisposte(int id) {
        ArrayList<Messaggio> list = sistema.visualizzaRisposte(id);
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++){
                System.out.println("\n"+list.get(i).toString());
            }
        }else{
            goToMenu("Non hai messaggi");
        }
        goToMenu("Ritorno al menu");
    }

    private void inserisciBeb(){
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
        input.nextLine();
        String indirizzo = input.nextLine();


        try{
            sistema.inserisciBeB(nome,descrizione,paese,citta,provincia,cap,indirizzo);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }

        boolean trv = true;
        while(trv) {
            System.out.println("Vuoi inserire una tariffa?\n1. Si\n2. No");
            int id = input.nextInt();
            switch (id) {
                case 1:
                    int meseInizio = 0;
                    int meseFine = 0;
                    System.out.println("Inserisci nome: ");
                    input.nextLine();
                    nome = input.nextLine();
                    do {
                        System.out.println("Inserisci mese di inizio: (1,2,...,12)");
                        meseInizio = input.nextInt();
                    }while (meseInizio > 12);
                    do{
                        System.out.println("Inserisci mese fine: (1,2,...,12)");
                        meseFine = input.nextInt();
                    }while(meseFine > 12 || meseFine < meseInizio);

                    System.out.println("Inserisci fattore moltiplicativo (x,y)");
                    float fattoreMoltiplicativo = input.nextFloat();

                    sistema.inserisciTariffa(nome, meseInizio, meseFine, fattoreMoltiplicativo);
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    System.out.println("ID inserito sbagliato");
                    break;
            }
        }


        boolean inserimento = true;

        while (inserimento){

            System.out.println("Seleziona un'opzione:");
            System.out.println("1. Inserisci una stanza");
            System.out.println("2. Conferma inserimento");
            int inp=input.nextInt();

            switch (inp) {
                case 1:
                    inserisciStanza();
                    break;
                case 2:
                    confermaBeb();
                    inserimento = false;
                    break;
                default:
                    System.out.println("Opzione non valida");
                    break;
            }

        }
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

        System.out.println("Seleziona i servizi della stanza tra quelli elencati");
        Map<Integer, Servizio> map =sistema.getServizi();
        for (Servizio serv : map.values()){
            System.out.println("\n"+serv.toString());
        }
        ArrayList<Integer> servizi = new ArrayList<>();
        while(true){
            System.out.println("Inserisci codice servizio\n0 - Chiudi inserimento");
            int sv = input.nextInt();
            if (sv == 0){
                sistema.inserisciStanza(nome, ospiti, prezzopernotte, dimensione, descrizione,servizi);
                return;
            }else{
                servizi.add(sv);
            }
        }
    }

    private void inserisciCasaVacanze() {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci una nuova casa Vacanze:");

        System.out.print("Inserisci il nome della casa Vacanze: ");
        String nome = input.nextLine();

        System.out.print("Inserisci la descrizione della casa Vacanze: ");
        String descrizione = input.nextLine();

        System.out.print("Inserisci il paese della casa Vacanze: ");
        String paese = input.nextLine();

        System.out.print("Inserisci la cittÃ  della casa Vacanze: ");
        String citta = input.nextLine();

        System.out.print("Inserisci la provincia della casa Vacanze: ");
        String provincia = input.nextLine();

        System.out.print("Inserisci il CAP della casa Vacanze: ");
        int cap = input.nextInt();
        input.nextLine(); // Consuma il newline dopo il nextInt

        System.out.print("Inserisci l'indirizzo della casa Vacanze: ");
        String indirizzo = input.nextLine();

        System.out.print("Inserisci il numero massimo di ospiti: ");
        int nMaxOspiti = input.nextInt();

        System.out.print("Inserisci il numero di vani della casa Vacanze: ");
        int nVani = input.nextInt();

        System.out.print("Inserisci il prezzo per notte della casa Vacanze: ");
        float prezzoNotte = input.nextFloat();

        System.out.print("Inserisci la dimensione della casa Vacanze (in mq): ");
        int dimensione = input.nextInt();
        System.out.println("Seleziona i servizi della casa Vacanze tra quelli elencati");
        Map<Integer, Servizio> map =sistema.getServizi();
        for (Servizio serv : map.values()){
            System.out.println("\n"+serv.toString());
        }
        ArrayList<Integer> servizi = new ArrayList<>();
        while(true){
            System.out.println("Inserisci codice servizio\n0 - Chiudi inserimento");
            int sv = input.nextInt();
            if (sv != 0){
                servizi.add(sv);
            }else{
                break;
            }
        }
        sistema.inserisciCasavacanza(nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, servizi );

        boolean trv = true;
        while(trv) {
            System.out.println("Vuoi inserire una tariffa?\n1. Si\n2. No");
            int id = input.nextInt();
            switch (id) {
                case 1:
                    int meseInizio = 0;
                    int meseFine = 0;
                    System.out.println("Inserisci nome: ");
                    input.nextLine();
                    nome = input.nextLine();
                    do {
                        System.out.println("Inserisci mese di inizio: (1,2,...,12)");
                        meseInizio = input.nextInt();
                    }while (meseInizio > 12);
                    do{
                        System.out.println("Inserisci mese fine: (1,2,...,12)");
                        meseFine = input.nextInt();
                    }while(meseFine > 12 || meseFine < meseInizio);

                    System.out.println("Inserisci fattore moltiplicativo (x,y)");
                    float fattoreMoltiplicativo = input.nextFloat();

                    sistema.inserisciTariffa(nome, meseInizio, meseFine, fattoreMoltiplicativo);
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    System.out.println("ID inserito sbagliato");
                    break;
            }
        }

        confermaCasaVacanze();
    }


    public void confermaCasaVacanze(){
        try {
            sistema.confermaCasaVacanze();
            System.out.println("Casa Vacanze Inserito");
            goToMenu("Casa Vacanze inserita");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            goToMenu("ERRORE NELL'INSERIMENTO");
        }
    }

    public void confermaBeb(){
        if (((Beb)sistema.getStcorrente()).getListaStanze().size() != 0){
            try {
                sistema.confermaBeB();
                goToMenu("BEB inserito!");
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            goToMenu("Non hai inserito nessuna stanza, ritorno al menu");
        }

    }

    public void visualizzaPrenotazioni(){
        Map<Integer, Struttura> map = sistema.visualizzaPrenotazioni();
        int sv;
        Scanner scanner = new Scanner(System.in);
        for(Struttura st : map.values()){
            System.out.println("\n"+st.toString());
        }
        while(true) {
            System.out.println("\nScegli la struttura");
            sv = scanner.nextInt();
            if(map.containsKey(sv))
                break;
            System.out.println("Non possiedi questa struttura, inseriscine una di tua proprietà");

        }
        Map<Integer, Prenotazione> prenotazioni = sistema.selezionaStrutturaVis(sv);
        if (!prenotazioni.isEmpty()){
            for (Prenotazione pre : prenotazioni.values()){
                System.out.println("\n");
                System.out.println("\n"+pre.toString());
            }
            goToMenu("Ecco le prenotazioni! Sono tornato nel menu");
        }else{
            goToMenu("Non hai prenotazioni");
        }
    }


    //UC13
    public void commentaRecensione()  {
        Map<Integer, Struttura> map = sistema.commentaRecensione();
        Scanner scanner = new Scanner(System.in);
        for (Struttura stn : map.values()) {
            System.out.println("\n"+stn.toString());
        }
        int id;
        while (true){
            System.out.println("Scegli la recensione");
            id = scanner.nextInt();
            if (map.containsKey(id)) {
                break;
            }
        }
        selStrut(id);
    }

    public void selStrut(int id) {
        sistema.selStrut(id);
        getRecensioniComm();

    }

    public  void getRecensioniComm() {
        ArrayList<Recensione> listRec = sistema.getRecensioniComm();
        Scanner scanner = new Scanner(System.in);

        if (!listRec.isEmpty()){
            for (int i = 0; i < listRec.size(); i++){
                System.out.println("\n"+listRec.get(i).toString());
            }
            int id = 0;
            boolean trv = true;
            while (trv){
                System.out.println("Scegli la Struttura");
                id = scanner.nextInt();
                for (int i = 0; i < listRec.size(); i++){
                    if (listRec.get(i).getId() == id){
                        trv = false;
                        break;
                    }
                }
            }

            if (id != 0){
                inserisciCommento(id);
            }else{
                goToMenu("Errore nell'inserimento della struttura");
            }
        }else{
            goToMenu("Non hai recensioni");
        }

    }

    public void inserisciCommento(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInserisci commento:");
        String commento = scanner.nextLine();
        sistema.inserisciCommento(commento, id);
        goToMenu("Commento inserito!");
    }
    

    public void confermaAsisstenza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi confermare la richiesta?\nDigita CONFERMA");
        String sv = scanner.nextLine();
        if (sv.equals("CONFERMA")){
            sistema.confermaAssistenza();
            goToMenu("Richiesta assistenza inoltrata!");
        }else{
            goToMenu("Richiesta assistenza non inoltrata!");
        }
    }

    public void visualizzaRecensioni(){
        ArrayList<Recensione> sv = sistema.visualizzaRecensioni();

        if(sv.isEmpty()){
            goToMenu("Non ci sono recensioni");
        }else{
            for(int i = 0; i < sv.size(); i++){
                System.out.println("\n"+sv.toString());
            }
            goToMenu("Ecco le recensioni, torno al menu.");

        }
    }

    public void modificaStruttura() {
        Map<Integer, Struttura> mappaStrutture = sistema.modificaStruttura();
        Scanner scanner = new Scanner(System.in);
        for (Struttura str : mappaStrutture.values()){
            System.out.println("\n"+str.toString());
        }
        int id = 0;
        while (true){
            System.out.println("Scegli struttura");
            id = scanner.nextInt();
            if (mappaStrutture.containsKey(id)){
                break;
            }
            System.out.println("Id non corretto");
        }

        //PERCHé ABBIAMO STCORRENTE E ULTRASTRUCTURE
        Struttura st =  sistema.selStrut(id);
        if (st instanceof CasaVacanze){
            modificaCV();
        }else{
            modificaBeb();
        }
        goToMenu("Modifiche effettuate!\n"+mappaStrutture.get(id).toString());
    }

    public void modificaCV(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listaserv = new ArrayList<>();
        String nome = "";
        String descrizione = "";
        boolean trv = true;
        int id = 0;

        //nome
        while (trv){
            System.out.println("Vuoi modificare il nome?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci nome:");
                    scanner.nextLine();
                    nome = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                    nome = null;
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        //descrizione
        trv = true;
        while (trv){
            System.out.println("Vuoi modificare la descrizione?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci descrizione:");
                    scanner.nextLine();
                    descrizione = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                   descrizione=null;
                    // descrizione = sistema.getStrutturascelta().getDescrizione();
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }
        //prezzo
        trv = true;
        float prezzo = 0;
        while (trv){
            System.out.println("Vuoi modificare il prezzo?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci prezzo (x,y):");
                    prezzo =scanner.nextFloat();
                    trv = false;
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        //listaserv
        trv = true;
        while (trv){
            System.out.println("Vuoi aggiungere servizi?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    while (true){
                        HashMap<Integer, Servizio> servizi = sistema.getServizi();
                        for (Servizio sv : servizi.values()){
                            System.out.println("\n"+sv.toString());
                        }
                        System.out.println("Inserisci 975 per annullare l'inserimento\nInserisci id:");
                        id = scanner.nextInt();
                        if(servizi.containsKey(id)){
                            listaserv.add(id);
                        }else{
                            System.out.println("Id di nessun servizio");
                        }

                        if (id == 975){
                            break;
                        }
                    }
                    trv = false;
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        sistema.modificaCV(nome, descrizione, listaserv, prezzo);

    }

    public void modificaBeb() {
        Scanner scanner = new Scanner(System.in);
        String nome = "";
        String descrizione = "";
        boolean trv = true;
        int id = 0;

        while (trv){
            System.out.println("Vuoi modificare il nome?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci nome:");
                    scanner.nextLine();
                    nome = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                    nome = sistema.getStrutturascelta().getNome();
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        trv = true;
        while (trv){
            System.out.println("Vuoi modificare la descrizione?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci nome:");
                    scanner.nextLine();
                    descrizione = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                    descrizione = sistema.getStrutturascelta().getNome();
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        sistema.modificaBeb(nome, descrizione);

        trv = true;
        while (trv){
            System.out.println("Vuoi aggiungere una stanza?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    sistema.setStcorrente(sistema.getStrutturascelta());
                    inserisciStanza();
                    sistema.setStcorrente(null);
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        trv = true;
        while (trv){
            System.out.println("Vuoi modificare una stanza?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    getStanze();
                    modStanza();
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }
    }

    public void getStanze(){
        Map<String, Stanza> mapStanze = sistema.getStanze();
        Scanner scanner = new Scanner(System.in);
        String id = "";
        for (Stanza stn : mapStanze.values()){
            System.out.println("\n"+stn.toStrings());
        }

        while (true){
            System.out.println("Inserisci id:");
            id = scanner.nextLine();
            if (mapStanze.containsKey(id)){
                break;
            }else{
                System.out.println("ID Sbagliato");
            }
        }
        selStanza(id);
    }

    private void modStanza() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listaserv = new ArrayList<>();
        String nome = "";
        String descrizione = "";
        boolean trv = true;
        int id = 0;

        //nome
        while (trv){
            System.out.println("Vuoi modificare il nome?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci nome:");
                    scanner.nextLine();
                    nome = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                    nome = null;
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        //descrizione
        trv = true;
        while (trv){
            System.out.println("Vuoi modificare la descrizione?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci descrizione:");
                    scanner.nextLine();
                    descrizione = scanner.nextLine();
                    trv = false;
                    break;
                case 2:
                    descrizione = null;
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        //prezzo
        trv = true;
        float prezzo = 0;
        while (trv){
            System.out.println("Vuoi modificare il prezzo?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    System.out.println("Inserisci prezzo (x,y):");
                    scanner.nextLine();
                    prezzo =scanner.nextFloat();
                    trv = false;
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        //listaserv
        trv = true;
        while (trv){
            System.out.println("Vuoi aggiungere servizi?\n1. Si\n2. No");
            id = scanner.nextInt();
            switch (id){
                case 1:
                    while (true){
                        HashMap<Integer, Servizio> servizi = sistema.getServizi();
                        for (Servizio sv : servizi.values()){
                            System.out.println("\n"+sv.toString());
                        }
                        System.out.println("Inserisci 975 per annullare l'inserimento\nInserisci id:");
                        id = scanner.nextInt();
                        if(servizi.containsKey(id)){
                            listaserv.add(id);
                        }else{
                            System.out.println("Id di nessun servizio");
                        }

                        if (id == 975){
                            break;
                        }
                    }
                    trv = false;
                    break;
                case 2:
                    trv = false;
                    break;
                default:
                    goToMenu("Hai inserito una scelta sbagliata");
                    break;
            }
        }

        sistema.modStanza(nome, descrizione, listaserv, prezzo);
    }

    public void selStanza(String id){
        sistema.selStanza(id);
    }
}