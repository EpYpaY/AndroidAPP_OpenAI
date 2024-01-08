package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class OpenAI_Link extends AppCompatActivity {
    private ImageButton imgb;
    private EditText prompt;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ai_link);
        //TextInputLayout prompt = findViewById();

        imgb = findViewById(R.id.BackHome1);
        prompt = findViewById(R.id.prompt);
        answer = findViewById(R.id.answer);

        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}