package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login1(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    public void Materie(View view){

        class ServletCallMaterie extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(s);
                Log.e("Stato","messaggio di risposta :"+ s);//scrivvo sul logbbbb






                /*Gson materiegson = new Gson();
                materie materiee = materiegson.fromJson(s, materie.class);

                String jsonOutput = "[{\"titoloCorso\":,}]";
                materie[] posts = materiegson.fromJson(jsonOutput, materie[].class);
                Log.v("SteveMoretz", String.valueOf(posts.length)); */


            /*
                JSONArray cast = jsonResponse.getJSONArray("jsonarrayname");
                for (int i=0; i<cast.length(); i++) {
                    JSONObject actor = cast.getJSONObject(i);
                    String name = actor.getString("arrayelementname");
                    allNames.add(name);
                }

                Spinner spinner1 = (Spinner) findViewById(R.id.spinner);

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_item,list);

                dataAdapter.setDropDownViewResource
                        (android.R.layout.simple_spinner_dropdown_item);

                spinner1.setAdapter(dataAdapter);
             */
            }


            @Override
            protected String doInBackground(Void... voids) {
                try {

                    String urll = "http://192.168.1.183:8080/Ripetizioni/ServletJSON?azione=getMateria";
                    //String urll = "http://192.168.56.1:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;

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