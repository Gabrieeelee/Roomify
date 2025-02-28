package com.Roomify.UI;

import com.Roomify.Exception.LogException;
import com.Roomify.*;
import com.Roomify.Exception.LoginClienteException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;




public class LoginMenu extends Menu  {
    private static MenuCliente mc;
    private static MenuHost mh;
    private static MenuPartner mpa;
    private static MenuAmministratore ma;
    @Override
    void displayMenu() {
        System.out.println("Benvenuto! Seleziona un'opzione:");
        System.out.println("1. Accedi");
        System.out.println("2. Registrati");
        System.out.println("3. Chiudi");
    }

    @Override
    void processaScelta(int scelta) throws Exception {
        switch (scelta) {
            case 1:
                accedi();
                break;
            case 2:
                registrati();
                break;
            case 3:
                chiudi();
                break;
            default:
                System.out.println("Scelta non valida.");
                return;
        }
        System.out.println("finito");
    }

    private static void accedi() throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il tuo identificativo:");
        int id = scanner.nextInt();
        if(id == 99){
            ma = new MenuAmministratore();
            ma.menu();
        }
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
            mh = new MenuHost();
            mh.menu();
        } else if (user instanceof Cliente) {
            mc = new MenuCliente();
            try {
                mc.menu();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (user instanceof PartnerAssicurativo) {
            mpa = new MenuPartner();
            mpa.menu();
        }
    }

    //funziona
    private  void registrati() throws LogException {
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


        System.out.println("Inserisci la tua email:");
        String email = scanner.nextLine();

        System.out.println("Inserisci il tuo numero di telefono:");
        String ntelefono = scanner.nextLine();

        System.out.println("Seleziona il tipo di utente:");
        System.out.println("1. Host");
        System.out.println("2. Cliente");
        System.out.println("3. Partner Assicurativo");

        int tipoUtente = scanner.nextInt();
        switch (tipoUtente) {
            case 1:
                System.out.println("Inserisci la partita iva:");
                scanner.nextLine();
                String piva = scanner.nextLine();

                System.out.println("Inserisci il tuo paese di residenza:");
                String paesediresidenza = scanner.nextLine();

                System.out.println("Inserisci la tua sede fiscale:");
                String sedefiscale = scanner.nextLine();

                if(!sistema.registrazioneHost( nome,cognome, data, cf, email, ntelefono, piva, paesediresidenza, sedefiscale)){
                    goToMenu("Sei gia registrato come Host, puoi avere un solo account intestato a te!");
                }

                ArrayList<Abbonamento> listAbb = sistema.getListaAbbonamneto();
                //fare to string degli abbonamenti se non ci sono
                for(int i = 0; i < listAbb.size();i++){
                    System.out.println(listAbb.get(i).toString());
                }
                boolean trvt = true;
                while (trvt){
                    System.out.println("Seleziona l'abbonamento: ");
                    int sv = scanner.nextInt();
                    for (int i = 0; i < listAbb.size(); i++){
                        if (listAbb.get(i).getId() == sv){
                            trvt = false;
                            break;
                        }
                    }
                }

                System.out.println("Vuoi confermare la registrazione?\nDigita CONFERMA");
                scanner.nextLine();
                String sv = scanner.nextLine();
                if (sv.equals("CONFERMA")){
                    sistema.confermaRegistrazione();
                    System.out.println("Il tuo identificativo e': " + sistema.getUtenteCorrente().getId());
                }else{
                    System.out.println("Non hai confermato");
                }

            case 2:
                if(!sistema.registrazioneCliente(nome, cognome, data, cf, email, ntelefono)){
                    goToMenu("Sei gia registrato come Cliente, puoi avere un solo account intestato a te!");
                }
                System.out.println("Vuoi confermare la registrazione?\nDigita CONFERMA");
                scanner.nextLine();
                String svs = scanner.nextLine();
                if (svs.equals("CONFERMA")){
                    sistema.confermaRegistrazione();
                    System.out.println("Il tuo identificativo e': " + sistema.getUtenteCorrente().getId());

                }else{
                    System.out.println("Non hai confermato");
                }

            case 3:
                System.out.println("Inserisci il numero di licenza:");
                int nlicenza = scanner.nextInt();

                if(!sistema.registrazionePartner(nome, cognome, data, cf, ntelefono, email, nlicenza)){
                    goToMenu("Sei gia registrato come Partner Assicurativo, puoi avere un solo account intestato a te!");
                }
                System.out.println("Vuoi confermare la registrazione?\nDigita CONFERMA");
                scanner.nextLine();
                String conferma = scanner.nextLine();
                if (conferma.equals("CONFERMA")){
                    sistema.confermaRegistrazione();
                    System.out.println("Il tuo identificativo e': " + sistema.getUtenteCorrente().getId());

                }else{
                    System.out.println("Non hai confermato");
                }

            default:
                System.out.println("Tipo di utente non valido.");
                return;
        }

    }

    public void chiudi() throws LogException {
      System.exit(0);
    }

    private void goToMenu(String msg){
        System.out.println(msg);
        try {
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}