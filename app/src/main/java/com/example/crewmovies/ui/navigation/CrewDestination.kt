package com.example.crewmovies.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

interface CrewDestination {
    val icon : ImageVector
    val name : String
    val route : String
    val destination : String
}

object Home : CrewDestination {
    override val icon = Icons.Default.Home
    override val name = "Home"
    override val route = "overview_route"
    override val destination = "overview_destination"
}

object Lists : CrewDestination {
    override val icon = Icons.Default.Menu
    override val name = "Lists"
    override val route = "lists_route"
    override val destination = "lists_destination"
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
val crewTabRowScreens = listOf(Home, Lists)


