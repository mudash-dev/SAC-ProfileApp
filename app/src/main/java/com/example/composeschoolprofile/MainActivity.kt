package com.example.composeschoolprofile

//import androidx.compose.ui.tooling.data.EmptyGroup.name
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route) { Home(navController)}
        composable(
            Screen.ViewProfile.route + "/{textname}/{regno}/{schoolemail}/{course} ",
             arguments = listOf(
                 navArgument("textname"){type = NavType.StringType},
                 navArgument("regno"){type = NavType.StringType},
                 navArgument("schoolemail"){type = NavType.StringType},
                 navArgument("course"){type = NavType.StringType},
                // navArgument("selecteditem"){type = NavType.StringType}
             )
        )
        { entry ->
            ViewProfile(
                textname = entry.arguments?.getString("textname"),
                regno = entry.arguments?.getString("regno"),
                schoolemail = entry.arguments?.getString("schoolemail"),
                course = entry.arguments?.getString("course"),
                navController = navController
            )
        }
    }
}


const val college:String = "Scholars Association College"

@Composable
fun Home(navController: NavController) {
    Column {
        //header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.unilogo), contentDescription = "SAC",
                Modifier.background(color = Color.White)
            )
            Text(
                text = college,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(24.dp)
            )
        }

        //upload profile
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ) {
            val contextForToast = LocalContext.current.applicationContext
            Image(painter = painterResource(id = R.drawable.upload),
                contentDescription = "Profile pic",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .clickable {
                        Toast
                            .makeText(contextForToast, "Upload Your Photo", Toast.LENGTH_LONG)
                            .show()
                    }


            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        //Collect data
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Fill in your Details below:",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Blue

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
            Spacer(modifier = Modifier.height(5.dp))
            var schoolemail by remember {
                mutableStateOf(TextFieldValue(" "))
            }
            OutlinedTextField(
                value = schoolemail,
                label = { Text(text = "Enter Your assigned School Email") },
                onValueChange = { newText ->
                    schoolemail = newText
                }

            )
            var course by remember {
                mutableStateOf(TextFieldValue(" "))
            }
            OutlinedTextField(
                value = course,
                label = { Text(text = "Enter Your Course of Study") },
                onValueChange = { newText ->
                    course = newText
                }

            )
            Spacer(modifier = Modifier.height(4.dp))
            //choose department
            Row {
                Text(
                    text = "Choose Your Department:",
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(4.dp))
                val radioOptions = listOf(" COPAS ", " COHES ", " SCIT ")
                var selectedItem by remember {
                    mutableStateOf(radioOptions[0])
                }
                Column(modifier = Modifier.selectableGroup())
                {
                    radioOptions.forEach { label ->
                        Row(
                            modifier = Modifier
                                //.height(20.dp)
                                .selectable(
                                    selected = (selectedItem == label),
                                    onClick = { selectedItem = label },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            // verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            RadioButton(
                                modifier = Modifier.padding(end = 10.dp),
                                selected = (selectedItem == label),
                                onClick = null
                            )
                            Text(
                                text = label,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
        {
            val context = LocalContext.current
            Button(
                onClick = {
                    Toast.makeText(context, "You profile has been saved.", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.ViewProfile.withArgs(textname,regno,schoolemail,course))

                },
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ){
                Text(text = "Save", color = Color.Black)
            }

        }

            }
    }
}

@Composable
fun ViewProfile(
    textname: String?,
    regno: String?, schoolemail: String?,
    course: String?,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding((16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        )
        {
            Text(text = textname.toString())
            Text(text = regno.toString())
            Text(text = schoolemail.toString())
            Text(text = course.toString())

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
            { Button(
                onClick = {
                    navController.popBackStack(Screen.Home.route,false)
                },
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ){
                Text(text = "Edit", color = Color.Black)
            }
            }
        }
    }
}
