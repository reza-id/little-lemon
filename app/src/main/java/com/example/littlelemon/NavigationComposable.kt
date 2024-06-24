package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationComposable(context: Context, navController: NavHostController, paddingValues: PaddingValues) {
    val sharedPreferences = context.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE)
    val isLoggedIn = sharedPreferences.getBoolean(Constants.LOGGED_IN, false)

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = if (isLoggedIn) Home.route else Onboarding.route
    ) {
        composable(Onboarding.route) {
            Onboarding(context, navController)
        }
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(context, navController)
        }
    }
}
