    package com.Roomify;

    import scala.collection.SeqView;

    import java.io.Serial;
    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class Beb extends Struttura{

        private Map<String,Stanza> listaStanze;
        private Stanza stanzaCorrente;


        public Beb(int id, String nome, String descrizione, String paese, String citta, String provincia, int cap, String indirizzo, Host proprietario) {
            super(id, nome, descrizione, paese, citta, provincia, cap, indirizzo,proprietario);
            this.listaStanze = new HashMap<>();
        }

        public Stanza getStanzaCorrente() {
            return stanzaCorrente;
        }

        //Inserimento della stanza caso d'uso 1
        public Stanza inserisciStanza(int id,String nome,int nospiti, float prezzopernotte, int dimensione,String descrizione, ArrayList<Servizio> listaServizi,Beb beb){
            Stanza st=new Stanza(id,nome, nospiti, prezzopernotte, dimensione, descrizione, listaServizi,beb);
            listaStanze.put(nome, st);
            return st;
        }

        public Map<String, Stanza> getListaStanze() {
            return listaStanze;
        }

        public ArrayList<Stanza> isAvaible(LocalDate dataInizio, LocalDate dataFine, int nOspiti) {
            ArrayList<Stanza> listStanzeDisp = new ArrayList<>();
            Map<String, Stanza> stnBeb =  this.getListaStanze();
            for (Stanza stanza : stnBeb.values()) {
                Stanza sv = stanza.isAvailable(dataInizio, dataFine, nOspiti);
                if (sv != null) {
                    listStanzeDisp.add(sv);
                }
            }
            return listStanzeDisp;
        }

        public void aggiornaStruttura(String nome, String descrizione){
            if(nome!=null)
            setNome(nome);
            if(descrizione!=null)
            setDescrizione(descrizione);
        }

        public Stanza selStanza(String id){
            stanzaCorrente = listaStanze.get(id);
            return stanzaCorrente;
        }

        public Stanza   selStanza(int id){
            String nome="";
            for(Stanza stanza : listaStanze.values()){
                if(stanza.getId()==id){
                    nome = stanza.getNome();
                }
            }
            stanzaCorrente = listaStanze.get(nome);
            return stanzaCorrente;
        }

        public void modStanza(String nome, String descrizione, ArrayList<Servizio> listSer, float prezzopernotte){
            listaStanze.remove(stanzaCorrente.getNome());
            stanzaCorrente.setInfo(nome, descrizione, listSer, prezzopernotte);
            listaStanze.put(stanzaCorrente.getNome(), stanzaCorrente);
        }

        public Map<Integer,Prenotazione> getListaPrenStanze(){
            Map<Integer,Prenotazione> mapPrenotazione = new HashMap<>();
            for(Stanza stanza : listaStanze.values()){
                mapPrenotazione.putAll(stanza.getListaprenotazioni());
            }
            return mapPrenotazione;
        }

        public void addPrenotazione(Prenotazione pren){

                stanzaCorrente.addPrenotazione(pren);

        }

    }


