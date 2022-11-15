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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.crewmovies.ui.*
import com.example.crewmovies.ui.navigation.NavigationManager
import com.example.crewmovies.ui.screen_crew_search.CrewBottomTabRow
import com.example.crewmovies.ui.theme.CrewMoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrewSearchApp(navigationManager)
        }
    }
}

@Composable
fun CrewSearchApp(navigationManager: NavigationManager) {
    CrewMoviesTheme {


        val navController = rememberNavController()
        //initialize commands flow of navigation manager with initial value as its a stateflow and therefore needs a initial value
        navigationManager.commands.collectAsState().value.also { command ->
            if (command.destination.isNotEmpty()) {
                navController.navigate(command.destination)
                //navController.navigateSingleTopTo(command.destination)
            }
        }

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            crewTabRowScreens.find { it.route == currentDestination?.route } ?: CrewSearchNavigationDestinations.Home

        CrewSearchAppScaffold(navController, currentScreen)
    }
}

@Composable
fun CrewSearchAppScaffold(
    navController : NavHostController,
    currentScreen : NavigationCommandWithIcon
){
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
                        navController.navigateSingleTopTo(newScreen.destination)
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