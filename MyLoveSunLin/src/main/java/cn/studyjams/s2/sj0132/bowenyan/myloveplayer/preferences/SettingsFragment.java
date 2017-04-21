package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.R;

/**
 * Created by yanbowen on 4/21/2017.
 */

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Load settings XML
        int preferencesResId = R.xml.settings;
        addPreferencesFromResource(preferencesResId);
        super.onActivityCreated(savedInstanceState);
    }
}