package com.example.littlelemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Home Screen", textAlign = TextAlign.Center)
        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Go to profile")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}
