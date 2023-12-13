package com.example.tripjournal_project

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng
import java.lang.IllegalStateException
import java.time.LocalDateTime

const val MIN_DIST = 10.0

class Journeypoints(val id: Long, val name: String, val start: LocalDateTime)
{
    val tags = mutableListOf<trippoints>()

    fun firstPosition(): LatLng {
        return LatLng(tags.first().location.latitude,tags.first().location.longitude)
    }
    fun toLatLng(): List<LatLng> {
        return tags.map { LatLng(it.location.latitude, it.location.longitude) }
    }

    fun addLocation(time: LocalDateTime, location: Location) {
        if (tags.isEmpty() || location.distanceTo(tags.last().location) >= MIN_DIST) {
            tags.add(trippoints(location))
        }
        else
        {
            throw IllegalStateException("error")
    }

}
}