package com.Roomify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PartnerAssicurativo extends Utente {
    private int nlicenza;
    private Map<Integer, PolizzaAssicurativa> listapolizze;
    private ArrayList<PolizzaAssicurativa> lPAssCorrente;
    private PolizzaAssicurativa polizzaCorrente;

    public PartnerAssicurativo(int id, String nome, String cognome, LocalDate dataDiNascita, String codicefiscale, String email, String telefono, int nlicenza) {
        super(id, nome, cognome, dataDiNascita, codicefiscale, email, telefono);
        this.nlicenza = nlicenza;
        listapolizze = new HashMap<>();
        lPAssCorrente = new ArrayList<>();
    }

    public int getNlicenza() {
        return nlicenza;
    }

    public Map<Integer, PolizzaAssicurativa> getListapolizze() {
        return listapolizze;
    }

    public void inseriNuovaPolizza(int id, String tipo, String copertura, int durata, String stato){
        lPAssCorrente.add(new PolizzaAssicurativa(generaNumeroId(), tipo, copertura, durata, stato,this));
    }

    public int generaNumeroId(){
        Random random=new Random();
        while(true){
            int randomNumber=random.nextInt(200000);
            if (!listapolizze.containsKey(randomNumber)){
                return randomNumber;
            }
        }
    }

    public void confermaInserimento(){
        for(int i = 0; i < lPAssCorrente.size(); i++){
            PolizzaAssicurativa pa = lPAssCorrente.get(i);
            listapolizze.put(pa.getId(),pa);
        }
        lPAssCorrente.removeAll(lPAssCorrente);
    }

    public void annullaInserimento(){
        lPAssCorrente.removeAll(lPAssCorrente);
    }

    public ArrayList<PolizzaAssicurativa> getlPAssCorrente() {
        return lPAssCorrente;
    }

    public ArrayList<PolizzaAssicurativa> mostraPolizze(int durata){
        ArrayList<PolizzaAssicurativa> listaPolizzeDisp = new ArrayList<>();
        for (PolizzaAssicurativa pa : listapolizze.values()){
            if(pa.isOk(durata)){
                listaPolizzeDisp.add(pa);
            }
        }
        return listaPolizzeDisp;
    }

    public PolizzaAssicurativa getPolizza(int id){
        return listapolizze.get(id);
    }

    public PolizzaAssicurativa selectPolizza(int id){
        polizzaCorrente=listapolizze.get(id);
        return polizzaCorrente;
    }

    public boolean  delPolizza(){
        listapolizze.remove(polizzaCorrente.getId());
        return true;
    }

    public boolean  disPolizza(){
        polizzaCorrente.setStato("Disattivato");
        return true;
    }

}
