package com.example.crewmovies.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

/*As per the dialog documentation:
                This is suitable only when this dialog represents a separate screen in your app that needs its own lifecycle and saved state, independent of any other destination in your navigation graph. For use cases such as AlertDialog, you should use those APIs directly in the composable destination that wants to show that dialog.

                So you shouldn't be using a dialog destination at all: a dialog destination is specifically and only for providing the content lambda of a regular Dialog. What your code is actually doing is creating an empty Dialog (i.e., you don't emit any composables in the lambda you pass to dialog, then creating another AlertDialog stacked on top of that empty dialog. That's not what you want to be doing.

                Instead, you should be following the AlertDialog docs and directly creating the AlertDialog where you want it to be used, setting your own boolean for when it should be shown/hidden.
                 Quelle: https://stackoverflow.com/questions/72478905/how-to-integrate-alertdialog-with-navigation-component-in-jetpack-compose
                 https://stackoverflow.com/a/72479997
                 https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#AlertDialog(kotlin.Function0,kotlin.Function0,androidx.compose.ui.Modifier,kotlin.Function0,kotlin.Function0,androidx.compose.ui.graphics.Shape,androidx.compose.ui.graphics.Color,androidx.compose.ui.graphics.Color,androidx.compose.ui.window.DialogProperties)
*/
/*
object DialogDestinations {
    val root = object : NavigationCommand {

        override val name = "Dialogs"
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "dialogs_route"
        override val destination = "dialogs_destination"
    }

    val AddList = object : NavigationCommand {

        override val name = "DialogAddList"
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "dialog_add_list_route"
        override val destination = "dialog_add_list_destination"

    }

    /*
    private const val PERSON = "person"
    val destination = "crew_search_details/{$PERSON}"
    val arguments = listOf(
        navArgument(PERSON) { type = PeopleResultModelNavArgType()
        })

    fun crewSearchDetailsPerson(
        person: PeopleResultModelNavArgType? = null
    ) = object : NavigationCommand {

        override val name = "Details"
        override val arguments = CrewSearchDetailsDestinations.arguments
        override val route  = "crew_search_details_route"
        override val destination = "crew_search_details$person"
    }
     */
}
 */