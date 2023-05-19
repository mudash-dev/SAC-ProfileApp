package com.example.composeschoolprofile

//import androidx.compose.ui.tooling.data.EmptyGroup.name
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeschoolprofile.ui.theme.ComposeSchoolProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSchoolProfileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Student("Scholars Association College")
                }
            }
        }
    }
}

@Composable
fun Student(school: String) {
    Column {
        //header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            Image(painter = painterResource(id = R.drawable.unilogo), contentDescription ="SAC" )
            Text(
                text = school,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(24.dp)
            )
        }

        //upload profile
        Row(horizontalArrangement = Arrangement.Center ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            Image(painter = painterResource(id = R.drawable.upload),
                contentDescription ="Profile pic",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)


            )
        }

        //Collect data
        Row (
            modifier = Modifier
                .fillMaxWidth()
                ){

            var textname by remember {
                mutableStateOf(TextFieldValue(" "))
            }
            OutlinedTextField(
                value = textname,
                label = { Text(text = "Enter Your Name") },
                onValueChange = { newText ->
                    textname = newText
                }

            )

        }
    }


}


@Preview(showBackground = true)
@Composable
fun StudentPreview() {
    ComposeSchoolProfileTheme {
        Student("Scholars Association College")
    }
}