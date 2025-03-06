package com.Roomify.UI;

import com.Roomify.*;
import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MenuCliente extends Menu {

    @Override
    protected void displayMenu(){
        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Prenota alloggio");
        System.out.println("2. Visualizza prenotazioni");
        System.out.println("3. Annulla prenotazione");
        System.out.println("4. Recensisci struttura");
        System.out.println("5. Assistenza");
        System.out.println("6. Visualizza assistenza");
        System.out.println("7. Aggiungi Assicurazione");
        System.out.println("8. Visualizza messaggi richiesta assistenza");
        System.out.println("9. Visualizza recensioni commentate dall'host");
        System.out.println("99. Logout");
    }


    @Override
    protected void processaScelta(int scelta) {
        while(true){
            switch (scelta) {
                case 1:
                    prenotaAlloggio();
                    break;
                case 2:
                    visualizzaPrenotazioniCliente();
                    break;
                case 3:
                    annullaPrenotazione();
                    break;
                case 4:
                    nuovaRecensione();
                    break;
                case 5:
                    assistenza();
                    break;
                case 6:
                    visualizzaAssistenza();
                    break;
                case 7:
                    aggiungiAssicurazione();
                    break;
                case 8:
                    visualizzaMessaggi();
                    break;
                case 9:
                    visualizzaRecensioniCommentati();
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
    }

    private void visualizzaRecensioniCommentati() {
        ArrayList<Recensione> list = sistema.visualizzaRecensioniCommentati();
        Scanner scanner = new Scanner(System.in);
        if (!list.isEmpty()){
            for(int i = 0; i < list.size(); i++){
                System.out.println("\n"+list.get(i).toString());
            }
        }else{
            goToMenu("Non hai recensioni commentati");
        }

        int id = 0;
        boolean trv = true;
        while (trv){
            System.out.println("Inserisci id:");
            id = scanner.nextInt();
            for (int i = 0; i <list.size(); i++){
                if (list.get(i).getId() == id){
                    trv = false;
                    break;
                }
            }
            if(!trv) break;
            System.out.println("Id inserito non corretto");
        }

        visualizzaRispostaHost(id);
    }

    private void visualizzaRispostaHost(int id) {
        String commento = sistema.visualizzaRispostaHost(id);
        if (commento != null){
            System.out.println("Ecco il commento dell'host:");
            System.out.println(commento);
        }else{
            goToMenu("Errore nell'ottenere il commento dell'host della recensione id: "+id);
        }
        goToMenu("Ritorno al menu");
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
                richiestaAssistenzaa();
                confermaAsisstenza();
                break;
            case "RECENSIONE":
                getListaRec();
                richiestaAssistenzaa();
                confermaAsisstenza();
                break;
            case "ALTRI_MOTIVI":
                richiestaAssistenzaa();
                confermaAsisstenza();
                break;
        }
    }

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
                System.out.println(pre.toString());
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

    public void richiestaAssistenzaa(){
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

    private void prenotaAlloggio() {
        boolean a=true;
        ArrayList<Struttura> str;
        Scanner input = new Scanner(System.in);
        do {
            a=true;
            LocalDate dataInizio = null;
            LocalDate dataFine = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            System.out.println("Inserisci città:");
            String citta = input.nextLine();

            do {
                System.out.println("Inserisci data inizio nel formato YYYY-MM-DD:");
                String dataIn = input.nextLine();
                try {
                    dataInizio = LocalDate.parse(dataIn, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data non valido. Assicurati di usare il formato yyyy-MM-dd.");
                }
            }while(dataInizio.isBefore(LocalDate.now()) || dataInizio.isEqual(LocalDate.now()));
            do {
                System.out.println("Inserisci data fine nel formato YYYY-MM-DD:");
                String dataFi = input.nextLine();
                try {
                    dataFine = LocalDate.parse(dataFi, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato data non valido. Assicurati di usare il formato yyyy-MM-dd.");
                }
            }while(dataFine.isBefore(LocalDate.now()) || dataFine.isEqual(LocalDate.now()));

            System.out.println("Inserisci numeri di ospiti: ");
            int nOspiti = input.nextInt();

            str = sistema.prenotaAlloggio(citta, dataInizio, dataFine, nOspiti);

            if(str.isEmpty()){
                a=false;
                System.out.println("Non abbiamo trovato nessun alloggio per queste date e questa città. Riprova!");
                input.nextLine();
            }
        }while(!a);

        for (int i = 0; i < str.size(); i++){
            System.out.println("\n"+str.get(i).toString());
        }
        //controllo se l'id inserito sia giusto
        int sv = 0;
        while(sv < 3){
            System.out.println("Inserisci id");
            int id = input.nextInt();
            for(int i = 0; i < str.size(); i++){
                if (str.get(i).getId() == id){
                    selezionaStruttura(id);
                }
            }
            System.out.println("\nId inserito è sbagliato...");
            sv++;
        }
        goToMenu("Hai inserito troppe volte un id sbagliato. \nRitorno nel menu...");
    }

    private void selezionaStruttura(int id) {
        Scanner input = new Scanner(System.in);
        Struttura st = sistema.selezionaStruttura(id);
        if(st instanceof Beb){
            ArrayList<Stanza> listastanze=getStanzeDisp();
            for(int i=0;i<listastanze.size();i++){
                System.out.println("\n"+listastanze.get(i).toStrings());
            }

            int sv = 0;
            boolean bool = false;
            while(sv < 3){
                System.out.println("Inserisci id");
                id = input.nextInt();
                for(int i=0;i<listastanze.size();i++){
                    if (listastanze.get(i).getId() == id){
                        selezionaStanza(id);
                        bool = true;
                        break;
                    }
                }
                if(!bool){
                    System.out.println("\nId inserito è sbagliato...");
                    sv++;
                }else{
                    break;
                }
            }
            goToMenu(" Ritorno nel menu...");
        }else{
            System.out.println("\n"+((CasaVacanze)sistema.getStrutturascelta()).toString());
            confermaPrenotazione();
        }
    }

    public ArrayList<Stanza> getStanzeDisp(){
        return sistema.getStanzeDisp();
    }

    public void selezionaStanza(int id) {
        sistema.selezionaStanza(id);
        System.out.println("\n"+((Beb)sistema.getStrutturascelta()).getStanzaCorrente().toStrings());
        confermaPrenotazione();

    }

    public void confermaPrenotazione() {
        sistema.confermaPrenotazione();
        goToMenu("Prenotazione confermata!");
    }

    //UC4
    public void visualizzaPrenotazioniCliente(){
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


        System.out.println("Inserisci data fine yyyy-MM-dd");
        sv = input.nextLine();
        try{
            dataFine = LocalDate.parse(sv, formatter);
        }catch(Exception e){
            System.out.println("Formato non valido");
        }
        if(dataInizio != null && dataFine != null){

            map = sistema.visualizzaPrenotazioniCliente(dataInizio, dataFine);
        }
        if (!map.values().isEmpty()){
            for(Prenotazione pre : map.values()){
                System.out.println("\n"+pre.toString());
            }
        }else{
            System.out.println("\nNon è stato trovato nessuna prenotazione");
        }
        goToMenu("Esco dal menu visualizza prenotazione");
    }

    //UC5
    public void annullaPrenotazione(){
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Prenotazione> map = sistema.annullaPrenotazione();
        if (!map.isEmpty()){
            for(Prenotazione pre : map.values()){
                if (!pre.getStato().equals("Prenotazione annullata")){
                    System.out.println("\n"+pre.toString());
                }
            }
        }else{
            goToMenu("Non hai prenotazioni da annullare");
        }
        System.out.println("\nInserisci id prenotazione:");
        int id = scanner.nextInt();
        for(Prenotazione pre : map.values()){
            if(pre.getId() == id){
                selezionaPrenotazione(id);
            }
        }

        goToMenu("Non è stato trovato nessuna prenotazione");

    }

    public void selezionaPrenotazione(int id){
        if (sistema.selezionaPrenotazione(id)){
            goToMenu("Prenotazione "+id+" annullata");
        }else{
            goToMenu("Prenotazione "+id+" non annullata, è già avvenuta.");
        }

    }

    //UC6
    public void nuovaRecensione(){
        Map<Integer, Struttura> map = sistema.nuovaRecensione();
        Scanner scanner = new Scanner(System.in);
        if (!map.isEmpty()){
            for(Struttura st : map.values()){
                System.out.println("\n"+st.toString());
            }
            do {
                System.out.println("\nInserisci id struttura:");
                int id = scanner.nextInt();
                if (map.containsKey(id)) {
                    selezionaStrutturaRec(id);
                    break;
                } else {
                    System.out.println("Non hai alloggiato in questa struttura, scegline una in cui hai alloggiato!");
                }
            }while(true);
        }else{
            goToMenu("Non hai nessuna recensione da effettuare");
        }
    }

    public void selezionaStrutturaRec(int id){
        sistema.selezionaStrutturaRec(id);
        inserisciRecensione();
    }

    public void inserisciRecensione(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci valutazione in numero: 1-5");
        int id1 = scanner.nextInt();

        System.out.println("Inserisci commento");
        scanner.nextLine();
        String sv = scanner.nextLine();

        sistema.inserisciRecensione(id1, sv);
        goToMenu("Recensione inserita!");
    }

    //UC11


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

    //UC10
    public void aggiungiAssicurazione() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Prenotazione> sv = sistema.aggiungiAssicurazione();
        for (int i = 0; i < sv.size(); i++) {
            System.out.println("\n"+sv.get(i).toString());
        }
        int id = 0;
        boolean trvt = true;
        while (trvt) {
            System.out.println("Inserisci id:");
            id = scanner.nextInt();
            for (int i = 0; i < sv.size(); i++) {
                if (sv.get(i).getId() == id) {
                    trvt = false;
                    break;
                }
            }
            if(trvt)
                System.out.println("La prenotazione scelta non è esistente");
        }
        selPren(id);


    }

    public void selPren(int id){
        sistema.selPren(id);
        mostraPolizze();
    }

    public void mostraPolizze() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<PolizzaAssicurativa> sv = sistema.mostraPolizze();
        for (int i = 0; i < sv.size(); i++) {
            System.out.println("\n"+sv.get(i).toString());
        }
        int j=0;
        int id = 0;
        boolean trvt = true;
        while (trvt) {
            System.out.println("Inserisci id:");
            id = scanner.nextInt();
            for (int i = 0; i < sv.size(); i++) {
                if (sv.get(i).getId() == id) {
                    j=i;
                    trvt = false;
                    break;
                }
            }
        }

        selezionaPolizza(sv.get(j).getPartnerAssicurativo().getId(),id);
    }

    private void selezionaPolizza(int idPartner, int idPolizza) {
        sistema.selezionaPolizza(idPartner, idPolizza);
        confermaAssicurazione();
    }

    private void confermaAssicurazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi confermare l'assicurazioen?\nDigita CONFERMA");
        scanner.nextLine();
        String sv = scanner.nextLine();
        if (sv.equals("CONFERMA")){
            sistema.confermaAssicurazione();
            goToMenu("Assicurazione effettuata");
        }else{
            goToMenu("Assicurazione non inserita");
        }
    }
}