package eu.gsegado.heavyplateloader.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import eu.gsegado.heavyplateloader.R
import eu.gsegado.heavyplateloader.databinding.ActivityWeightLoaderBinding
import eu.gsegado.heavyplateloader.utils.Constants.BAR_WEIGHT

class WeightLoaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightLoaderBinding
    private val vm: WeightLoaderViewModel by viewModels()

    private val plateAdapter = PlateAdapter()
    private val plateAvailableAdapter by lazy {
        PlateAvailableAdapter(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        vm.init()

        // Binding
        binding = ActivityWeightLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initView()

        // Increase / decrease buttons
        binding.increaseButton.setOnClickListener {
            vm.onUserIncrementWeight()
        }
        binding.decreaseButton.setOnClickListener {
            vm.onUserDecrementWeight()
        }

        // Settings button
        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            this.startActivity(intent)
        }

        // Bottom Sheet
        initBottomSheet()

        // Seekbar listener
        binding.seekbar.max = 300
        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                vm.onUserChangedWeight(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        // Observers
        initObservers()
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPref?.getString(BAR_WEIGHT, "20")?.let { barChoice ->
            vm.setBarWeight(barChoice)
        }
    }

    private fun initView() {
        binding.weightEdittext.setText("0")
        binding.seekbar.progress = 0
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.listPlates.layoutManager = layoutManager
        binding.listPlates.adapter = plateAdapter
    }

    private fun initBottomSheet() {
        binding.bottomSheet.listAvailablePlates.adapter = plateAvailableAdapter
    }

    private fun initObservers() {
        vm.weightFormatted.observe(this, {
            binding.weightEdittext.setText(it)
        })
        vm.nbPlates.observe(this, {
            plateAdapter.setNumberOfPlates(it)
        })
        vm.listPlates.observe(this, {
            plateAdapter.setItems(it)
        })
        vm.weightLiveData.observe(this, {
            binding.seekbar.progress = it
        })

        vm.plateListAvailableLivedata.observe(this, {
            plateAvailableAdapter.setItems(it)
        })

        vm.weightBarbellLivedata.observe(this, {
            binding.barWeightLabel.text = String.format(getString(R.string.barbell_label), it)
        })
    }

}