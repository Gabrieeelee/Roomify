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

    public LocalDate getDatainizio() {
        return datainizio;
    }

    public LocalDate getDatafine() {
        return datafine;
    }

    public String getStato() {
        return stato;
    }

    public Prenotazione(int id, LocalDate datainizio, LocalDate datafine, String stato, Stanza st, Cliente cl) {
        this.id = id;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.stato = stato;
        this.st = st;
        this.cl = cl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatainizio(LocalDate datainizio) {
        this.datainizio = datainizio;
    }

    public void setDatafine(LocalDate datafine) {
        this.datafine = datafine;
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

    public Stanza getSt() {
        return st;
    }

    public Cliente getCl() {
        return cl;
    }

    public Struttura getStruttu() {
        return struttu;
    }

    public int getnOspiti() {
        return nOspiti;
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
        return "Prenotazione{" +
                "id=" + id +
                ", datainizio=" + datainizio +
                ", datafine=" + datafine +
                ", stato='" + stato + '\'' +
                ", st=" + st +
                ", cl=" + cl +
                ", struttu=" + struttu +
                ", costo totale=" + costoTotale +
                '}';
    }


    public Struttura isRecensibile(){
        if(LocalDate.now().isAfter(datafine)){
            return struttu;
        }
        else
            return null;
    }

    public boolean isAssicurabile(){
        if (LocalDate.now().isBefore(datainizio) && ChronoUnit.DAYS.between(LocalDate.now(), dataPrenotazione) <= 3){
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
}
