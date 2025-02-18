package com.Roomify.UI;

import com.Roomify.Beb;
import com.Roomify.Cliente;
import com.Roomify.Exception.LogException;
import com.Roomify.Struttura;

import javax.sql.rowset.serial.SQLOutputImpl;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
                prenotaAlloggio();

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
    private void prenotaAlloggio() throws LogException {
        Scanner input = new Scanner(System.in);
        LocalDate dataInizio;
        LocalDate dataFine;
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
      /*  ArrayList<Struttura> str = sistema.prenotaAlloggio(citta, dataInizio, dataFine, nOspiti);
        for (int i = 0; i < str.size(); i++){
            System.out.println(str.toString());
            System.out.println("\n");
        }
*/
        selezionaStruttura();

    }

    private void selezionaStruttura(){
        Scanner input = new Scanner(System.in);

        System.out.println("Inserisci id");
        int id = input.nextInt();

        Struttura st = sistema.selezionaStruttura(id);

        if(st instanceof Beb){
            selezionaStanza();
        }


    }

    public void selezionaStanza(){

    }
}
