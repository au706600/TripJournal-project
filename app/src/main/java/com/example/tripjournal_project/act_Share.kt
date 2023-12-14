package com.example.tripjournal_project

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShareButton(
    JourneysList: ArrayList<Journey>,
    activeJourneyID: activeJourneyID
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }

    Button(
        onClick = {
            val journey = JourneysList[activeJourneyID.ID]


            // Share intent
            val shareIntent = Intent(ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(EXTRA_SUBJECT, "A trip to ".plus(journey.name))

            val shareString = "A trip to ".plus(journey.name).plus("\n\nWith stops in:\n")

            for(spot in journey.spots) {
                shareString
                    .plus("Spot: ").plus(spot.name)
                    .plus("\nActivity: ").plus(spot.activity)
                    .plus("\n")
            }

            shareIntent.putExtra(EXTRA_TEXT, shareString)

            // Create a chooser dialog
            val chooser = Intent.createChooser(shareIntent, "Share via")

            // Launch the intent with the chooser dialog
            launcher.launch(chooser)
        }
    ) {
        Text("Share via")
    }
}