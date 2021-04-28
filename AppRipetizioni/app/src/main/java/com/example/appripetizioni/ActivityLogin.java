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
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                Log.e("Stato","messaggio di risposta :"+ s);//scrivvo sul log
                if (s.equals("Admin") || s.equals("Utente")) {
                    Intent intent = new Intent(getApplicationContext(), ActivityLogout.class);
                    intent.putExtra("Stato", "");
                    startActivity(intent);
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    TextView username = findViewById(R.id.editText1);
                    TextView password = findViewById(R.id.editText2);
                    String usernameString = username.getText().toString();
                    String passwordString = password.getText().toString();
                    Log.d(usernameString, passwordString);

                    String urll = "http://192.168.1.103:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;
                    //String urll = "http://192.168.1.138:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;
                    //String urll = "http://192.168.1.236:8080/Ripetizioni/ServletLogin?azione=login" + "&" + "utente=" + usernameString + "&" +  "password=" + passwordString;

                    CookieManager cookieManager = new CookieManager();
                    CookieHandler.setDefault(cookieManager);


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