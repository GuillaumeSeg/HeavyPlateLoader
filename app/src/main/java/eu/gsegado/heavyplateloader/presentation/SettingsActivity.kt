package eu.gsegado.heavyplateloader.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.gsegado.heavyplateloader.R


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        supportActionBar?.title = getString(R.string.settings_title)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings_container, SettingsFragment())
                .commit()
    }
}
