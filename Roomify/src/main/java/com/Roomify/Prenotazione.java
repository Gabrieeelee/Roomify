package com.Roomify;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Prenotazione {

    private int id;
    private LocalDate datainizio;
    private LocalDate datafine;
    private LocalDate dataPrenotazione;
    private String stato;
    private int nOspiti;
    private PolizzaAssicurativa polizza;
    float costoTotale;

    Stanza st;

    Cliente cl;
    Struttura struttu;

    public Prenotazione() {
    }

    public Prenotazione(int id, LocalDate datainizio, LocalDate datafine, String stato,int nOspiti, Struttura struttu, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.struttu = struttu;
        this.cl = cl;
        this.nOspiti = nOspiti;
        this.dataPrenotazione=LocalDate.now();
    }


    public Prenotazione( LocalDate datainizio, LocalDate datafine,int nOspiti) {
        this.nOspiti = nOspiti;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.dataPrenotazione=LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public LocalDate getDatainizio() {
        return datainizio;
    }

    public LocalDate getDatafine() {
        return datafine;
    }

    public String getStato() {
        return stato;
    }

    public Prenotazione(int id, LocalDate datainizio, LocalDate datafine, String stato,int nOspiti,Struttura struttu, Stanza st, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.nOspiti = nOspiti;
        this.struttu = struttu;
        this.st = st;
        this.cl = cl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setSt(Stanza st) {
        this.st = st;
    }

    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    public void setStruttu(Struttura struttu) {
        this.struttu = struttu;
    }

    public Stanza getStanza(){
        return st;
    }

    public Struttura getStruttu() {
        return struttu;
    }

    public int getnOspiti() {
        return nOspiti;
    }

    public float getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale() {
       long p = ChronoUnit.DAYS.between(datainizio, datafine);
       float fattoreMoltiplicativo=1;
       for(int i=0;i<struttu.getListatf().size();i++){
           if(datainizio.getMonthValue()>struttu.getListatf().get(i).getmeseinizio() && datafine.getMonthValue()<struttu.getListatf().get(i).getmesefine()){
             fattoreMoltiplicativo = struttu.getListatf().get(i).getFattoreMoltiplicativo();
           }
       }
       if(struttu instanceof Beb){
           costoTotale=st.getPrezzopernotte()*p*fattoreMoltiplicativo;
       }else{
           costoTotale=((CasaVacanze)struttu).getPrezzoNotte()*p*fattoreMoltiplicativo;
       }
    }

    @Override
    public String toString() {
        return "Prenotazione: " +
                "\n|ID=" + id +
                "\n|Datainizio=" + datainizio +
                "\n|Datafine=" + datafine +
                "\n|Stato='" + stato + '\'' +
                "\n|Struttura=" + struttu.getNome() +
                (st != null ? "\n|Stanza=" + st.getNome() : "") +
                "\n|Cliente=" + cl.getCodicefiscale() +
                "\n|Costo Totale=" + costoTotale+
                "\n|Polizza:" + polizza;
    }


    public Struttura isRecensibile(){
        if(LocalDate.now().isAfter(datafine)){
            return struttu;
        }
        else
            return null;
    }

    public boolean isAssicurabile(){
        if (LocalDate.now().isBefore(datainizio) && (ChronoUnit.DAYS.between(LocalDate.now(), dataPrenotazione) <= 3)&& ChronoUnit.DAYS.between(LocalDate.now(), dataPrenotazione) >= 0){
            return true;
        }

        return false;
    }

    public void addPolizza(PolizzaAssicurativa polizza) {
        this.polizza = polizza;
    }

    public int getDurata(){
        return (int) ChronoUnit.DAYS.between(this.getDatainizio(), this.getDatafine());
    }

    public PolizzaAssicurativa getPolizza() {
        return polizza;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public boolean isAnnullabile() {
        if (!stato.equals("Prenotazione annullata") && LocalDate.now().isBefore(datainizio)){
            return true;
        }
        return false;
    }
}
