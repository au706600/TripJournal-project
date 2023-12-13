package com.example.tripjournal_project

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

data class Metamodel(
    val id: String,
    val title: String,
    val contentDescription: String,
    val onClick: () -> Unit
)


data class Journey(val name: String)

data class trippoints(val location: Location)

class JourneyViewModel : ViewModel() {
    private val _journeys = mutableStateOf(listOf<Journey>())
    val journeys: State<List<Journey>> = _journeys

    fun addJourney(journey: Journey) {
        _journeys.value = _journeys.value + journey
    }
}
@Composable

fun menuitem(MenuItem: Metamodel, navigate: () -> Unit)
{

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).
            clickable {
                      MenuItem.onClick()
                navigate()
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,) {
            //Icon(contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = MenuItem.title,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp)
        }
    }

}