package com.example.tripjournal_project

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Map(activeJourneyID: activeJourneyID, Spots: ArrayList<tourney>, location: Location,back:() -> Unit) {
    //val aarhus = LatLng(56.154826, 10.212434)

    val journey = Spots[activeJourneyID.ID]
    val journeyLocation = journey.location
    val journeyCoordinates = LatLng(journeyLocation.latitude, journeyLocation.longitude)
    val spotsList = journey.spots

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { back() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                    Text(
                        text = "Journey Map",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.DarkGray,
                titleContentColor = Color(0xFFD0BCFF)
            )
        )
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(journeyCoordinates, 15f)
        }

        GoogleMap(modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState) {
            val pointslist = ArrayList<LatLng>()

            for (journey in spotsList) {
                pointslist.add(LatLng(journey.location.latitude, journey.location.longitude))
                Polyline(color = Color.Magenta, pattern = listOf(Dash(2f)),
                    points = pointslist
                )

                Marker(
                    state = MarkerState(position = LatLng(journey.location.latitude, journey.location.longitude)),
                    title = journey.name,
                    snippet = "Marker in: ${journey.name}"
                )
            }
        }
    }
}


