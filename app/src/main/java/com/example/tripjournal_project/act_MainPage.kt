package com.example.tripjournal_project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mainpage(menuItem: List<Metamodel>, onMenuItemClick: (Metamodel) -> Unit)
{
    MaterialTheme {
        Surface(color = MaterialTheme.colorScheme.background)
        {
            Row {
                Column(modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
                    .fillMaxHeight()
                    .width(120.dp)) {
                    LazyColumn(Modifier.background(color = MaterialTheme.colorScheme.primary)) {

                        items(menuItem) { item ->
                            menuitem(MenuItem = item) {
                                onMenuItemClick(item)
                            }
                        }
                    }
                }

                Column(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .weight(1f))
                {
                    TopAppBar(title = { Text(text = "Hi James")},
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = Color.DarkGray,
                            titleContentColor = Color(0xFFD0BCFF)))
                    
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Hello, welcome to the app",
                        fontSize = 20.sp,
                        color = Color.Black
                    )



                }

            }
        }
    }
}