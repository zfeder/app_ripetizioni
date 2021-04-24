package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogout extends AppCompatActivity {

    private List<String> lista = new ArrayList<>();
    private AutoCompleteTextView autoCompleteTextView;
    private String nomeMateria;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Materie(null);

        autoCompleteTextView = findViewById(R.id.autoCompletText);
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.option_item, lista);
        autoCompleteTextView.setAdapter(adapter);

    }

  

    public void logout(View view){

        class ServletCallLogout extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(s);
                Log.e("Stato","messaggio di risposta :"+ s);//scrivvo sul log
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletLogin?azione=logout";
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletLogin?azione=logout";
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletLogin?azione=logout";

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
        ServletCallLogout servletCallLogout = new ServletCallLogout();
        servletCallLogout.execute();
    }

    public void Calendario(String s){

        class ServletCallCalendario extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String prova) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(prova);
                Log.e("Stato","messaggio di risposta :"+ prova);//scrivvo sul log

                ArrayList<String> list = new ArrayList<String>();
                try {
                    getJsonCalendario(prova);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletJSON?azione=getCalendario2"+ "&" + "value=" + s;
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getCalendario" + "&" + "value=" + s;
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletJSON?azione=getCalendario2"+ "&" + "value=" + s;



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
        ServletCallCalendario servletCallCalendario = new ServletCallCalendario();
        servletCallCalendario.execute();
    }

    public void getSelectedMateria (View v) {
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompletText);
        nomeMateria = source.getText().toString();
        System.out.println(nomeMateria);
        Calendario(nomeMateria);

    }

    public void CallCalendario(String a) {
        Intent intent = new Intent(this, ActivityCalendario.class);
        intent.putExtra("a", a);
        intent.putExtra("nomeMateria", nomeMateria);
        startActivity(intent);
    }

    public void getJsonCalendario(String a) throws JSONException, IOException {
        CallCalendario(a);
    }

    public void Materie(View view){

        class ServletCallMaterie extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String prova) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(prova);
                Log.e("Stato","messaggio di risposta :"+ prova);//scrivvo sul log

                ArrayList<String> list = new ArrayList<String>();
                try {
                    getJson(prova);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletJSON?azione=getMateria";
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getMateria";
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletJSON?azione=getMateria";



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

    public void getJson(String a) throws JSONException {
        JSONArray jsonArray = new JSONArray(a);
        String data = null;


        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            data = String.valueOf(jsonObject.get("titoloCorso"));
            lista.add(data);
        }

        //adapter.notifyDataSetChanged();
        //autoCompleteTextView.setAdapter(adapter);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.popup_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.one:
                        getMiePrenotazioni(v);
                        return true;
                    case R.id.two:
                        storicoPrenotazioni(v);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    /* public void prenotazioniAttive(View view) {
        Intent intent = new Intent(this, PrenotazioniAttive.class);
        startActivity(intent);
    } */

    public void storicoPrenotazioni(View view) {
        Intent intent2 = new Intent(this, StoricoPrenotazioni.class);
        startActivity(intent2);
    }

    public void miePrenotazioni() {

        class ServletCallmiePrenotazioni extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String prova) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(prova);
                Log.e("Stato","messaggio di risposta :"+ prova);//scrivvo sul log

                ArrayList<String> list = new ArrayList<String>();
                try {
                    getJsonMiePrenotazioni(prova);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletShow?azione=miePrenotazioni";
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getCalendario" + "&" + "value=" + s;
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletJSON?azione=getCalendario2"+ "&" + "value=" + s;



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
        ServletCallmiePrenotazioni servletCallmiePrenotazioni = new ServletCallmiePrenotazioni();
        servletCallmiePrenotazioni.execute();


    }

    public void getJsonMiePrenotazioni(String a) throws JSONException, IOException {
        CallMiePrenotazioni(a);
    }

    public void CallMiePrenotazioni(String a) {
        Intent intent = new Intent(this, PrenotazioniAttive.class);
        intent.putExtra("a", a);
        startActivity(intent);
    }

    public void getMiePrenotazioni (View v) {
        miePrenotazioni();
    }


}