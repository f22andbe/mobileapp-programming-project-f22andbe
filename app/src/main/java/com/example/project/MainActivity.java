package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;


@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=f22andbe";

    private ArrayList<AminoAcid> aminoArrayList;
    private Gson gson;
    RecyclerView recyclerView;
    Type type;
    AminoAcidViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aminoArrayList = new ArrayList<>();

        /* Read in recyclerview */
        recyclerView = findViewById(R.id.recyclerView);

        /* Set up adapter */
        mAdapter = new AminoAcidViewAdapter(this, aminoArrayList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* setup JSON parser */
        gson = new Gson();
        type = new TypeToken<ArrayList<AminoAcid>>(){}.getType();

    }





}
