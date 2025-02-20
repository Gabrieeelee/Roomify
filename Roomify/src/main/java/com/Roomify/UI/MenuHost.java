package com.Roomify.UI;

import com.Roomify.Beb;
import com.Roomify.Exception.LogException;

import java.util.Scanner;

public class MenuHost extends Menu{

    protected void displayMenu() {
        System.out.println("Seleziona un'opzione:");
        System.out.println("1. Inserisci un B&B");
        System.out.println("2. Inserisci una casa vacanze");
        System.out.println("7. Logout");

    }

    @Override
    protected void processaScelta(int scelta) throws LogException {
        switch (scelta) {
            case 1:
                    inserisciBeb();
                break;
            case 2:
                inserisciCasaVacanze();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Opzione non valida");
        }
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
    private void inserisciCasaVacanze() throws LogException {}
}
