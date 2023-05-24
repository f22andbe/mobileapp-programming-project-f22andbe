package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DetailActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
    Context context;
    TextView aminoNameView;
    TextView aminoThreeLetterSymbolView;
    TextView aminoOneLetterSymbolView;
    TextView aminoCategoryView;
    TextView aminoWikiUrlView;
    TextView aminoWikiTextView;
    ImageView aminoImageView; // we now also have an ImageView in the ViewHolder



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        context = this;

        aminoNameView = findViewById(R.id.aminoname);
        aminoThreeLetterSymbolView = findViewById(R.id.aminothreeletter);
        aminoOneLetterSymbolView = findViewById(R.id.aminooneletter);
        aminoWikiUrlView = findViewById(R.id.aminowikiurl);
        aminoWikiTextView = findViewById(R.id.aminowikitext);
        aminoImageView = findViewById(R.id.aminoimage); //here we get a reference to it

        String wikiPage;
        String apiUrl = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=1&explaintext=1&titles=";
        //String apiUrl = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=1&titles="; // this is the string that is used to call the MediaWikiAction api


        Intent intent = getIntent();
        if (intent != null) {
            setViews(intent);
            wikiPage = apiUrl + intent.getStringExtra("aminoname");
            Log.d("before JsonTask/wikiPageTitle", wikiPage);
            new JsonTask(this).execute(wikiPage);
            //new JsonTask(this).execute(apiUrl);
        }

    }

    public void setViews(Intent intent) {
        aminoNameView.setText("Name: " + intent.getStringExtra("aminoname"));
        aminoThreeLetterSymbolView.setText("Three letter symbol: " + intent.getStringExtra("aminothreelettersymbol"));
        aminoOneLetterSymbolView.setText("One letter symbol: " + intent.getStringExtra("aminoonelettersymbol") );
        aminoWikiUrlView.setVisibility(View.GONE);
        //aminoWikiUrlView.setText("Wiki: " + intent.getStringExtra("aminowikiurl"));
        loadImage(intent.getStringExtra("aminoimgurl"), aminoImageView);
    }

    private void loadImage(String url, ImageView imageView){
        // Set RequestOptions for Glide
        RequestOptions requestOptions = new RequestOptions().error(R.drawable.error_image) //if image can't be loaded from url show error_image instead
                .fitCenter() //scale image down to size of imageview
                .transform(new RoundedCorners(16)) //give image rounded corners
                .diskCacheStrategy(DiskCacheStrategy.ALL); // Cache both original and resized image
        // Load the image with Glide
        Glide.with(context)                //context of the DetailActivity
                .load(url)
                .apply(requestOptions) //apply specified options to this request
                .into(imageView);      //this is the imageView the image should be shown in
    }


    /* Use mediawiki action api to download part of the wiki page at
     * the url we got via intent, use org.json to parse the json response
     * from action api. We use org.json instead of gson because it's better
     * at extracting string data from json instead of marshalling it into  an object
     */
    private static String extractSummary(String jsonString) {
        String summary = "";
        String key="";
        try {

            // Parse the JSON response
            /*summary = new JSONObject(jsonString)
                          .getJSONObject("query")
                          .getJSONObject("pages")
                          .getJSONObject("63539").getString("extract"); */

            JSONObject response = new JSONObject(jsonString)
                    .getJSONObject("query")
                    .getJSONObject("pages");

            for (Iterator <String> k = response.keys(); k.hasNext();) {
                key = k.next();
                Log.d("keys in pages", "Object Name: " + key);
            }
            summary = response.getJSONObject(key).getString("extract");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return summary;
    }

    @Override
    public void onPostExecute(String response) {
        Log.d("onPostExecute", "jsonstring: " + response);
        String text = extractSummary(response);
        aminoWikiTextView.setText(text);
    }

}



