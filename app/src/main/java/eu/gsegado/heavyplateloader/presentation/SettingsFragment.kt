package eu.gsegado.heavyplateloader.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.DropDownPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import eu.gsegado.heavyplateloader.R
import eu.gsegado.heavyplateloader.utils.Constants.BAR_WEIGHT

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        sharedPref?.getString(BAR_WEIGHT, "20")?.let {
            val barweightPref = findPreference<DropDownPreference>(BAR_WEIGHT)
            when (it) {
                "20" -> barweightPref?.summary = getString(R.string.settings_male_barweight)
                "15" -> barweightPref?.summary = getString(R.string.settings_female_barweight)
            }
        }

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        key?.let {
            if (it == BAR_WEIGHT) {
                sharedPreferences?.getString(key, "20")?.let { barChoice ->
                    val barweightPref = findPreference<DropDownPreference>(it)
                    when (barChoice) {
                        "20" -> {
                            barweightPref?.summary = getString(R.string.settings_male_barweight)
                        }
                        "15" -> {
                            barweightPref?.summary = getString(R.string.settings_female_barweight)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }
}