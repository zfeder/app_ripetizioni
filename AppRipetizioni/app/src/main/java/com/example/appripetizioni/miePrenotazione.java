package com.example.appripetizioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.appripetizioni.ActivityCalendario.extractPrenotazioni;

public class miePrenotazione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mie_prenotazione);
        Intent intent = getIntent();
        String a = intent.getExtras().getString("a");

        ArrayList<Prenotazioni> prenotazioni = null;
        prenotazioni = extractPrenotazioni(a);

        ListView prenotazionilv = (ListView) findViewById(R.id.lv1);

        miePrenotazioniAdapter adapter = new miePrenotazioniAdapter(this, prenotazioni);

        prenotazionilv.setAdapter(adapter);

    }



}