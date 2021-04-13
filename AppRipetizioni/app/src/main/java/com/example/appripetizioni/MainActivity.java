package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> lista = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Materie(null);


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        spinner = findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void login1(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    public void getJson(String a) throws JSONException {
        JSONArray jsonArray = new JSONArray(a);
        String data = null;


        for (int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            data = String.valueOf(jsonObject.get("titoloCorso"));
            lista.add(data);
        }

        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);


    }

    public void getJsonCalendario(String a) throws JSONException {


        List<prenotazioni> testList = new ArrayList<>();

        Gson gson = new Gson();

        String json = gson.toJson(testList);

        Type type = new TypeToken<ArrayList<prenotazioni>>(){}.getType();

        ArrayList<prenotazioni> array = gson.fromJson(json, type);


        /* Type listType = new TypeToken<ArrayList<prenotazioni>>() {}.getType();
        Gson gson = new Gson();
        String json = gson.toJson(a);
        ArrayList<prenotazioni> obj = gson.fromJson(json, listType);
        Log.d("JSONSAMPLE",""+obj);
        prenotazioni el1 = obj.get(1);
        Log.d("JSONSAMPLE",el1.getIdPrenotazione()+" "+ el1.getIdDocente()); */

        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);


    }

    public void getSelectedMateria (View v) {
        String s = (String) spinner.getSelectedItem();
        System.out.println(s);
        Calendario(s);


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

                    //String urll = "http://192.168.1.103:8080/Ripetizioni/ServletJSON?azione=getMateria";
                    String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getMateria";


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
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletJSON?azione=getCalendario"+ "&" + "value=" + s;
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getCalendario";


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

}