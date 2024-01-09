package com.example.projet_android;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Add_element extends AppCompatActivity {

    private TextView newTitre;
    private TextView newMarque;
    private TextView newDegres;
    private TextView newNote;
    private Button addNew;
    private DatabaseReference rootDatabaseRef;
    private DatabaseReference childRhum;
    private List<Rhum> mRhumEntries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);
        newTitre = findViewById(R.id.Titre);
        newDegres = findViewById(R.id.new_degres);
        newNote = findViewById(R.id.new_note);
        newMarque = findViewById(R.id.new_marque);
        addNew = findViewById(R.id.add_new);
        rootDatabaseRef = FirebaseDatabase.getInstance().getReference();
        childRhum = rootDatabaseRef.child("Rhum"); //le .child permet d'ajouter dans l'objet défini (ici dans le 1)
        childRhum.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot rhumSnapshot : snapshot.getChildren()) {
                    Rhum rhum = rhumSnapshot.getValue(Rhum.class);
                    mRhumEntries.add(rhum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(LOG_TAG, error.getMessage());
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ID = mRhumEntries.size() + 1;
                String Titre = newTitre.getText().toString();
                String Marque = newMarque.getText().toString();
                String Degres = newDegres.getText().toString();
                String Note = newNote.getText().toString();

                String key = rootDatabaseRef.push().getKey();
                rootDatabaseRef.child("Rhum").child(key).child("Degres").setValue(Degres);
                rootDatabaseRef.child("Rhum").child(key).child("ID").setValue(ID);
                rootDatabaseRef.child("Rhum").child(key).child("Marque").setValue(Marque);
                rootDatabaseRef.child("Rhum").child(key).child("Note").setValue(Note);
                rootDatabaseRef.child("Rhum").child(key).child("Titre").setValue(Titre);

                finish();
            }
        });

    }
}