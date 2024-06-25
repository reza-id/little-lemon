package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.components.TopAppBar
import com.example.littlelemon.ui.components.HeroSection
import com.example.littlelemon.ui.components.MenuItems

@Composable
fun Home(database: AppDatabase, navController: NavController) {
    Column {
        TopAppBar(navController)
        HeroSection()
        val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
        MenuItems(menuItems = databaseMenuItems)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(database = AppDatabase.getDatabase(LocalContext.current), navController = navController)
}
