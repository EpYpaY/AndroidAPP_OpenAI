package com.example.projet_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RhumAdapter extends ArrayAdapter<Rhum> {

    public RhumAdapter(@NonNull Context context, Rhum[] objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(R.layout.layout_liste, null);
        //personnalisation de la vue

        Rhum rhum = getItem(position);
        TextView nom = (TextView) row.findViewById(R.id.titre);
        nom.setText(rhum.getTitle());
        TextView detail = (TextView) row.findViewById(R.id.detail);
        detail.setText(rhum.getMarque());
        ImageView poster = row.findViewById(R.id.imageView2);


        return (row);
    }

}
