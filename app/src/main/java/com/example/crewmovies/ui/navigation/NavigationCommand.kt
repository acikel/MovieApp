package com.example.crewmovies.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val name : String
    val arguments: List<NamedNavArgument>
    val route : String
    val destination : String
}