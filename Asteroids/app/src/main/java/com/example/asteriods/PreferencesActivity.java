package com.example.asteriods;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class PreferencesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);

        final EditTextPreference fragments = (EditTextPreference) findPreference("fragments");
        fragments.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int valor;
                try {
                    valor = Integer.parseInt((String) newValue);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Ha de ser un número", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (valor >= 0 && valor <= 9) {
                    fragments.setSummary("En quants trossos es divideix un asteroide (" + valor + ")");
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Màxim de fragments 9", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}