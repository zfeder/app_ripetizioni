package com.example.appripetizioni;

import android.util.Log;

import com.google.gson.Gson;

public class materie {
    String titoloCorso;

    public String getTitoloCorso() {
        return this.titoloCorso;
    }

    public void setTitoloCorso(String titolocorso) {
        this.titoloCorso = titolocorso;
    }


     Gson materiegson = new Gson();


}