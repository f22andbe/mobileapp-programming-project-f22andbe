package com.example.project;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class EditPreferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new EditPreferencesFragment())
                .commit();
    }

}
