package com.example.littlelemon.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun CategorySection(categories: Set<String>, selectedCategory: String, onSelectCategory: (sel: String) -> Unit) {
    val cat = remember { mutableStateOf("") }
    Column {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Category(category = "All", selectedCategory == "all" || selectedCategory == "") {
                cat.value = it.lowercase()
                onSelectCategory(it.lowercase())
            }
            for (category in categories) {
                Category(category = category, selectedCategory == category) {
                    cat.value = it
                    onSelectCategory(it)
                }
            }
        }
        Divider(
            thickness = 2.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Composable
fun Category(category: String, isSelected: Boolean, onClickCategory: (String) -> Unit) {
    Button(
        onClick = {
            onClickCategory(category)
        },
        shape = RoundedCornerShape(18),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFF495E57),
            containerColor = if (isSelected) LittleLemonColor.yellow else LittleLemonColor.cloud

        )
    ) {
        Text(
            text = category,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
