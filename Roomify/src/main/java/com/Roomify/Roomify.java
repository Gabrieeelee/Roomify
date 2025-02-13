package com.Roomify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roomify {

    private static Roomify roomify;
    private Beb bebcorrente;
    private CasaVacanze cvcorrente;
    private Map<Integer,Struttura> mapStrutture;
    private HashMap<Integer, Servizio> servizi;

    private Roomify() {
        this.mapStrutture = new HashMap<>();
        loadServices();
    }

    public HashMap<Integer, Servizio> getServizi() {
        return servizi;
    }

    private void loadServices(){
        servizi = new HashMap<>();
        servizi.put(1, new Servizio(1, "Lavatrice", "Elettrodomestico utilizzato per lavare i vestiti"));
        servizi.put(2, new Servizio(2,"Microonde", "Elettrodomestico per riscaldare o cucinare velocemente"));
        servizi.put(3, new Servizio(3, "lavastoviglie", "Elettrodomestico utilizzato per lavare i piatti"));
        servizi.put(4, new Servizio(4, "vasca", "Elettrodomestico utilizzato per lavare i piatti"));
        System.out.println("Caricamento servizi completato");
    }

    public static Roomify getInstance() {
        if (roomify == null)
            roomify = new Roomify();
        else
            System.out.println("Istanza già creata");

        return roomify;
    }

    public void inserisciBeB(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo){
        this.bebcorrente = new Beb(id,nome, descrizione, paese, citta, provincia, cap, indirizzo);
        System.out.println("B&B inserito nella struttura corrente");
    }

    public void inserisciCasavacanza(int id, String nome, String descrizione, String paese, String citta, String provincia, int  cap, String indirizzo,int nMaxOspiti, int nVani, float prezzoNotte, int dimensione, ArrayList<Servizio> listaServizi){
        this.cvcorrente=new CasaVacanze(id, nome, descrizione, paese, citta, provincia, cap, indirizzo, nMaxOspiti, nVani, prezzoNotte, dimensione, listaServizi);
        System.out.println("Casa Vacanza inserita nella struttura corrente");
    }

    public void inserisciTariffa(String nome, int periodo, float fattoreMoltiplicativo){
        if(bebcorrente != null){

        }else if(cvcorrente != null){

        }else { System.out.println("Struttura non inizializzata");}
    }

    public void inserisciStanza(String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Integer> listacodservizi){
        if(bebcorrente != null){
            this.bebcorrente.inserisciStanza(nome,nospiti,prezzopernotte,dimensione,descrizione,listacodservizi);
            System.out.println("Stanza inserita in beb"+bebcorrente.getNome());
        }else{
            System.out.println("Struttura non inizializzata");
        }
    }

    public void confermaCasaVacanze(){
        mapStrutture.put(cvcorrente.getId(),cvcorrente);
    }

    public void confermaBeB(){
        mapStrutture.put(bebcorrente.getId(), bebcorrente);
        /*for (Map.Entry<Integer, Struttura> entry : mapStrutture.entrySet()) {
            System.out.println("Chiave: " + entry.getKey() + ", Valore: " + entry.getValue().getNome());
        }*/
    }

    public Beb getBebCorrente(){
        return bebcorrente;
    }
    public CasaVacanze getcvCorrente(){
        return cvcorrente;
    }

    public Map<Integer,Struttura> getElencoStrutture(){
        return mapStrutture;
    }

    
}
