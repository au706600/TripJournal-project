package com.example.tripjournal_project

import android.content.SharedPreferences
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Creating an object because I didn't find a way to force pass-by-reference
// nor a reasonable way to pass arguments when navigating
data class activeJourneyID(var ID: Int)

data class Journey(val name: String, val location: Location, val spots: ArrayList<Spot>) {
    fun getCoordinates() : Pair<Double, Double> {
        return Pair(location.latitude, location.longitude)
    }
}

data class Spot(val name: String, val activity: String, val location: Location) {
    fun getCoordinates() : Pair<Double, Double> {
        return Pair(location.latitude, location.longitude)
    }
}

class DataManageViewModel(journeysList: ArrayList<Journey>) : ViewModel() {
    fun saveDataLocally() {
        viewModelScope.launch(Dispatchers.IO) {
            // Serialize here
        }
    }

    fun loadLocalData() : ArrayList<Journey> {
        // Load from memory here
        return ArrayList()
    }
}



fun generateTestingData(): ArrayList<Journey> {
    val Journeys = ArrayList<Journey>()
    val AarhusLocation = Location("")
    AarhusLocation.latitude = 56.15674
    AarhusLocation.longitude = 10.21076
    for (i in 0..2) {
        Journeys.add( Journey(
            name = "Aarhus".plus(i.toString()),
            location = AarhusLocation,
            spots = ArrayList()))
        for (j in 0..4) {
            val AarhusSpotLocation = Location("")
            AarhusSpotLocation.latitude = 56.15674 - 0.1 * j
            AarhusSpotLocation.longitude = 10.21076 - 0.1 * j
            Journeys[i].spots.add( Spot(
                name = "Location ".plus(((i*10+j).toString())),
                activity = "Things",
                location = AarhusSpotLocation))
        }
    }
    return Journeys
}