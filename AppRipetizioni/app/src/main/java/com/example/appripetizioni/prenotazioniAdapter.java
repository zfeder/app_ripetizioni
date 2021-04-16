package com.example.appripetizioni;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class prenotazioniAdapter extends ArrayAdapter<prenotazioni> {

    public prenotazioniAdapter(Activity context, ArrayList<prenotazioni> Prenotazioni) {
        super(context, 0,Prenotazioni);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        prenotazioni Prenotazioni = getItem(position);

        TextView idDocente = (TextView) listItemView.findViewById(R.id.iDocente);
        idDocente.setText(Prenotazioni.getIdDocente());

        TextView Giorno = (TextView) listItemView.findViewById(R.id.Giorno);
        Giorno.setText(Prenotazioni.getGiorno());

        TextView Ora = (TextView) listItemView.findViewById(R.id.Ora);
        Ora.setText(Prenotazioni.getOrario());

        return listItemView;
    }
}
