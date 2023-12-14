package com.example.tripjournal_project

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSpot(
    navController: NavController,
    journeyViewModel: JourneyViewModel,
    JourneysList: ArrayList<tourney>,
    activeJourneyID: activeJourneyID,
    locationService: LocationService,
    locationParam: Location,
    back:()-> Unit)
{
    val scope = rememberCoroutineScope()
    var nameText by remember{ mutableStateOf(TextFieldValue("")) }
    var activityText by remember{ mutableStateOf(TextFieldValue("")) }
    var returnLocation = Location("")
    // var currentLocationJob = runBlocking { launch { locationService.getCurrentLocation(currentLocation) } }

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
                        text = "Journeys",
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

        Button(onClick = {
            navController.navigate("SpotMapLocationFinder")
        }
        )
        {
            Text("Select Location")
        }

        Row()
        {
            TextField(value = nameText, onValueChange = { newValue -> nameText = newValue },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), placeholder = { Text("Add spot name") })
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        Row()
        {
            TextField(value = activityText,
                onValueChange = { newValue -> activityText = newValue },
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                placeholder = { Text("Add activity description") })
        }


        Spacer(modifier = Modifier.fillMaxWidth().height(35.dp))

        Button(onClick = {
            // Waiting for current location job to be completed
            // runBlocking { currentLocationJob.join() }
            returnLocation.latitude = locationParam.latitude
            returnLocation.longitude = locationParam.longitude
            val newSpot = Spot(
                nameText.text,
                activityText.text,
                returnLocation
            )
            JourneysList[activeJourneyID.ID].spots.add(newSpot)
            scope.launch {
                navController.navigate("JourneyView")
            }
        }
        )
        {
            Text("Save")
        }

    }
}