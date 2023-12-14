package com.example.tripjournal_project

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import java.time.LocalDateTime
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val TAG_SERVICE = "MapService"
const val REQUEST_ID = 10
class LocationService(
    private val context: androidx.activity.ComponentActivity,
    private val client: FusedLocationProviderClient,
    private val geocoder: Geocoder
    //private val viewModel: SharedViewmodel
) {
    var locationOn: Boolean = false
    private var launcher: ActivityResultLauncher<String>

    init{
        launcher =context.registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            locationOn = it

            Log.v("CALLBACK", "GRANTED = $it")
        }
    }

    suspend fun getCurrentLocation(): Location {
        return suspendCoroutine { continuation ->
            try{
                client.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener { continuation.resume(it)
                    //viewModel.journeyPoints.value?.addLocation(LocalDateTime.now(), it)}.addOnFailureListener {
                        Log.v(TAG_SERVICE, "The location request failed")
                    }
            } catch (e: SecurityException)
            {

            }
        }
    }

    fun checkPermission(): Boolean
    {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun requestPermission()
    {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}