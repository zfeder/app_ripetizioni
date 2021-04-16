package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.internal.$Gson$Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Calendario extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("a");



        ArrayList<prenotazioni> Prenotazioni = new ArrayList<>();

        try {
            JSONObject jsonRootObject = new JSONObject(a);
            JSONArray jsonArray = jsonRootObject.getJSONArray("prenotazionis");

            for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String idDocente = jsonObject.optString("idDocente");
                String Giorno = jsonObject.optString("Giorno");
                String Orario = jsonObject.optString("Orario");

                prenotazioni Prenota = new prenotazioni(null, null, idDocente, null, null, null, null, Orario, Giorno);

                Prenotazioni.add(Prenota);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView prenotazioniLV = (ListView) findViewById(R.id.lv1);

        prenotazioniAdapter Adapter = new prenotazioniAdapter(this, Prenotazioni);

        prenotazioniLV.setAdapter(Adapter);

     /*   String details = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(a);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i =0; i<jsonArray.length(); i++)  {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                details = String.valueOf(jsonObject.get("idPrenotazione"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lista.add(details);
        }

        adapter.notifyDataSetChanged(); */
    }

}