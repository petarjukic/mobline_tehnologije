package com.example.vjezba3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepoList repoList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rvInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doRequest();
    }

    public void doRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/search/repositories?q=stars:>100000";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                repoList = gson.fromJson(response, RepoList.class);
                adapter = new MyAdapter(repoList.items);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        queue.add(stringRequest);
    }
}