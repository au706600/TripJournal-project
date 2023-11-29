package com.example.tripjournal_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tripjournal_project.ui.theme.TripJournalprojectTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()
        // For retrieving the test user data on firebase.
        //db.document("testuser/18ncw63frXQdnBzs61Dv").get().addOnSuccessListener{
          //  Log.v("Logging", it.data?.get("Name").toString())
        //}

        //val service = FireStore(db, auth)
        auth.currentUser
        setContent {
            TripJournalprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TripJournalprojectTheme {
        Greeting("Android")
    }
}