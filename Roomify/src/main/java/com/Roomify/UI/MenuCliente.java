package com.Roomify.UI;

import com.Roomify.*;
import com.Roomify.Exception.LogException;

import javax.sql.rowset.serial.SQLOutputImpl;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MenuCliente extends Menu {


    protected void displayMenu(){
        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Prenota alloggio");
        System.out.println("2. Visualizza prenotazioni");
        System.out.println("3. Recensisci struttura");
        System.out.println("7. Logout");
    }


    @Override
    protected void processaScelta(int scelta) throws LogException {
        switch (scelta) {
            case 1:
            //    prenotaAlloggio();

                break;
            case 2:
               // inserisciRecensioneEvento();
                break;
            case 3:
               // visualizzaRecensioni();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Opzione non valida");
        }
    }
    private void prenotaAlloggio() throws Exception {
        Scanner input = new Scanner(System.in);
        LocalDate dataInizio = null;
        LocalDate dataFine = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        System.out.println("Inserisci città:");
        String citta = input.nextLine();

        System.out.println("Inserisci data inizio nel formato YYYY-MM-DD:");
        String dataIn = input.nextLine();

        System.out.println("Inserisci data fine nel formato YYYY-MM-DD:");
        String dataFi = input.nextLine();
        try {
            dataInizio= LocalDate.parse(dataIn, formatter);
            dataFine = LocalDate.parse(dataFi, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato data non valido. Assicurati di usare il formato yyyy-MM-dd.");
        }

        System.out.println("Inserisci numeri di ospiti: ");
        int nOspiti = input.nextInt();



        ArrayList<Struttura> str = sistema.prenotaAlloggio(citta, dataInizio, dataFine, nOspiti);
        for (int i = 0; i < str.size(); i++){
            System.out.println(str.toString());
            System.out.println("\n");
        }

       selezionaStruttura();

    }

  private void selezionaStruttura() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci id");
        int id = input.nextInt();

        Struttura st = sistema.selezionaStruttura(id);

        if(st instanceof Beb){
            Map<String, Stanza> map = ((Beb) st).getListaStanze();
            for (Stanza stn : map.values()){
                System.out.println("\n");
              //  System.out.println(stn.toString());
            }

            System.out.println("\nInserisci la stanza che vuoi prenotare:");
            id = input.nextInt();
            selezionaStanza(id);
        }else{
            confermaPrenotazione();
        }


    }

    public void selezionaStanza(int id){
        sistema.selezionaStanza(id);
    }

    public void confermaPrenotazione() throws Exception {
        sistema.confermaPrenotazione();
        menu();
    }

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
         //   System.out.println("\n"+pre.toString());
        }
    }
    //UC4
    public void annullaPrenotazione(){
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Prenotazione> map = sistema.annullaPrenotazione();
        for(Prenotazione pre : map.values()){
           // System.out.println("\n"+pre.toString());
        }
        System.out.println("\nInserisci id prenotazione:");
        int id = scanner.nextInt();
        while(true){
            for(Prenotazione pre : map.values()){
                if(pre.getId() == id){
                    selezionaPrenotazione(id);
                }
            }
        }
    }

    public void selezionaPrenotazione(int id){
        sistema.selezionaPrenotazione(id);
    }

    //UC6
    public void nuovaRecensione(){
        Map<Integer, Struttura> map = sistema.nuovaRecensione();
        Scanner scanner = new Scanner(System.in);
        for(Struttura st : map.values()){
         //   System.out.println("\n"+st.toString());
        }
        System.out.println("\nInserisci id struttura:");
        int id = scanner.nextInt();
        selezionaPrenotazione(id);
    }

    public void selezionaStrutturaRec(int id){
        sistema.selezionaStrutturaRec(id);
    }

    public void inserisciRecensione(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci valutazione in numero: 1-5");
        int id1 = scanner.nextInt();

        System.out.println("Inserisci valutazione in numero: 1-5");
        String sv = scanner.nextLine();

        sistema.inserisciRecensione(id1, sv);
    }
}
