package com.example.admin.kresnol;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.util.Log;

/**
 * Created by admin on 24.04.19.
 */

//класс фрагмента экрана настроек
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    final String LOG_TAG = "myLogs";

    public ListPreference settingLevel;

    PreferenceCategory settingPlayers;
    Preference createPlayers;
    Preference editPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.preferences);
        //находим преференсес в лайауте
        settingLevel = (ListPreference) this.findPreference("pref_level");
        settingPlayers = (PreferenceCategory) this.findPreference("pref_players");
        createPlayers = (Preference) this.findPreference("pref_create_players");

        editPlayers = (Preference) this.findPreference("pref_edit_players");

        // устанавливаем слушатель
        settingLevel.setOnPreferenceChangeListener(this);
        settingPlayers.setOnPreferenceChangeListener(this);
        createPlayers.setOnPreferenceChangeListener(this);

        createPlayers.setOnPreferenceClickListener(this);

        editPlayers.setOnPreferenceChangeListener(this);

        // пишем в summary текущее значение настройки уровня
        settingLevel.setSummary(settingLevel.getEntry());

    }

    //устанавливаем при изменении настройки уровня игры новое значение в Summary
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        preference.setSummary((CharSequence) newValue);

        return true;
    }

    //если был клик на пункте настройки создать игрока то вызываем активити создания тгрока
    @Override
    public boolean onPreferenceClick(Preference preference) {
        Log.d(LOG_TAG, "onPreferenceClick");

        makeActivityCreatePlayerActivity();
        return true;
    }

    //активити создания тгрока
    public void makeActivityCreatePlayerActivity() {

        Intent intent = new Intent(getActivity(), CreatePlayerActivity.class);
        startActivity(intent);

    }
}