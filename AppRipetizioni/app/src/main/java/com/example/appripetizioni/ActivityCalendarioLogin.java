package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.internal.$Gson$Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityCalendarioLogin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_login);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("a");
        String nomeMateria = intent.getExtras().getString("nomeMateria");

        ArrayList<Prenotazioni> prenotazioni = null;
        prenotazioni = extractPrenotazioni(a);

        TextView materiaTv = (TextView) findViewById(R.id.materia);
        materiaTv.setText(nomeMateria);


        ListView prenotazionilv = (ListView) findViewById(R.id.lv1);

        prenotazioniLoginAdapter adapter = new prenotazioniLoginAdapter(this, prenotazioni);

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
                String idPrenotazione = jsonObject.getString("idPrenotazione");


                Prenotazioni prenotalv = new Prenotazioni(idPrenotazione, cognome, nome,  orario, data);

                prenota.add(prenotalv);

            }


        } catch (JSONException e) {
            Log.e("QueryUtils","Problem loading JSON");
        }

        return prenota;

    }

    public void prenota (View v) {
        Button b = (Button)v;
        String a = b.getTag().toString();
        Log.e("Tag bottone", "" + a);//scrivvo sul log
        prenotaDB(a);
    }

    public void prenotaDB(String a){

        class ServletCallMaterie extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(s);
                Log.e("Stato","messaggio di risposta :"+ s);//scrivvo sul log


            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    //String urll = "http://192.168.1.103:8080/Ripetizioni/ServletShow?azione=Prenota" + "&" + "idPrenotazione=" + a;
                    //String urll = "http://192.168.1.138:8080/Ripetizioni/ServletShow?azione=Prenota" + "&" + "idPrenotazione=" + a;
                    String urll = "http://192.168.1.236:8080/Ripetizioni/ServletShow?azione=Prenota" + "&" + "idPrenotazione=" + a;

                    //connessione
                    //specifico i dati che voglio mandare direttamente nella chiamata
                    URL url = new URL(urll);


                    HttpURLConnection con = (HttpURLConnection) url.openConnection();//apro la connessione

                    StringBuilder js = new StringBuilder();
                    BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String listJson;
                    while((listJson = buff.readLine()) != null){
                        js.append(listJson + "\n");
                    }
                    buff.close();
                    //con.setDoInput(false);
                    con.disconnect();
                    return js.toString().trim();
                } catch (Exception a) {
                    System.out.println("Errore cercando di prendere le materie dalla servlet --------------> " + a);
                    return null;
                }
            }
        }
        ServletCallMaterie servletCallMaterie = new ServletCallMaterie();
        servletCallMaterie.execute();
    }





}