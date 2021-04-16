package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityLogout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
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

                    //String urll = "http://192.168.1.103:8080/Ripetizioni/ServletLogin?azione=logout";
                    //String urll = "http://192.168.1.183:8080/Ripetizioni/ServletLogin?azione=logout";
                    String urll = "http://192.168.1.165:8080/Ripetizioni/ServletLogin?azione=logout";

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



}