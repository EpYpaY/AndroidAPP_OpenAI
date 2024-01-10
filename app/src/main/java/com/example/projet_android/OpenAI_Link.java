package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OpenAI_Link extends AppCompatActivity {
    private ImageButton imgb;
    private EditText prompt;
    private TextView answer;
    private ImageButton send;
    private String url = "https://api.openai.com/v1/chat/completions";
    private String apikey = "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";
    //private String apikey2 = "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";
    private String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ai_link);
        //TextInputLayout prompt = findViewById();

        imgb = findViewById(R.id.BackHome1);
        prompt = findViewById(R.id.prompt);
        answer = findViewById(R.id.answer);
        send = findViewById(R.id.send);

        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatGPT(v);
            }
        });

    }

    public void chatGPT(View view) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("model","gpt-3.5-turbo");
            jsonObject.put("temperature", 0.5);
            jsonObject.put("max_tokens",64);
            jsonObject.put("top_p",1);

            JSONArray jsonArrayMessage = new JSONArray();
            JSONObject jsonObjectMessage = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();

            jsonObject1.put("role", "system");
            jsonObject1.put("content", "You will be provided with a block of text, and your task is to extract a list of keywords from it.");

            jsonObjectMessage.put("role", "user");
            jsonObjectMessage.put("content", prompt.getText());

            jsonArrayMessage.put(jsonObject1);
            jsonArrayMessage.put(jsonObjectMessage);

            jsonObject.put("messages", jsonArrayMessage);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String stringText;
                try {
                    stringText = response.getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                prompt.setText("");

                output = output + stringText;

                answer.setText(null);

                System.out.println(output);

                answer.setText(output);

                output = "";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> mapHeader = new HashMap<>();

                mapHeader.put("Authorization", "Bearer " + apikey);
                mapHeader.put("Content-Type", "application/json");

                return mapHeader;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };

        int TimeoutPeriod = 60000; // durée de timeout définie à 60 secondes
        RetryPolicy retryPolicy = new DefaultRetryPolicy(TimeoutPeriod,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjectRequest.setRetryPolicy(retryPolicy);

        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
    }
}