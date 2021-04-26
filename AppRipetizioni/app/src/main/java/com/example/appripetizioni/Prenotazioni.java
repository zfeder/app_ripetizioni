package com.example.appripetizioni;

public class Prenotazioni {

    private String idPrenotazione;
    private String idUtente;
    private String idDocente;
    private String idCorso;
    private String cognome;
    private String nomeP;
    private String stato;
    private String orario;
    private String giorno;


    public Prenotazioni(String idPrenotazione, String cognome, String nomeP, String orario, String giorno) {
        this.idPrenotazione = idPrenotazione;
        this.cognome = cognome;
        this.nomeP = nomeP;
        this.orario = orario;
        this.giorno = giorno;
    }

    public Prenotazioni(String cognome, String nomeP, String orario, String giorno) {
        this.cognome = cognome;
        this.nomeP = nomeP;
        this.orario = orario;
        this.giorno = giorno;
    }



    public String getIdDocente() {
        return idDocente;
    }

    public String getCognome() {
        return cognome;
    }

    public String getGiorno() {
        return giorno;
    }

    public String getIdCorso() {
        return idCorso;
    }

    public String getIdPrenotazione() {
        return idPrenotazione;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public String getNomeP() {
        return nomeP;
    }

    public String getOrario() {
        return orario;
    }

    public String getStato() {
        return stato;
    }


}