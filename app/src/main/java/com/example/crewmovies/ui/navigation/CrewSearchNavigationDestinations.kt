package com.example.crewmovies.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import com.example.crewmovies.ui.navigation.NavigationCommand

object CrewSearchNavigationDestinations {
    //The root destination is used for nested navigation graphs see: https://hitherejoe.medium.com/nested-navigation-graphs-in-jetpack-compose-dc0ada1d4726
    //With a nested navigation graph (here implented in CrewNavHost) navigation can be navigated by features which are logically grouped elements of the application,
    //like Tabs, Details, Dialogs, and so on.
    //The root object is like a identifier of the feature and is assigned to the route of the navigation is the navigation graph (see CrewNavHost)
    //The root object only groups the destinations together that belong to one feature. The group components are the actual destinations and are defined here as further objects.
    val root = object : NavigationCommand {

        override val name = "TabNavigation"
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "tab_navigation_route"
        override val destination = "tab_navigation_destination"
    }

    val Home = object : NavigationCommandWithIcon {

        override val icon = Icons.Default.Home
        override val name = "Home"
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "home_route"
        override val destination = "home_destination"

    }

    val Lists = object : NavigationCommandWithIcon {

        override val icon = Icons.Default.Menu
        override val name = "Lists"
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "lists_route"
        override val destination = "lists_destination"

    }
}

interface NavigationCommandWithIcon : NavigationCommand{
    val icon : ImageVector
}
/*
object NewListDialog : CrewDestination {
    override val icon = null
    override val name = "NewListsDialog"
    override val route = "new_list_dialog_route"
    override val destination = "new_list_dialog_destination"
}
 */

// Screens to be displayed in the top RallyTabRow
val crewTabRowScreens = listOf(CrewSearchNavigationDestinations.Home, CrewSearchNavigationDestinations.Lists)


