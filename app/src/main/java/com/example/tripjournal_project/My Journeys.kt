package com.example.tripjournal_project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MyJourneys(navController: NavController,
               JourneysList: ArrayList<tourney>,
               journeyViewModel: JourneyViewModel,
               activeJourneyID: activeJourneyID,
               back:()->Unit)
{
    val scope = rememberCoroutineScope()
    var journeys by rememberSaveable { mutableStateOf<List<tourney>>(emptyList()) }

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
                        text = "My Journeys",
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
            .height(10.dp)
            .background(color = Color.White)
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
        ) {
            itemsIndexed(JourneysList) { i, journey ->
                Text(
                    text = journey.name,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .background(color = Color.LightGray)
                        .clickable {
                            activeJourneyID.ID = i
                            scope.launch {
                                navController.navigate(
                                    "JourneyView"
                                )
                            }
                        }
                )
                Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Button(onClick = {
            scope.launch {
                navController.navigate("Add Journey")
            }
        })
        {
            Text("Add Journey")
        }
    }
}
