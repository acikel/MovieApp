package com.example.crewmovies.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltNavGraphViewModel depricated see comment below
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.ui.navigation.CrewSearchDetailsDestinations
import com.example.crewmovies.ui.navigation.getJsonParse
import com.example.crewmovies.ui.screen_crew_search.CrewSearchScreen
import com.example.crewmovies.ui.screen_crew_search.CrewSearchViewModel
import com.example.crewmovies.ui.screen_crew_search_details.CrewSearchDetailsScreen
import com.example.crewmovies.ui.screen_crew_search_details.CrewSearchDetailsViewModel
import com.example.crewmovies.ui.screen_lists.DialogBoxAddList
import com.example.crewmovies.ui.screen_lists.ListScreenViewModel
import com.example.crewmovies.ui.screen_lists.ListsScreen
import com.google.gson.Gson

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CrewNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CrewSearchNavigationDestinations.root.destination, //starts at the startDestination of the feature of CrewSearchTabNavigationDestinations
        modifier = modifier
    ) {
        //nesting navigations with navigation into features see https://hitherejoe.medium.com/nested-navigation-graphs-in-jetpack-compose-dc0ada1d4726
        navigation(startDestination = CrewSearchNavigationDestinations.Home.destination,
            route = CrewSearchNavigationDestinations.root.destination
        ) {
            composable(CrewSearchNavigationDestinations.Home.destination) {
                val viewmodel = hiltViewModel<CrewSearchViewModel>()
                CrewSearchScreen(
                    /* hiltNavGraphViewModel is depricated use hiltViewModel<ExampleViewModel>() instead (also see https://developer.android.com/jetpack/compose/libraries?authuser=3#hilt-navigation):
                    navController.hiltNavGraphViewModel(
                        route = CrewSearchNavigationDestinations.Home.destination
                    )
                     */
                //println("Destination: ${navCom.destination}")

                    onClickItem = { person ->
                        val navCom = CrewSearchDetailsDestinations.crewSearchDetailsPerson(person.getJsonParse())
                        //navController.navigate("${CrewSearchDetailsDestinations.destination}/$navCom")
                        navController.navigateSingleTopTo(navCom.destination)
                    },
                    crewSearchViewModel = viewmodel

                )
            }
            composable(CrewSearchNavigationDestinations.Lists.destination) {
                val viewmodel = hiltViewModel<ListScreenViewModel>()
                ListsScreen(
                    listScreenViewModel=viewmodel
                )
            }

            composable(CrewSearchDetailsDestinations.destination, CrewSearchDetailsDestinations.arguments
            ){ navBackStackEntry->
                val person = navBackStackEntry.arguments?.getString("person_id")?.let { Gson().fromJson(it.replace("$$$", "/"), PeopleResultModel::class.java) }
                //if(navBackStackEntry.savedStateHandle.contains("person"))
                //    navBackStackEntry.savedStateHandle.remove<PeopleResultModel>("person")
                //navBackStackEntry?.savedStateHandle?.set("person", person)
                println("profile picture path: $person.profilePicturePath")
                println("i entered details screen ${person?.name}")
                val viewmodel = hiltViewModel<CrewSearchDetailsViewModel>()
                person?.let { CrewSearchDetailsScreen(person, crewSearchDetailsViewModel = viewmodel) }
                //CrewSearchDetailsScreen(crewSearchDetailsViewModel = viewmodel)
            }

        }
        /*As per the dialog documentation:
                This is suitable only when this dialog represents a separate screen in your app that needs its own lifecycle and saved state, independent of any other destination in your navigation graph. For use cases such as AlertDialog, you should use those APIs directly in the composable destination that wants to show that dialog.

                So you shouldn't be using a dialog destination at all: a dialog destination is specifically and only for providing the content lambda of a regular Dialog. What your code is actually doing is creating an empty Dialog (i.e., you don't emit any composables in the lambda you pass to dialog, then creating another AlertDialog stacked on top of that empty dialog. That's not what you want to be doing.

                Instead, you should be following the AlertDialog docs and directly creating the AlertDialog where you want it to be used, setting your own boolean for when it should be shown/hidden.
                 Quelle: https://stackoverflow.com/questions/72478905/how-to-integrate-alertdialog-with-navigation-component-in-jetpack-compose
                 https://stackoverflow.com/a/72479997
                 https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#AlertDialog(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.window.DialogProperties)

        navigation(startDestination = DialogDestinations.AddList.destination,
            route = DialogDestinations.root.destination
        ) {
            //composable(DialogDestinations.AddList.destination) {
            /*
            val viewmodel = hiltViewModel<ListScreenViewModel>()
            DialogBoxAddList(
                viewmodel
            )
             */
            /*
            The Navigation back stack stores a NavBackStackEntry not only for each individual destination, but also for each parent navigation graph that contains the individual destination. This allows you to retrieve a NavBackStackEntry that is scoped to a navigation graph. A navigation graph-scoped NavBackStackEntry provides a way to create a ViewModel that's scoped to a navigation graph, enabling you to share UI-related data between the graph's destinations. Any ViewModel objects created in this way live until the associated NavHost and its ViewModelStore are cleared or until the navigation graph is popped from the back stack.
            This means we can use the NavBackStackEntry to get the scope of the navigation graph we are in and use that as the ViewModelStoreOwner to get the viewmodel for that scope.
            Add this in every composable to get the BackStackEntry for login and then use that as the ViewModelStoreOwner to get the viewmodel.
            Quelle: https://stackoverflow.com/questions/69642441/how-to-share-a-viewmodel-between-navgraph-components-only

            val loginBackStackEntry = remember { navController.getBackStackEntry(CrewSearchNavigationDestinations.Lists.destination) }
            val listScreenViewModel: ListScreenViewModel = hiltViewModel(loginBackStackEntry)
            */

            /*

             */
            //DialogBoxAddList(
            //    listScreenViewModel
            //)
            //}
        }
        */

        /*
        // builder parameter will be defined here as the graph
        composable(route = Home.route) {
            CrewSearchScreen()
        }
        composable(route = Lists.route) {
            ListsScreen()
        }
         */
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
