package com.example.littlelemon.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.littlelemon.MenuItemEntity
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun MenuItems(menuItems: List<MenuItemEntity>) {
    LazyColumn {
        items(
            items = menuItems,
            itemContent = { menuItem ->
                MenuItem(menuItem = menuItem)
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemEntity) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = menuItem.desc,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = "$${menuItem.price}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            GlideImage(
                model = menuItem.image,
                contentScale = ContentScale.Crop,
                contentDescription = menuItem.title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow
    )
}

@Preview(showBackground = true)
@Composable
private fun MenuItemsPreview() {
    MenuItems(
        menuItems = listOf(
            MenuItemEntity(
                1,
                "Title",
                10.0,
                "Lorem ipsum dolor sit amet consectetur adipiscing elit",
                "category",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true"
            ),
            MenuItemEntity(
                2,
                "Title",
                10.0,
                "Lorem ipsum dolor sit amet consectetur adipiscing elit",
                "category",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true"
            ),
            MenuItemEntity(
                3,
                "Title",
                10.0,
                "Lorem ipsum dolor sit amet consectetur adipiscing elit",
                "category",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true"
            ),
        )
    )
}
