package com.Roomify.UI;

import com.Roomify.Exception.LogException;
import com.Roomify.*;
import com.Roomify.Exception.LoginClienteException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LoginMenu extends Menu  {

    @Override
    void displayMenu() {
        System.out.println("Benvenuto! Seleziona un'opzione:");
        System.out.println("1. Accedi");
        System.out.println("2. Registrati");
        System.out.println("3. Chiudi");
    }

    @Override
    void processaScelta(int scelta) throws LogException {
        switch (scelta) {
            case 1:
       //         accedi();
                break;
            case 2:
     //           registrati();
                break;
            case 3:
   //             chiudi();
                break;
            default:
                System.out.println("Scelta non valida.");
                return;
        }
    }

 /*   private static void accedi() throws LogException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo identificativo:");
        int id = scanner.nextInt();
        // Cerca l'utente con l'identificativo fornito
        Utente user = sistema.getUtente(id);
        if (user == null) {
            System.out.println("Utente non trovato.");
            return;
        }

        // Se l'utente esiste, si effettua il login...
        sistema.logIn(id);
        // ... ed si associa il corretto menuStrategy
        if (user instanceof Host) {
            throw new LoginHostException();
        } else if (user instanceof Cliente) {
            throw new LoginClienteException();
        }
    }

    private static void registrati() throws LogException {
        LocalDate data = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il tuo nome:");
        String nome = scanner.nextLine();

        System.out.println("Inserisci il tuo cognome:");
        String cognome = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Inserisci la tua data di nascita:  (formato: yyyy-MM-dd)");
        String datadinascita = scanner.nextLine();

        try {
            // Proviamo a parsare la data
            data = LocalDate.parse(datadinascita, formatter);
            System.out.println("Data inserita: " + data);
        } catch (DateTimeParseException e) {
            System.out.println("Formato data non valido. Assicurati di usare il formato yyyy-MM-dd.");
        }

        System.out.println("Inserisci il tuo codice fiscale:");
        String cf = scanner.nextLine();
        do{
             cf= scanner.nextLine();
        }while(cf.length()>10);

        System.out.println("Inserisci la tua email:");
        String email = scanner.nextLine();

        System.out.println("Inserisci il tuo numero di telefono:");
        String ntelefono = scanner.nextLine();

        System.out.println("Seleziona il tipo di utente:");
        System.out.println("1. Host");
        System.out.println("2. Cliente");

        int iden;

        int tipoUtente = scanner.nextInt();
        switch (tipoUtente) {
            case 1:
                System.out.println("Inserisci la partita iva:");
                String piva = scanner.nextLine();

                System.out.println("Inserisci il tuo paese di residenza:");
                String paesediresidenza = scanner.nextLine();

                System.out.println("Inserisci la tua sede fiscale:");
                String sedefiscale = scanner.nextLine();

                iden = sistema.signUpLogIn(new Host(sistema.getId(), nome,cognome, data, cf, email, ntelefono, piva, paesediresidenza, sedefiscale));
                System.out.println("Il tuo identificativo e': " + iden);
                throw new LoginHostException();
            case 2:
                iden = sistema.signUpLogIn(new Cliente(sistema.getId(), nome, cognome, data, cf, email, ntelefono));
                System.out.println("Il tuo identificativo e': " + iden);
                throw new LoginClienteException();
            default:
                System.out.println("Tipo di utente non valido.");
                return;
        }

    }
    public void chiudi() throws LogException {
        throw new LogException();
    }*/
}
