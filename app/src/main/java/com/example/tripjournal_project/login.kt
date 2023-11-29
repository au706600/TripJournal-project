package com.example.tripjournal_project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun login(service: Firestore, nav: NavController)
{
    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column {
        Row()
        {
            Text("Email: ")
            TextField(value = email.value, onValueChange = {newText -> email.value = newText},
                modifier = Modifier.fillMaxWidth())
        }

        Row()
        {
            Text("Password: ")
            TextField(value = email.value, onValueChange = {newText -> email.value = newText},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Button(onClick = {
            scope.launch {
                service.login(email.value, password.value)
            }
        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Don't have an account? Sign in here:",
            fontSize = 20.sp,
            color = Color.Black
        )

        Button(onClick = {
            nav.navigate("signup")
        }) {
            Text("Signup")
        }
    }
}