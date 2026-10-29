package com.example.a102_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// CHALLENGE 7
fun getAgeGroup(age: Int): String {
    val ageGroup: String = when {
        age < 13 -> "Child"
        age < 17 -> "Teenager"
        age < 60 -> "Adult"
        else -> "Senior"
    }
    return ageGroup
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserProfileScreen()
        }
    }
}

@Composable
fun UserProfileScreen() {
    var name by remember { mutableStateOf("John Smith") }
    var age by remember { mutableIntStateOf(30) }
    val birthday = "1996/05/25"
    val address by remember { mutableStateOf("123 Example St") }
    var username by remember { mutableStateOf("@smith_j") }
    var like by remember { mutableIntStateOf(0) }
    val isVerified = true

    // CHALLENGE 3 SESSION 1
    val friends = remember {
        mutableStateListOf("Pablo", "Arturo", "Sebastian", "Diego", "Joshua")
    }
    val ageGroup = getAgeGroup(age)

    Surface {
        ProfileContent(
            name = name,
            age = age,
            birthday = birthday,
            address = address,
            username = username,
            likesCount = like,
            isVerified = isVerified,
            ageGroup,
            friends = friends,
            onLike = {like ++},
            onChangeUsername = { username = "@newUser123"}
        )
    }
}

@Composable
fun ProfileContent(
    name: String,
    age: Int,
    birthday: String,
    address: String,
    username: String,
    likesCount: Int,
    isVerified: Boolean,
    ageGroup: String,
    friends: List<String>,
    onLike: () -> Unit,
    onChangeUsername: () -> Unit)
{
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(20.dp,35.dp,20.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\uD83D\uDE03",
                    style = androidx.compose.material3.MaterialTheme.typography.displayMedium
                )
                Text(text = name)
                Text(text = username)
            }
        }
        Text(text = "User Profile",
            modifier = Modifier.padding(20.dp))
        Text(text = "Name: $name",
            modifier = Modifier.padding(5.dp))
        Text(text = "Age: $age",
            modifier = Modifier.padding(5.dp))
        Text(text = "Birthday: $birthday",
            modifier = Modifier.padding(5.dp))
        Text(text = "Address: $address",
            modifier = Modifier.padding(5.dp))
        Text(text = "Username: $username",
            modifier = Modifier.padding(5.dp))
        Text(text = "Likes: $likesCount",
            modifier = Modifier.padding(5.dp))
        Text(text = "Verified: ${if (isVerified) "✅" else "❌"}")

        Row {
            Button(onClick = onLike) {
                Text("Like")
            }
            Button(onClick = onChangeUsername) {
                Text("Change Username")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    UserProfileScreen()
}