package com.example.tripjournal_project

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun LocationWidget(service: LocationService) {
    val location = remember {
        mutableStateOf<Location?>(null)
    }
    LaunchedEffect(key1 = Unit) {
        location.value = service.getCurrentLocation(Location(""))
    }

    Column {
        Text("Location: ${location.value?.latitude} ${location.value?.longitude} ")

    }
}