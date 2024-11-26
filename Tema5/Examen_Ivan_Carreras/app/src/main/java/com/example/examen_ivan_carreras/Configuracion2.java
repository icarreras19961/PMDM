package com.example.examen_ivan_carreras;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Configuracion2 extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuracion);
        final ListPreference backTheme = (ListPreference) findPreference("grafics");
        backTheme.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object valor) {
                        //No se como apuntar directamente a un archivo
                        if (valor.toString().equals("Claro")) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        }
                        return true;
                    }
                }
        );

        final EditTextPreference nombrePuntos = (EditTextPreference) findPreference("nombre");
        nombrePuntos.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object nombre) {
                //Aqui hay un intento de enviar el nombre pero como no funciona el score no he continuado
                MainActivity main = new MainActivity();
                main.setNombreScore(nombre.toString());
                return true;
            }
        });
    }
}