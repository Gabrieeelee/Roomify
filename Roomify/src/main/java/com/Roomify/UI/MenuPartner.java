package com.Roomify.UI;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuPartner extends Menu{

    @Override
    void displayMenu() {
        System.out.println("1 - Inserisci nuova polizza");
        System.out.println("2 - Assistenza");
        System.out.println("7 - Logout");
    }

    @Override
    void processaScelta(int scelta) throws Exception {
        switch (scelta){
            case 1:
                inserisciNuovaPolizza();
                break;
            case 2:
                richiestaAssistenza();
                break;
            case 7:
                logout();
                Menu menu = new LoginMenu();
                menu.menu();
                break;
            default:
                System.out.println("Scelta inesistente");
        }

    }
    private void goToMenu(String msg){
        System.out.println(msg);
        try {
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void inserisciNuovaPolizza(){
        Scanner input = new Scanner(System.in);
        boolean trv = true;

        while(trv){
            System.out.println("Vuoi inserire una polizza?");
            System.out.println("1 - Si");
            System.out.println("2 - Chiudi inserimento");
            int sv = input.nextInt();
            switch (sv){
                case 1:
                    System.out.println("Inserisci ID:");
                    int id = input.nextInt();

                    System.out.println("Inserisci Tipo:");
                    input.nextLine();
                    String tipo = input.nextLine();

                    System.out.println("Inserisci Copertura:");
                    String copertura = input.nextLine();

                    System.out.println("Inserisci Durata (in giorni):");
                    int durata = input.nextInt();

                    System.out.println("Inserisci Stato:");
                    String stato = input.nextLine();
                    sistema.inseriNuovaPolizza(id, tipo, copertura, durata, stato);
                    break;
                case 2:
                    trv = false;
                    confermaInserimento();
                    break;
            }
        }

    }

    public void confermaInserimento(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vuoi confermare la registrazione?\nDigita CONFERMA");
        scanner.nextLine();
        String sv = scanner.nextLine();
        if (sv.equals("CONFERMA")){
            sistema.confermaInserimento();
            goToMenu("Inserimento completato!");

        }else{
            sistema.annullaInserimento();
            goToMenu("Non hai confermato l'inserimento");
        }
    }

    public void richiestaAssistenza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci descrizione del problema");
        scanner.nextLine();
        String str = scanner.nextLine();

        sistema.richiestaAssistenza(str, 0, "Generale");
        confermaAssistenza();
    }

    public void confermaAssistenza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi confermare la registrazione?\nDigita CONFERMA");
        scanner.nextLine();
        String sv = scanner.nextLine();
        if (sv.equals("CONFERMA")){
            sistema.confermaAssistenza();
            goToMenu("Inserimento completato!");

        }else{
            sistema.annullaInserimento();
            goToMenu("Non hai confermato l'inserimento");
        }
    }
}
