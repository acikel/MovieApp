package com.example.crewmovies.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import com.example.crewmovies.ui.navigation.DefaultDestinations.Default

class NavigationManager {
    var commands = MutableStateFlow(Default)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}