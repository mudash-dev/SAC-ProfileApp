package com.example.composeschoolprofile

//import androidx.compose.ui.tooling.data.EmptyGroup.name
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
            Image(painter = painterResource(id = R.drawable.unilogo), contentDescription ="SAC" ,Modifier.background(color = Color.White))
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
        Spacer(modifier = Modifier.height(4.dp))
        //Collect data
        Column (
            modifier = Modifier
                .fillMaxWidth()
                ){
            Text(text = "Fill in your Details below:",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Green

                )
            Spacer(modifier = Modifier.height(5.dp))
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
            Spacer(modifier = Modifier.height(4.dp))
            var regno by remember {
                mutableStateOf(TextFieldValue(" "))
            }
            OutlinedTextField(
                value = regno,
                label = { Text(text = "Enter Registration Number") },
                onValueChange = { newText ->
                    regno = newText
                }

            )
            val course by remember {
                mutableStateOf(TextFieldValue(" "))
            }
            OutlinedTextField(
                value = course,
                label = { Text(text = "Enter Your Course of Study") },
                onValueChange = { newText ->
                    regno = newText
                }

            )

            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = "Choose Your Department:",
                    style = MaterialTheme.typography.titleSmall
                    )

                Spacer(modifier = Modifier.height(4.dp))

                val radioOptions = listOf(" COPAS ", " COHES ", " SCIT ")
                radioOptions.forEach { label ->
                    radioButton()
                    Text(
                        text = label,
                        color = Color.Black
                    )
                }
            }
            }
        Spacer(modifier = Modifier.width(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    Toast.makeText(context, "You profile has been saved.", Toast.LENGTH_SHORT).show()

                },
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ) {
                Text(text = "Save",color = Color.Black)
            }
        }


        }
    }



fun radioButton() {

}

@Preview(showBackground = true)
@Composable
fun StudentPreview() {
    ComposeSchoolProfileTheme {
        Student("Scholars Association College")
    }
}