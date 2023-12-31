package com.example.tripjournal_project

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun JourneyView(
    navController: NavController,
    JourneysList : ArrayList<tourney>,
    journeyViewModel: JourneyViewModel,
    activeJourneyID: activeJourneyID,
    back:()->Unit)
{

    // Used for sharing to other apps
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }

    val journeyIndex = activeJourneyID.ID

    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally)
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
                        text = JourneysList[journeyIndex].name,
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

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = Color.White)
        )

        Button(onClick = {
            scope.launch {
                navController.navigate("Add Spot")
            }
        })
        {
            Text("Add Spot")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = Color.White)
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
        ) {
            items(JourneysList[journeyIndex].spots) { spot ->
                Text(text = spot.name)
                Text(text = spot.activity)
                Spacer(modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .height(20.dp))
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(color = Color.White)
        )

        Button(onClick = {
            scope.launch {
                navController.navigate("Map")
            }
        }) {
            Text("Show Map")
        }

        Button(onClick = {
            val journey = JourneysList[activeJourneyID.ID]

            // Share intent
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "A trip to ".plus(journey.name))

            var shareStringBuilder = StringBuilder()
            shareStringBuilder.append("A trip to ".plus(journey.name).plus("\n\nWith stops in:\n"))

            for(spot in journey.spots) {
                shareStringBuilder.append(
                    "Spot: ".plus(spot.name)
                        .plus("\nActivity: ").plus(spot.activity)
                        .plus("\n"))
            }

            val shareString = shareStringBuilder.toString()

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareString)

            // Create a chooser dialog
            val chooser = Intent.createChooser(shareIntent, "Share via")

            // Launch the intent with the chooser dialog
            launcher.launch(chooser)
        }) {
            Text("Share")
        }
    }
}