package com.Roomify.UI;

import com.Roomify.Assistenza.CategoriaAssistenza;
import com.Roomify.Assistenza.RichiestaAssistenza;
import com.Roomify.PolizzaAssicurativa;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class MenuPartner extends Menu{

    @Override
    void displayMenu() {
        System.out.println("1 - Inserisci nuova polizza");
        System.out.println("2 - Assistenza");
        System.out.println("3 - Visualizza assistenze");
        System.out.println("4 - Elimina polizza assicurativa");
        System.out.println("7 - Logout");
    }

    @Override
    void processaScelta(int scelta)  {
        switch (scelta){
            case 1:
                inserisciNuovaPolizza();
                break;
            case 2:
                assistenza();
                break;
            case 3:
                visualizzaAssistenza();
                break;
            case 4:
                deldisPolizza();
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

    private void visualizzaAssistenza() {
        Map<Integer, RichiestaAssistenza> map =sistema.visualizzaAssistenza();
        if (map.isEmpty()){
            goToMenu("Non hai assistenze");
        }else{
            for (RichiestaAssistenza ra : map.values()){
                System.out.println("\n"+ra.toString());
            }
        }
        goToMenu("Torno al menu");
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

                    System.out.println("Inserisci Tipo:");
                    input.nextLine();
                    String tipo = input.nextLine();

                    System.out.println("Inserisci Copertura:");
                    String copertura = input.nextLine();

                    System.out.println("Inserisci Durata (in giorni):");
                    int durata = input.nextInt();

                    System.out.println("Inserisci Stato:");
                    String stato = input.nextLine();
                    sistema.inseriNuovaPolizza( tipo, copertura, durata, stato);
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


    private void assistenza() {
        ArrayList<CategoriaAssistenza> list = sistema.assistenza();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).name().equals("ALTRI_MOTIVI"))
                System.out.println(i+" - "+list.get(i).name());
        }
        boolean trv = true;
        while(trv){
            System.out.println("Seleziona supporto");
            int id = scanner.nextInt();

            for (int i = 0; i < list.size(); i++){
                if (i == id){
                    selCat(list.get(i).name());
                    trv=false;
                    break;
                }
            }
        }
    }

    private void selCat(String id) {
        CategoriaAssistenza cat = sistema.selCat(id);
        switch (cat.name()){
            case "ALTRI_MOTIVI":
                richiestaAssistenza();
                confermaAssistenza();
                break;
        }
    }

    public void richiestaAssistenza(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci descrizione del problema");
        scanner.nextLine();
        String str = scanner.nextLine();

     //   sistema.richiestaAssistenza(str, 0, );
        sistema.richiestaAssistenza(str);
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

    private void deldisPolizza() {
        Map<Integer, PolizzaAssicurativa> mapPolizze = sistema.deldisPolizza();
        Scanner scanner = new Scanner(System.in);
        for (PolizzaAssicurativa pa : mapPolizze.values()){
            System.out.println(pa.toString());
        }
        int id = 0;
        if(!mapPolizze.isEmpty()){
            while (true){
                System.out.println("Inserisci id:");
                id = scanner.nextInt();
                if (mapPolizze.containsKey(id)){
                    break;
                }else{
                    System.out.println("ID sbagliato");
                }
            }
        } else  goToMenu("Non hai polizze assicurative! Ritorno al menu!");

        selPolizza(id);
    }

    private void selPolizza(int id) {
        sistema.selPolizza(id);
        Scanner scanner = new Scanner(System.in);
        int choose =0;
        boolean trv = true;
        while (trv){
            System.out.println("Scegli l'opzione");
            System.out.println("1 - Elimina polizza");
            System.out.println("2 - DisattivaPolizza");
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    delPolizza();
                    trv = false;
                    break;
                case 2:
                    disPolizza();
                    trv = false;
                    break;
                default:
                    System.out.println("ID sbagliato");
                    break;
            }
        }

        goToMenu("Ritorno al menu!");
    }

    private void disPolizza() {
        sistema.disPolizza();
        System.out.println("Disattivazine completata");
    }

    private void delPolizza() {
        sistema.delPolizza();
        System.out.println("Eliminazione completata");
    }

}
