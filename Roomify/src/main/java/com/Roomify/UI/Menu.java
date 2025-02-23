package com.Roomify.UI;

import com.Roomify.Exception.LogException;
import com.Roomify.Roomify;

import java.util.Scanner;

public abstract class Menu {
    protected static Roomify sistema;

    public Menu() {
        sistema = Roomify.getInstance();
    }

    public void menu() throws Exception {
        displayMenu();
        int scelta = getOperazioneUtente();
        processaScelta(scelta);
    }

    abstract void displayMenu();

    protected int getOperazioneUtente() {
        Scanner input = new Scanner(System.in);
        System.out.print("Inserisci il numero dell'opzione desiderata: ");
        while (!input.hasNextInt()) {
            System.out.println("Inserisci un numero valido.");
            input.next();
        }

        int i = input.nextInt();
        return i;

    }

    abstract void processaScelta(int scelta) throws Exception;

    protected void logout()  {
        sistema.logout();
    }
}
