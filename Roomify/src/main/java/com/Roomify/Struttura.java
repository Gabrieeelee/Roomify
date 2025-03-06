package com.Roomify;

import java.util.ArrayList;

public abstract class Struttura {
    private int id;
    private String nome;
    private String descrizione;
    private String paese;
    private String citta;
    private String provincia;
    private int cap;
    private String indirizzo;
    private Host proprietario;
    private ArrayList<Recensione> listRecensioni;
    private ArrayList<TariffaStagionale> listatf;

    public Struttura(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo, Host proprietario) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.paese = paese;
        this.citta = citta;
        this.provincia = provincia;
        this.cap = cap;
        this.indirizzo = indirizzo;
        this.proprietario = proprietario;
        this.listRecensioni=new ArrayList<>();
        this.listatf = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPaese() {
        return paese;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void addRecensione(Recensione re){
        listRecensioni.add(re);
    }

    public boolean equalsCitta(String citta){
        if(this.citta.equals(citta)){
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Struttura:" +
                "\n|ID=" + id +
                "\n|Nome='" + nome + '\'' +
                "\n|Descrizione='" + descrizione + '\'' +
                "\n|Paese='" + paese + '\'' +
                "\n|Citta='" + citta + '\'' +
                "\n|Provincia='" + provincia + '\'' +
                "\n|Cap=" + cap +
                "\n|Indirizzo='" + indirizzo + '\'' +
                "\n|Proprietario=" + proprietario.getNome();
    }

    public ArrayList<Recensione> getListRecensioni() {
        return listRecensioni;
    }

    public ArrayList<Recensione> getRecComm(){
        ArrayList<Recensione> recensioni=new ArrayList<>();
        for(int i=0;i<listRecensioni.size();i++){
            Recensione re=listRecensioni.get(i).isRisp();
            if(re!=null)
                recensioni.add(re);
        }
        return recensioni;
    }

    public void inserisciCommentoHost(String commentoHost, int id){
        for(int i=0;i<listRecensioni.size();i++){
            if(listRecensioni.get(i).getId()==id){
                listRecensioni.get(i).setCommentoHost(commentoHost);
                listRecensioni.get(i).setStato("Concluso");
                break;
            }
        }
    }

    public void inserisciTariffa(String nome, int datainizio, int datafine, float fattoreMoltiplicativo){
         TariffaStagionale tf=new TariffaStagionale(nome,datainizio,datafine,fattoreMoltiplicativo);
         listatf.add(tf);
    }

    public ArrayList<TariffaStagionale> getListatf() {
        return listatf;
    }


}
