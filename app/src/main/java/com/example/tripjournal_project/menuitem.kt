package com.example.tripjournal_project

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Metamodel(
    val id: String,
    val title: String,
    val contentDescription: String,
    val onClick: () -> Unit
)

@Composable

fun menuitem(text:String, menuItem: Metamodel)
{

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,) {
            //Icon(contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text,
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp)
        }
    }

}