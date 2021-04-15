package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Calendario extends AppCompatActivity {

    private ListView lv;
    private List<String> lista = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("a");


        lv = findViewById(R.id.lv1);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, lista);
        lv.setAdapter(adapter);

        String details = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(a);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for (int i =0; i<jsonArray.length(); i++)  {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                details = String.valueOf(jsonObject.get("idPrenotazione"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lista.add(details);
        }

        adapter.notifyDataSetChanged();
    }

}