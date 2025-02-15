package com.Roomify;

import java.time.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Roomify roomify= Roomify.getInstance();
        roomify.inserisciBeB(1,"la dolce mela","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");

        ArrayList<Integer> listcod= new ArrayList();
        listcod.add(1);
        listcod.add(4);
        roomify.inserisciStanza(1,"luxury", 4,111,123,"notti hot",listcod);
    //    roomify.inserisciStanza(2,"luxuryyyy", 1,111,123,"notti hot",listcod);
   //     roomify.inserisciStanza(3,"luxuryo", 2,111,123,"notti hot",listcod);
        roomify.confermaBeB();

        roomify.inserisciBeB(2,"la dolce melaaaaaa","un bellissimo beb", "italia", "taormina", "messina", 98034, "non lo so");
      //  roomify.inserisciStanza(1,"luxuryaaaaaa", 4,111,123,"notti hot",listcod);
     //   roomify.inserisciStanza(2,"luxuryyyybbbbb", 1,111,123,"notti hot",listcod);
        roomify.inserisciStanza(3,"luxuryoccccc", 2,111,123,"notti hot",listcod);
        roomify.confermaBeB();

        roomify.inserisciCasavacanza(3,"una vita al mare","una vita al mare","italia","rosolini","siracusa",15200,"via enricomaria",10,3,100,100,listcod);
        roomify.confermaCasaVacanze();

        System.out.println("prova11111");
        Struttura st;
        ArrayList<Struttura> listadisp= new ArrayList();
        listadisp=roomify.prenotaAlloggio("taormina",LocalDate.of(2020,10,10),LocalDate.of(2020,10,13),2);
        Cliente cl=new Cliente("Enricomaria","florio",LocalDate.of(2020,11,5),"ojsdfng","asd@sdf.com","123123123");
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


       // roomify.visualizzaprenotazionistruesta();
       // roomify.prenotaAlloggio("taormina",LocalDate.of(2020,10,10),LocalDate.of(2020,10,12),2);
    }
}