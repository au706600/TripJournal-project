package com.example.tripjournal_project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Contact_us(back: () -> Unit)
{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        IconButton(onClick = {back()}) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                        }
                    }
                    Text(
                        text = "Contact us", modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color(0xFFD0BCFF)
                )
            )
            Text(
                text = "Hello, welcome. In case you have questions about the app or there is something that doesn't work, " +
                        "please contact us at: ",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Thomas: ",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "201908338@post.au.dk",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Mohammad: ",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "202106601@post.au.dk",
                fontSize = 20.sp,
                color = Color.Black
            )


        }
    }
