package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(context: Context, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Profile Screen", textAlign = TextAlign.Center)
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = {
            context.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE)
                .edit(commit = true) { clear() }
            navController.navigate(Onboarding.route) {
                popUpTo(Home.route) {
                    inclusive = true
                }
            }
        }) {
            Text(text = "Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    val navController = rememberNavController()
    Profile(context = LocalContext.current, navController = navController)
}
