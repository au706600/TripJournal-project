package com.example.tripjournal_project

import android.location.Location

// Creating an object because I didn't find a way to force pass-by-reference
// nor a reasonable way to pass arguments when navigating
data class activeJourneyID(var ID: Int)
data class Journey(val name: String, val location: Location, val spots: ArrayList<Spot>) {
    fun getCoordinates() : Pair<Double, Double> {
        return Pair(location.latitude, location.longitude)
    }
}

fun generateStaticData(): ArrayList<Journey> {
    val Journeys = ArrayList<Journey>()
    val AarhusLocation = android.location.Location("")
        AarhusLocation.latitude = 56.15674
        AarhusLocation.longitude = 10.21076
    for (i in 0..2) {
        Journeys.add( Journey(name = "Aarhus".plus(i.toString()),
            location = AarhusLocation,
            spots = ArrayList<Spot>()))
        for (j in 0..4) {
            val AarhusSpotLocation = android.location.Location("")
            AarhusSpotLocation.latitude = 56.15674 - 0.1 * j
            AarhusSpotLocation.longitude = 10.21076 - 0.1 * j
            Journeys[i].spots.add( Spot(name = "Location ".plus(((i*10+j).toString())),
                activity = "Things",
                location = AarhusSpotLocation))
        }
    }


    return Journeys
}


data class Spot(val name: String, val activity: String, val location: Location) {
    fun getCoordinates() : Pair<Double, Double> {
        return Pair(location.latitude, location.longitude)
    }
}