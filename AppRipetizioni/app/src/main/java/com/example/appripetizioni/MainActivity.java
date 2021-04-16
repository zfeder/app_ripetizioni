package com.example.appripetizioni;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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
    //private Spinner spinner;
    private ListView listView1;
    private List<String> details = new ArrayList();
    private ArrayAdapter<String> adapter2;

    AutoCompleteTextView autoCompleteTextView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Materie(null);

        //String []option = {"Matematica" , "Italiano" , "Programmazione"};
        autoCompleteTextView = findViewById(R.id.autoCompletText);
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.option_item, lista);
        //autoCompleteTextView.setText(adapter.getItem(0).toString(), false);

        autoCompleteTextView.setAdapter(adapter);





        //adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        //spinner = findViewById(R.id.spinner);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




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

        //adapter.notifyDataSetChanged();
        //autoCompleteTextView.setAdapter(adapter);


    }

    public void getJsonCalendario(String a) throws JSONException, IOException {
        CallCalendario(a);

     /*   ObjectMapper mapper = new ObjectMapper();

        List<prenotazioni> details = mapper.readValue(a, new
                TypeReference<List<prenotazioni>>() {      });

        adapter2.notifyDataSetChanged();  */

    }


    public void getSelectedMateria (View v) {
        AutoCompleteTextView source = (AutoCompleteTextView) findViewById(R.id.autoCompletText);
        String s = source.getText().toString();
        System.out.println(s);
        Calendario(s);

    }

    public void CallCalendario(String a) {
        Intent intent = new Intent(this, Calendario.class);
        intent.putExtra("a", a);
        startActivity(intent);
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
                    String urll = "http://192.168.1.165:8080/Ripetizioni/ServletJSON?azione=getMateria";


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
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {

                    //String urll = "http://192.168.1.103:8080/Ripetizioni/ServletJSON?azione=getCalendario"+ "&" + "value=" + s;
                    String urll = "http://192.168.1.165:8080/Ripetizioni/ServletJSON?azione=getCalendario" + "&" + "value=" + s;


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