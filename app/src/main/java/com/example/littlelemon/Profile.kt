package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.Constants.EMAIL
import com.example.littlelemon.Constants.FIRST_NAME
import com.example.littlelemon.Constants.LAST_NAME
import com.example.littlelemon.Constants.USER_PREFERENCES

@Composable
fun Profile(context: Context, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Text(
            text = "Personal information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "First name",
            fontSize = 14.sp,
            color = DarkGray,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = sharedPreferences.getString(FIRST_NAME, null) ?: "",
            onValueChange = {  },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Last name",
            fontSize = 14.sp,
            color = DarkGray,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = sharedPreferences.getString(LAST_NAME, null) ?: "",
            onValueChange = {  },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Email",
            fontSize = 14.sp,
            color = DarkGray,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 8.dp)
        )
        OutlinedTextField(
            value = sharedPreferences.getString(EMAIL, null) ?: "",
            onValueChange = {  },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedButton(
            onClick = {
                sharedPreferences.edit(commit = true) { clear() }
                navController.navigate(Onboarding.route) {
                    popUpTo(Home.route) {
                        inclusive = true
                    }
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
            modifier = Modifier
                .padding(top = 48.dp)
        ) {
            Text(
                text = "Log out",
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    val navController = rememberNavController()
    Profile(context = LocalContext.current, navController = navController)
}
