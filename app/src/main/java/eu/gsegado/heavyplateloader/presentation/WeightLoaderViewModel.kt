package eu.gsegado.heavyplateloader.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import eu.gsegado.heavyplateloader.utils.Constants.BAR_WEIGHT
import java.util.*
import kotlin.math.floor
import kotlin.math.max

class WeightLoaderViewModel(application: Application) : AndroidViewModel(application) {
    private val increment: Int = 1
    private var weightBarbell: Int = 20
    private val maxWeight: Int = 300

    private var weight: Int = 0
    private val mapPlatesAvailable: EnumMap<PlateType, Boolean> by lazy {
        val map: EnumMap<PlateType, Boolean> = EnumMap(PlateType::class.java)
        map[PlateType.Plate25kg] = true
        map[PlateType.Plate20kg] = true
        map[PlateType.Plate15kg] = true
        map[PlateType.Plate10kg] = true
        map[PlateType.Plate5kg] = true
        map[PlateType.Plate2kg5] = true
        map[PlateType.Plate2kg] = true
        map[PlateType.Plate1kg5] = true
        map[PlateType.Plate1kg] = true
        map[PlateType.Plate0kg5] = true
        map
    }

    val weightFormatted = MutableLiveData<String>()
    val weightBarbellLivedata = MutableLiveData<Int>()
    val weightLiveData = MutableLiveData<Int>()
    val nbPlates = MutableLiveData<Int>()
    val plateListAvailableLivedata = MutableLiveData<List<Pair<PlateType, Boolean>>>()

    val listPlates = MutableLiveData<MutableList<PlateType>>(mutableListOf())

    fun init() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplication())
        sharedPref.getString(BAR_WEIGHT, "20")?.let {
            weightBarbell = it.toInt()
            weightBarbellLivedata.postValue(weightBarbell)
        }

        val list = mapPlatesAvailable.toList()
        list.sortedWith { o1, o2 -> o1.first.compareTo(o2.first) }
        plateListAvailableLivedata.postValue(list)
    }

    fun onUserChangedWeight(value: Int) {
        weight = value * increment
        //println("value = $value  /  weight = $weight" )

        weightProcess()
    }

    fun onUserPressedPlate(plate: PlateType) {
        mapPlatesAvailable[plate]?.let {
            mapPlatesAvailable[plate] = !it

            plateListAvailableLivedata.postValue(mapPlatesAvailable.toList())
        }

        weightProcess()
    }

    fun onUserIncrementWeight() {
        if (weight < maxWeight) {
            weight++
            weightLiveData.postValue(weight)
            weightProcess()
        }
    }

    fun onUserDecrementWeight() {
        if (weight > 0) {
            weight--
            weightLiveData.postValue(weight)
            weightProcess()
        }
    }

    fun setBarWeight(barChoice: String) {
        weightBarbell = barChoice.toInt()
        weightBarbellLivedata.postValue(weightBarbell)
        computePlates(weight.toDouble())
    }

    private fun weightProcess() {
        weightFormatted.postValue(weight.toString())
        computePlates(weight.toDouble())
    }

    private fun computePlates(totalWeight: Double) {
        val sideWeight: Double = max(0.0, (totalWeight - weightBarbell)/2.0)

        val pairListPlates: MutableList<Pair<PlateType, Int>> = mutableListOf()

        val listValuesPlatesAvaible: List<Double> = listOf(25.0, 20.0, 15.0, 10.0, 5.0, 2.5, 2.0, 1.5, 1.0, 0.5)

        var remainingWeight: Double = sideWeight

        if (mapPlatesAvailable[PlateType.Plate25kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate25kg, floor(remainingWeight / listValuesPlatesAvaible[0]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[0]
        }
        if (mapPlatesAvailable[PlateType.Plate20kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate20kg, (remainingWeight / listValuesPlatesAvaible[1]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[1]
        }
        if (mapPlatesAvailable[PlateType.Plate15kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate15kg, (remainingWeight / listValuesPlatesAvaible[2]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[2]
        }
        if (mapPlatesAvailable[PlateType.Plate10kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate10kg, (remainingWeight / listValuesPlatesAvaible[3]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[3]
        }
        if (mapPlatesAvailable[PlateType.Plate5kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate5kg, (remainingWeight / listValuesPlatesAvaible[4]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[4]
        }
        if (mapPlatesAvailable[PlateType.Plate2kg5] == true) {
            pairListPlates.add(Pair(PlateType.Plate2kg5, (remainingWeight / listValuesPlatesAvaible[5]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[5]
        }
        if (mapPlatesAvailable[PlateType.Plate2kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate2kg, (remainingWeight / listValuesPlatesAvaible[6]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[6]
        }
        if (mapPlatesAvailable[PlateType.Plate1kg5] == true) {
            pairListPlates.add(Pair(PlateType.Plate1kg5, (remainingWeight / listValuesPlatesAvaible[7]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[7]
        }
        if (mapPlatesAvailable[PlateType.Plate1kg] == true) {
            pairListPlates.add(Pair(PlateType.Plate1kg, (remainingWeight / listValuesPlatesAvaible[8]).toInt()))
            remainingWeight %= listValuesPlatesAvaible[8]
        }
        if (mapPlatesAvailable[PlateType.Plate0kg5] == true) {
            pairListPlates.add(Pair(PlateType.Plate0kg5, (remainingWeight / listValuesPlatesAvaible[9]).toInt()))
        }

        println("side weight : $sideWeight kg")

        val list: MutableList<PlateType> = mutableListOf()
        pairListPlates.forEach {
            print("$it +")
            for (i in 1..it.second) {
                list.add(it.first)
            }
        }
        println("--")
        list.add(PlateType.StopDisc)

        nbPlates.postValue(list.size)
        listPlates.postValue(list)
    }

}