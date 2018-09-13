package com.example.admin.kresnol;

/**
 * Created by admin on 12.09.18.
 */

import android.os.Bundle;
import android.preference.PreferenceFragment;

// TODO: 13.09.18 удалить, не используется
public class Fragment extends PreferenceFragment {

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }

    }
