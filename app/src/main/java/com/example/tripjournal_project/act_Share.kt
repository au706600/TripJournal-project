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
fun ShareButton() {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }

    Button(
        onClick = {
            // Share intent
            val shareIntent = Intent(ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(EXTRA_SUBJECT, "Subject")
            shareIntent.putExtra(EXTRA_TEXT, "Your shared text here.")

            // Create a chooser dialog
            val chooser = Intent.createChooser(shareIntent, "Share via")

            // Launch the intent with the chooser dialog
            launcher.launch(chooser)
        }
    ) {
        Text("Share via")
    }
}