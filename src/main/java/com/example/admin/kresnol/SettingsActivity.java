package com.example.admin.kresnol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 12.09.18.
 */

public class SettingsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction().add(R.id.prefs_content, new SettingsFragment()).commit();

    }


    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

        public static ListPreference settingLevel;
        ListPreference settingPlayers;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);
            settingLevel=(ListPreference)this.findPreference("pref_level");
            settingPlayers = (ListPreference)this.findPreference("pref_players");

            // устанавливаем слушатель
            settingLevel.setOnPreferenceChangeListener(this);
            settingPlayers.setOnPreferenceChangeListener(this);

            // пишем в summary текущее значение
            settingLevel.setSummary(settingLevel.getEntry());
            settingPlayers.setSummary(settingPlayers.getEntry());

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue)
        {
            preference.setSummary((CharSequence)newValue);
            return true;
        }



    }



    }



