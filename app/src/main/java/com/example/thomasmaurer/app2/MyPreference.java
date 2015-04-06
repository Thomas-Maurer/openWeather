package com.example.thomasmaurer.app2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Thomas on 19/02/2015.
 */
public class MyPreference extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.xml);
    }
}
