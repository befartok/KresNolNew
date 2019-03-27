package com.example.admin.kresnol;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
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

    public static class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        public static ListPreference settingLevel;



        //ListPreference settingPlayers;
        PreferenceCategory settingPlayers;
        Preference createPlayers;
        Preference editPlayers;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            addPreferencesFromResource(R.xml.preferences);
            //находим преференсес в лайауте
            settingLevel = (ListPreference) this.findPreference("pref_level");
            //settingPlayers = (ListPreference)this.findPreference("pref_players");
            settingPlayers = (PreferenceCategory) this.findPreference("pref_players");
            createPlayers = (Preference) this.findPreference("pref_create_players");

            editPlayers = (Preference) this.findPreference("pref_edit_players");

            // устанавливаем слушатель
            settingLevel.setOnPreferenceChangeListener(this);
            settingPlayers.setOnPreferenceChangeListener(this);
            createPlayers.setOnPreferenceChangeListener(this);
            editPlayers.setOnPreferenceChangeListener(this);

            // пишем в summary текущее значение
            settingLevel.setSummary(settingLevel.getEntry());
            //settingPlayers.setSummary(settingPlayers.getEntry());

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            preference.setSummary((CharSequence) newValue);

            return true;
        }
    }
}



