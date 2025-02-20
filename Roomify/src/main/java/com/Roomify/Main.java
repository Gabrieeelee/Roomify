package com.Roomify;

import java.sql.SQLOutput;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Roomify roomify= Roomify.getInstance();

       /* roomify.registrazioneHost("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        roomify.selezionaAbbonamento(3);
        roomify.confermaRegistrazione();

        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci id recensione");
        int id = input.nextInt();

        Cliente cl=(Cliente)roomify.getListaUtenti().get(7);
        roomify.login(cl);

        roomify.richiestaAssistenza("Problemi con Recensione,voglio eliminarla",id,"Recensione");
/*
        Host ho=(Host)roomify.getListaUtenti().get(8);
        roomify.login(ho);

        System.out.println("Inserisci id Recensione");
        id = input.nextInt();

        roomify.richiestaAssistenza("Problemi con Recensione il cliente è maleducato",id,"Recensione");
*/
       /* System.out.println("Inserisci id ass");
        id = input.nextInt();
        roomify.selezionaRAssistenza(id);
        roomify.generaMessaggio("CIAO");
        roomify.confermaMessaggio();
*/

      /*  Scanner input = new Scanner(System.in);
        System.out.println("Inserisci id prenotazione");
        int id = input.nextInt();

        Cliente cl=(Cliente)roomify.getListaUtenti().get(1);
        roomify.login(cl);

        roomify.richiestaAssistenza("Problemi con prenotazione",id,"Prenotazione");

        Host ho=(Host)roomify.getListaUtenti().get(6);
        roomify.login(ho);

        System.out.println("Inserisci id prenotazione");
        id = input.nextInt();

        roomify.richiestaAssistenza("Problemi con prenotazione",id,"Prenotazione");

        roomify.richiestaAssistenza("non so come si registra un struttura",0,"altro");
*/
/*        Host host=(Host)roomify.getListaUtenti().get(6);
        roomify.login(host);
        roomify.visualizzaPrenotazioni();
        roomify.selezionaStrutturaVis(4);
*/
    /*    Cliente cl=(Cliente)roomify.getListaUtenti().get(1);
        roomify.login(cl);
        roomify.visualizzaPrenotazioni(LocalDate.of(2024,01,01), LocalDate.of(2025,12,31));

        roomify.annullaPrenotazione();

        /*for(Prenotazione pre : roomify.annullaPrenotazione().values()){
            System.out.println(pre.toString());
        }*/
        /*  System.out.println("Seleziona prenotazione da annullare");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) {
            System.out.println("Inserisci un numero valido.");
            input.next();
        }
        roomify.selezionaPrenotazione(input.nextInt());*/
       /* System.out.println("ciao");
        Host ho=new  Host("Gabriele", "Florio", LocalDate.of(2001, 6, 3), "FLR", "email2@test.com", "228", "123123123", "IT", "Via Francesco II");
        roomify.setUtenteCorrente(ho);
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");

        ArrayList<Integer> listcod= new ArrayList();
        listcod.add(1);
        listcod.add(4);
        roomify.inserisciStanza(1,"luxury", 4,111,123,"notti hot",listcod);
        roomify.inserisciStanza(2,"luxuryyyy", 1,111,123,"notti hot",listcod);
        roomify.inserisciStanza(3,"luxuryo", 2,111,123,"notti hot",listcod);
        roomify.confermaBeB();

        roomify.inserisciBeB(2,"la dolce melaaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
        roomify.inserisciStanza(1,"luxuryaaaaaa", 4,111,123,"notti hot",listcod);
        roomify.inserisciStanza(2,"luxuryyyybbbbb", 1,111,123,"notti hot",listcod);
        roomify.inserisciStanza(3,"luxuryoccccc", 2,111,123,"notti hot",listcod);
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(3,"una vita al mare","una vita al mare","italia","rosolini","siracusa",15200,"via enricomaria",10,3,100,100,listcod);
        roomify.confermaCasaVacanze();

        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
        roomify.setUtenteCorrente(cl);
        System.out.println("prova11111");
        Struttura st;
        ArrayList<Struttura> listadisp= new ArrayList();
        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2020,10,10),LocalDate.of(2020,10,13),2);
        st=roomify.selezionaStruttura(1,listadisp,cl);
        roomify.selezionaStanza(1,st,cl);
        roomify.confermaPrenotazione();
        System.out.println("prova1");


        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2020,5,10),LocalDate.of(2020,6,13),2);
        st=roomify.selezionaStruttura(2,listadisp,cl);
        roomify.selezionaStanza(3,st,cl);
        System.out.println("prova2");
        roomify.confermaPrenotazione();


        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2021,5,10),LocalDate.of(2021,6,13),2);
        st=roomify.selezionaStruttura(2,listadisp,cl);
        roomify.selezionaStanza(3,st,cl);
        System.out.println("prova2");
        roomify.confermaPrenotazione();


        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2020,5,10),LocalDate.of(2020,6,13),2);
        st=roomify.selezionaStruttura(2,listadisp,cl);
        roomify.selezionaStanza(3,st,cl);
        System.out.println("prova2");
        roomify.confermaPrenotazione();

        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2021,10,10),LocalDate.of(2021,10,13),2);
        st=roomify.selezionaStruttura(1,listadisp,cl);
        roomify.selezionaStanza(1,st,cl);
        roomify.confermaPrenotazione();
        System.out.println("prova1");
*/
       // roomify.visualizzaprenotazionistruesta();
       // roomify.prenotaAlloggio("taormina",LocalDate.of(2020,10,10),LocalDate.of(2020,10,12),2);
    }
}