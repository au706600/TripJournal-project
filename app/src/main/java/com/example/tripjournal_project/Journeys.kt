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
fun Journeys(navController: NavController, viewmodel: SharedViewmodel,journeyViewModel: JourneyViewModel, back:()-> Unit)
{
    val scope = rememberCoroutineScope()
    var text by remember{ mutableStateOf(TextFieldValue("")) }
    var Text by remember{ mutableStateOf(TextFieldValue("")) }
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

        Row()
        {
            TextField(value = text, onValueChange = { newValue -> text = newValue },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(), placeholder = { Text("Add location") })
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        Row()
        {
            TextField(value = Text,
                onValueChange = { newValue -> Text = newValue },
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                placeholder = { Text("Add Description") })
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(15.dp))

        Button(onClick = {
            val newJourney = tourney(
                name = text.text,
                location = viewmodel.userlocation.value ?: Location(""),
                spots = ArrayList<Spot>()
            )
            journeyViewModel.addJourney(newJourney)
            scope.launch {
                navController.navigate("My Journeys")
            }
        }) {
            Text("Save")
        }

    }
}