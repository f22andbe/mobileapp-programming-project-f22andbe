package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class EditPreferencesFragment extends PreferenceFragment { // implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName("com.example.project.user_preferences");
        // Load the preferences from the XML resource
        addPreferencesFromResource(R.xml.preferences);
    }


}
