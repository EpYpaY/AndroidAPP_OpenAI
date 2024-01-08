package com.example.projet_android;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListeResultats extends AppCompatActivity {
    private RecyclerView list;
    private DatabaseReference mDatabase;
    private DatabaseReference rhumCloudEndPoint;
    private FirebaseRecyclerAdapter<Rhum, RhumHolder> mRhumFirebaseAdapter;
    private FirebaseRecyclerOptions<Rhum> options;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_resultats);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list = findViewById(R.id.recylcerView);
        list.setLayoutManager(linearLayoutManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        rhumCloudEndPoint = mDatabase.child("Rhum");
        options = new FirebaseRecyclerOptions.Builder<Rhum>().setQuery(rhumCloudEndPoint,Rhum.class).build();

        List<Rhum> mRhumEntries = new ArrayList<>();
        rhumCloudEndPoint.addValueEventListener(new ValueEventListener() {
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

        mRhumFirebaseAdapter = new FirebaseRecyclerAdapter<Rhum, RhumHolder>(options) {
            @NonNull
            @Override
            public RhumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_liste,parent,false);

                return new RhumHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull RhumHolder holder, int position, @NonNull Rhum model) {
                holder.Degres.setText(model.getDegres());
                holder.Titre.setText(model.getTitre());
                holder.Marque.setText(model.getMarque());
                holder.Note.setText(model.getNote());
            }
        };

        mRhumFirebaseAdapter.startListening();
        list.setAdapter(mRhumFirebaseAdapter);

        /*code pour ajout dans la BD
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("1"); //le .child permet d'ajouter dans l'objet défini (ici dans le 1)

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = input.getText().toString();

                rootDatabaseref.setValue(data);
            }
        })*/

        //final Rhum[] rhumList =



    }
}