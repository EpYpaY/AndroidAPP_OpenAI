package com.example.projet_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class RhumHolder extends RecyclerView.ViewHolder {
    public TextView Titre;
    public TextView Note;
    public TextView Marque;
    public TextView Degres;
    public RhumHolder(@NonNull View itemView) {
        super(itemView);
        Titre = itemView.findViewById(R.id.titre);
        Note = itemView.findViewById(R.id.note);
        Marque = itemView.findViewById(R.id.marque);
        Degres = itemView.findViewById(R.id.degres);
    }
}
