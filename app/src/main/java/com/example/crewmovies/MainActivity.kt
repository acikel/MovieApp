package com.example.crewmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crewmovies.ui.*
import com.example.crewmovies.ui.theme.CrewMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrewSearchApp()
        }
    }
}

@Composable
fun CrewSearchApp() {
    CrewMoviesTheme {
        // A surface container using the 'background' color from the theme
        //TODO stateless machen wie in nowinandroid
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            crewTabRowScreens.find { it.route == currentDestination?.route } ?: Home

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,

            ) {
            //CrewSearchScreen()
            //ListsScreen()
            Scaffold(
                bottomBar = {
                    CrewBottomTabRow(
                        allScreens = crewTabRowScreens,
                        onTabSelected = { newScreen ->
                            navController.navigateSingleTopTo(newScreen.route)
                        },
                        currentScreen = currentScreen
                    )
                }
                //backgroundColor = Color(R.color.purple_light),
            ) {
                    innerPadding ->
                CrewNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrewMoviesTheme {
        Greeting("Android")
    }
}