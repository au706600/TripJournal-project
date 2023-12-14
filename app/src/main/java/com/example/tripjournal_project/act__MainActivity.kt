package com.example.tripjournal_project

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tripjournal_project.ui.theme.TripJournalprojectTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.Locale


object JourneyData {
    val JourneyList = generateStaticData()
}

class MainActivity : ComponentActivity() {
    private lateinit var mapsClient: FusedLocationProviderClient
    private lateinit var geoCoder: Geocoder
    private lateinit var service: LocationService

        @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapsClient = LocationServices.getFusedLocationProviderClient(this)
        geoCoder = Geocoder(this, Locale.getDefault())
        service = LocationService(this, mapsClient, geoCoder)
        service.requestPermission()

            // Contains saved journey data
        val JourneysList = ArrayList<Journey>()
            // Used to access the right index of the journeys list
        val activeJourneyID = activeJourneyID(ID = 0)
            // Used to pass location data between activities. Should be current location
        var locationParam = Location("")
            locationParam.latitude = 56.15674
            locationParam.longitude = 10.21076

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
                            MyJourneys(
                                navController = navController,
                                JourneysList = JourneysList,
                                journeyViewModel = journeyViewModel,
                                activeJourneyID = activeJourneyID)
                            {
                                navController.popBackStack("mainpage", inclusive = false)
                            }
                        }

                        composable("Add Journey")
                        {
                            AddJourney(
                                navController = navController,
                                JourneysList = JourneysList,
                                locationService = service,
                                locationParam = locationParam)
                            {
                                navController.popBackStack("My Journeys", inclusive = false)
                            }
                        }

                        composable(
                            route = "JourneyView")
                        {
                            JourneyView(
                                navController = navController,
                                JourneysList = JourneysList,
                                journeyViewModel = journeyViewModel,
                                activeJourneyID = activeJourneyID)
                            {
                                navController.popBackStack("My Journeys", inclusive = false)
                            }
                        }

                        composable("Add Spot")
                        {
                            AddSpot(
                                navController = navController,
                                journeyViewModel = journeyViewModel,
                                JourneysList = JourneysList,
                                activeJourneyID = activeJourneyID,
                                locationService = service,
                                locationParam = locationParam) {
                                navController.popBackStack("JourneyView", inclusive = false)
                            }
                        }

                        composable("Map")
                        {
                            //val journeypoint = Journeypoints(1, "trip points", LocalDateTime.now())
                            Map(
                                JourneysList = JourneysList,
                                activeJourneyID = activeJourneyID,
                                locationParam = locationParam)
                            {
                                navController.popBackStack("JourneyView", inclusive = false)
                            }
                        }

                        composable("SpotMapLocationFinder") {
                            SpotMapLocationFinder(
                                JourneysList = JourneysList,
                                activeJourneyID = activeJourneyID,
                                locationParam = locationParam)
                            {
                                navController.popBackStack("Add Spot", inclusive = false)
                            }
                        }

                        composable("JourneyMapLocationFinder") {
                            JourneyMapLocationFinder(
                                JourneysList = JourneysList,
                                activeJourneyID = activeJourneyID,
                                locationParam = locationParam)
                            {
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
