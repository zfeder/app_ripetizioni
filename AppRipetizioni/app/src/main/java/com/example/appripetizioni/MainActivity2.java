package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void login(View view){

        class ServletCallMaterie extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {//dopo aver eseguito do in background avvio onPostExecute
                super.onPostExecute(s);
                Log.e("materie","messaggio di risposta :"+ s);//scrivvo sul log
                String s1 = s.substring(1,s.length()-1);
                String[] separazioni = s1.split(",");
                for(String a : separazioni){
                    System.out.println(a);
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    //connessione
                    //specifico i dati che voglio mandare direttamente nella chiamata
                    URL url = new URL("http://192.168.1.105:8080/Ripetizioni/ServletLogin?azione=login&utente=123&password=1234");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();//apro la connessione

                    //lettura
                    BufferedReader buff = new BufferedReader(new InputStreamReader(con.getInputStream()));//bufferreader creato con un imputStreamReader che converte in byte
                    StringBuilder js = new StringBuilder();
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