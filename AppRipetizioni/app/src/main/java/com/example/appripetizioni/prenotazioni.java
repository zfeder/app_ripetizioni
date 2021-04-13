package com.example.appripetizioni;

public class prenotazioni {

    String idPrenotazione;
    String idUtente;
    String idDocente;
    String idCorso;
    String cognome;
    String nomeP;
    String stato;
    String orario;
    String giorno;

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

    public void setIdPrenotazione(String idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public void setIdDocente(String idDocente) {
        this.idDocente = idDocente;
    }

    public void setIdCorso(String idCorso) {
        this.idCorso = idCorso;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }
}
