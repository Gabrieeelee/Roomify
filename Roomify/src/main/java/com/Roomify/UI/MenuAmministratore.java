package com.Roomify.UI;

import com.Roomify.Assistenza.RichiestaAssistenza;

import java.util.Map;
import java.util.Scanner;

public class MenuAmministratore extends Menu{

    @Override
    void displayMenu() {
        System.out.println("1 - Rispondi richiesta assistenza");
        System.out.println("7 - Logout");
    }

    @Override
    void processaScelta(int scelta) throws Exception {
        switch (scelta){
            case 1:
                selezionaRAssistenza();
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

    public void selezionaRAssistenza(){
        Map<Integer, RichiestaAssistenza> map = sistema.getListaAssistenza();
        Scanner scanner = new Scanner(System.in);
        for(RichiestaAssistenza ra : map.values()){
            if(!ra.getStato().equals("Chiuso")){
                System.out.println(ra.toString());
            }else{
                goToMenu("Non esistono assistenze");
            }
        }
        int id = 0;
        while(true){
            System.out.println("Inserisci id dell'assistenza");
            id = scanner.nextInt();
            if (map.containsKey(id)) break;

            System.out.println("Errore nell'inserimento");
        }

        sistema.selezionaRAssistenza(id);
        generaMessaggio();
    }

    private void generaMessaggio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci messaggio di risposta");
        String str = scanner.nextLine();
        sistema.generaMessaggio(str);
        confermaMessaggio();
    }

    private void confermaMessaggio() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi confermare la risposta?\nDigita CONFERMA");
        scanner.nextLine();
        String sv = scanner.nextLine();
        if (sv.equals("CONFERMA")){
            sistema.confermaMessaggio();
            goToMenu("Inserimento completato!");

        }else{
            sistema.annullaInserimento();
            goToMenu("Non hai confermato l'inserimento");
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
}
