package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PrenotazioniAttive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni_attive);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("a");
        Log.e("Stato","JSON IN PRENOTAZIONIATTIVEEEEE:"+ a);
        ArrayList<Prenotazioni> prenotazioni = null;
        prenotazioni = extractPrenotazioni(a);

        ListView prenotazionilv = (ListView) findViewById(R.id.lv1);

        prenotazioniAttiveAdapter adapter = new prenotazioniAttiveAdapter(this, prenotazioni);

        prenotazionilv.setAdapter(adapter);

    }

    public static ArrayList<Prenotazioni> extractPrenotazioni(String s) {

        ArrayList<Prenotazioni> prenota = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(s);
            JSONArray jsonArray = jsonRootObject.getJSONArray("prenotazioni");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String nome = jsonObject.getString("nomeP");
                String cognome = jsonObject.getString("cognome");
                String orario = jsonObject.getString("orario");
                String data = jsonObject.getString("giorno");
            /*    String idPrenotazione = jsonObject.getString("idPrenotazione"); */


                Prenotazioni prenotalv = new Prenotazioni(cognome, nome,  orario, data);

                prenota.add(prenotalv);

            }


        } catch (JSONException e) {
            Log.e("QueryUtils","Problem loading JSON");
        }

        return prenota;

    }


}
