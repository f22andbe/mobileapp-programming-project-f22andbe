package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import java.util.function.Predicate;
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
    SharedPreferences settings;


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

        /* initialize settings handling */
        settings =  getSharedPreferences("com.example.project.user_preferences", MODE_PRIVATE); // get object of type  SharedPreferences


        /* setup JSON parser */
        gson = new Gson();
        type = new TypeToken<ArrayList<AminoAcid>>(){}.getType();

    }


    /* This is a private helper function to filter out all the amino acids from aminoArrayList
     * that belong to groups that have been chosen by the user to be filtered out in the settings
     * If the CheckBox for the preference is set to true we define a predicate that evaluates to
     * true if the category defined in auxdata (of type Wikidata) contains the string sent to the
     * predicate. We then use the method removeIf(predicate) to delete all amino acids in
     * aminoArrayList that is of the class.
     */
    private void filterData(){

        if(settings.getBoolean("pref_amide", false)){
            Predicate<AminoAcid> predicate = acid -> acid.getAuxdata().getCategory().contains("Amide");
            aminoArrayList.removeIf(predicate);
        }
        if(settings.getBoolean("pref_anion", false)){
            Predicate<AminoAcid> predicate = acid -> acid.getAuxdata().getCategory().contains("Anion");
            aminoArrayList.removeIf(predicate);
        }
        if(settings.getBoolean("pref_fixedcation", false)){
            Predicate<AminoAcid> predicate = acid -> acid.getAuxdata().getCategory().contains("Fixed Cation");
            aminoArrayList.removeIf(predicate);
        }
        if(settings.getBoolean("pref_thiol", false)){
            Predicate<AminoAcid> predicate = acid -> acid.getAuxdata().getCategory().contains("Thiol");
            aminoArrayList.removeIf(predicate);
        }

    }

    /* parse JSON data and update view */
    @Override
    public void onPostExecute(String json) {
        /* parse json into AminoAcid objects */
        //aminoArrayList.clear();
        Log.d("onPostExecute", "json-string = " + json);

        aminoArrayList.addAll(gson.fromJson(json, type));
        //Log.d("onPostExecute before fileterData", "aminoArrayList = " + aminoArrayList.toString());
        filterData();
        //Log.d("onPostExecute after fileterData", "aminoArrayList = " + aminoArrayList.toString());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /* start JsonTask and fetch data */
        if (id == R.id.action_get_data) {

            /* get JSON data from webservice */
            new JsonTask(this).execute(JSON_URL);
            return true;
        }

        /* clear data and RecyclerView */
        if (id == R.id.action_clear_data) {
            Log.d("onOptionItemSelected","clear data");
            /* clear data */
            aminoArrayList.clear();
            mAdapter.notifyDataSetChanged();
            return true;
        }
        /* start about activity */
        if(id == R.id.about_screen) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        if(id == R.id.preference_screen) {
            startActivity(new Intent(this, EditPreferenceActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}

