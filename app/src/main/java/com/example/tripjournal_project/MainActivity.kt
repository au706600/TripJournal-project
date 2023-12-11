package com.example.tripjournal_project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tripjournal_project.ui.theme.TripJournalprojectTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
        @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = Firebase.auth
        FirebaseApp.initializeApp(this)
        val db = FirebaseFirestore.getInstance()
        // For retrieving the test user data on firebase.
        //db.document("testuser/18ncw63frXQdnBzs61Dv").get().addOnSuccessListener{
          //  Log.v("Logging", it.data?.get("Name").toString())
        //}

        val service = Firestore(db, auth)
        auth.currentUser

        val menuItems = listOf(
            Metamodel("1", "Share", "Share")
            {
                println("Clicked")
            },
            Metamodel("2", "My Journeys", "My journeys")
            {
                println("Clicked")
            },
            Metamodel("3", "Contact us", "Contact us")
            {
                println("Clicked")
            },
            Metamodel("4", "Log out", "Log out")
            {
                println("Clicked")
            }
        )
        setContent {
            TripJournalprojectTheme {
                val navController = rememberNavController()
                val journeyViewModel: JourneyViewModel = viewModel()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    NavHost(navController = navController, startDestination = "mainpage")
                    {
                        composable("signup")
                        {
                            signup(service, nav = navController)
                            {
                                navController.popBackStack("login", inclusive = false)
                            }
                        }

                        composable("login")
                        {
                            login(service, nav = navController)
                        }

                        composable("mainpage")
                        {
                            Mainpage(menuItem = menuItems, onMenuItemClick = {
                                selectedItem -> when (selectedItem.id)
                            {
                                    "1" -> {
                                        navController.navigate("Share")
                                    }

                                "2" -> {
                                    navController.navigate("My Journeys")
                                }

                                "3" -> {
                                    navController.navigate("Contact us")
                                }

                                "4" -> {
                                    navController.navigate("login") // Logging out
                                }
                                }
                            })
                        }


                        composable("Share")
                        {
                            ShareButton()
                        }

                        composable("My Journeys")
                        {
                            MyJourneys(navController = navController, journeyViewModel = journeyViewModel)
                            {
                                navController.popBackStack("mainpage", inclusive = false)
                            }
                        }

                        composable("Add Journey")
                        {
                            AddJourney(navController = navController) {
                                navController.popBackStack("My Journeys", inclusive = false)
                            }
                        }

                        composable("Journeys")
                        {
                            Journeys(navController = navController, journeyViewModel = journeyViewModel) {
                                navController.popBackStack("Add Journey", inclusive = false)
                            }
                        }


                        composable("Contact us")
                        {
                            Contact_us()
                            {
                                navController.popBackStack("mainpage", inclusive = false)
                            }
                        }

                    }
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