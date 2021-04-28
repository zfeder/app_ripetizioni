package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
                String idPrenotazione = jsonObject.getString("idPrenotazione");
                String idCorso = jsonObject.getString("idCorso");


                Prenotazioni prenotalv = new Prenotazioni(idPrenotazione, cognome, nome,  orario, data, idCorso);

                prenota.add(prenotalv);

            }


        } catch (JSONException e) {
            Log.e("QueryUtils","Problem loading JSON");
        }

        return prenota;

    }

    public void svolta (View v) {
        Button b = (Button)v;
        String a = b.getTag().toString();
        Log.e("Tag bottone", "" + a);//scrivvo sul log
        svoltaDB(a);
        Intent intent = new Intent(this, ActivityLogout.class);
        intent.putExtra("Stato", "Svolta");
        startActivity(intent);
    }

    public void svoltaDB(String a){

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

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletShow?azione=Svolta" + "&" + "idPrenotazione=" + a;
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletShow?azione=Svolta" + "&" + "idPrenotazione=" + a;

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

    public void disdici (View v) {
        Button b = (Button)v;
        String a = b.getTag().toString();
        Log.e("Tag bottone", "" + a);//scrivvo sul log
        disdiciDB(a);
        Intent intent = new Intent(this, ActivityLogout.class);
        intent.putExtra("Stato", "Disdici");
        startActivity(intent);
    }

    public void disdiciDB(String a){

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

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletShow?azione=Disdici" + "&" + "idPrenotazione=" + a;
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletShow?azione=Disdici" + "&" + "idPrenotazione=" + a;

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
