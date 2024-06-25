package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.components.CategorySection
import com.example.littlelemon.ui.components.TopAppBar
import com.example.littlelemon.ui.components.HeroSection
import com.example.littlelemon.ui.components.MenuItems

@Composable
fun Home(database: AppDatabase, navController: NavController) {
    Column {
        val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())
        val categories = databaseMenuItems.map {
            it.category.replaceFirstChar { character -> character.uppercase() }
        }.toSet()
        var selectedCategory by remember { mutableStateOf("") }

        var searchPhrase by remember { mutableStateOf("") }
        var menuItems = if (searchPhrase.isNotEmpty()) {
            databaseMenuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        } else {
            databaseMenuItems
        }

        menuItems = if (selectedCategory == "" || selectedCategory == "all") {
            menuItems
        } else {
            menuItems.filter { it.category.contains(selectedCategory, ignoreCase = true) }
        }

        TopAppBar(navController)
        HeroSection(searchPhrase, onSearchChange = { searchPhrase = it })
        CategorySection(categories, selectedCategory, onSelectCategory = { selectedCategory = it })
        MenuItems(menuItems = menuItems)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(database = AppDatabase.getDatabase(LocalContext.current), navController = navController)
}
