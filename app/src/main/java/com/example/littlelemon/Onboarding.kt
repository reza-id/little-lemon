package com.example.littlelemon

import android.content.Context
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.littlelemon.Constants.EMAIL
import com.example.littlelemon.Constants.FIRST_NAME
import com.example.littlelemon.Constants.LAST_NAME
import com.example.littlelemon.Constants.USER_PREFERENCES

@Composable
fun Onboarding() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = "Let's get to know you",
            color = White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(color = Color(0xFF495E57))
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

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
                value = firstName,
                onValueChange = { firstName = it },
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
                value = lastName,
                onValueChange = { lastName = it },
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
                value = email,
                onValueChange = { email = it },
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedButton(
                onClick = {
                    if(firstName.isBlank() && lastName.isBlank() && !EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "Registration unsuccessful, Please enter all data", Toast.LENGTH_SHORT).show()
                    } else {
                        sharedPreferences.edit(commit = true) {
                            putString(FIRST_NAME, firstName)
                            putString(LAST_NAME, lastName)
                            putString(EMAIL, email)
                        }

                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
                modifier = Modifier
                    .padding(top = 48.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    Onboarding()
}
