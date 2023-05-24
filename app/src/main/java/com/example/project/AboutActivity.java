package com.example.project;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private TextView textView; //this is where we show our about text
    private ImageView imageView; //this is where we show our logo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        imageView = findViewById(R.id.logoimage);
        textView = findViewById(R.id.textView);

        // Set image for the about View
        imageView.setImageResource(R.drawable.logo);
        // Set the text for the TextView
        textView.setText(R.string.about_string);
    }
}
