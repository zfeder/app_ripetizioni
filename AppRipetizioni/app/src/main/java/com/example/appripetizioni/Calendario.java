package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

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
        String nomeMateria = intent.getExtras().getString("nomeMateria");

        ArrayList<Prenotazioni> prenotazioni = null;
        prenotazioni = extractPrenotazioni(a);

        TextView materiaTv = (TextView) findViewById(R.id.materia);
        materiaTv.setText(nomeMateria);


        ListView prenotazionilv = (ListView) findViewById(R.id.lv1);

        prenotazioniAdapter adapter = new prenotazioniAdapter(this, prenotazioni);

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
                String corso = jsonObject.getString("idCorso");


                Prenotazioni prenotalv = new Prenotazioni(corso, cognome, nome,  orario, data);

                prenota.add(prenotalv);

            }


        } catch (JSONException e) {
            Log.e("QueryUtils","Problem loading JSON");
        }

        return prenota;

    }

   /* public void prenota (View v) {
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompletText);
        String s = source.getText().toString();
        System.out.println(s);
        Calendario(s);

    } */



}