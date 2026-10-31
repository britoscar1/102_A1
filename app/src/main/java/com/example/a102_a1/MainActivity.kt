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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

// CHALLENGE 1 SESSION 2
fun addFriend(friendList: MutableList<String>, newFriend: String) {
    if (newFriend !in friendList){
        friendList.add(newFriend)
    }
    else{
        print("This friend is already on the list")
    }
}

fun removeFriend(friendList: MutableList<String>, friendName: String) {
    if (friendName in friendList){
        friendList.remove(friendName)
    }
    else{
        print("This friend is not on the list")
    }
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
            onChangeUsername = { username = "@newUser123"},
            onAddFriend = { addFriend(friends, "Sofia")},
            onRemoveFriend = { removeFriend(friends, "Sofia")}
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
    onChangeUsername: () -> Unit,
    onAddFriend: () -> Unit,
    onRemoveFriend: () -> Unit
) {
    var showFriends by remember { mutableStateOf(false)}

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
        Text(text = "Verified: ${if (isVerified) "✅" else "❌"}",
            modifier = Modifier.padding(5.dp))
        Text(text = "Age Group: $ageGroup",
            modifier = Modifier.padding(5.dp))

        Row(
           verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onLike) {
                Icon(imageVector = Icons.Filled.ThumbUp,
                    contentDescription = "Like")
            }
            Text(text = "$likesCount")
        }

        Row {
//            Button(onClick = onLike) {
//                Text("Like")
//            }
            Button(onClick = onChangeUsername) {
                Text("Change Username")
            }
        }



        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,0.dp,20.dp)
        ) {
            Button(onClick = onRemoveFriend,
                modifier = Modifier.weight(1f)) {
                Text("Remove Friend")
            }
            Button(onClick = onAddFriend,
                modifier = Modifier.weight(1f)) {
                Text("Add Friend")
            }
        }

        Button(onClick = {showFriends = !showFriends}) {
            Text(if(showFriends) "Hide Friends" else "Show Friends")
        }

        if (showFriends) {
            Text(text = "Friends: (${friends.size}): ",
                modifier = Modifier.padding(5.dp))
            friends.forEach { friend ->
                Text(text = friend,
                    modifier = Modifier.padding(5.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    UserProfileScreen()
}