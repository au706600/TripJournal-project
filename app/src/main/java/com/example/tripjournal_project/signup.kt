package com.example.tripjournal_project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signup(service: Firestore, nav: NavController, back: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { back() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                }
                Text(
                    text = "Sign up",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.DarkGray,
            titleContentColor = Color(0xFFD0BCFF))
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row()
        {
            Text("Email: ")
            TextField(
                value = email.value, onValueChange = { newText -> email.value = newText },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row()
        {
            Text("Password: ")
            TextField(
                value = password.value, onValueChange = { newText -> password.value = newText },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Button(onClick = {
            scope.launch {
                val user = service.signup(email.value, password.value)
                nav.navigate("login")
            }
        }) {
            Text("Signup")
        }
    }
}


public fun getUserprofile()
{
    val user = Firebase.auth.currentUser
    user?.let {
        val email = it.email
        val emailVerified = it.isEmailVerified

        val uid = it.uid

    }
}

public fun addUserToFirebase(uid:String, user: FirebaseUser)
{
    val db = FirebaseFirestore.getInstance()
    db.collection("users").document(uid).set(hashMapOf(
        "id" to user.uid,
        "email" to user.email
    ))
}