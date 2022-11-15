package com.example.crewmovies.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object CrewSearchDetailsDestinations {
   /*
    private val person = "person"
    val route = "crew_search_details_$person"
    val arguments = listOf(
        navArgument(person) { type = NavType.<PeopleResultModel> }
    )
    */

    //THIS IS AN ANTIPATTERN (passing complex elements as arguments should be avoided)
    /*
   private const val PERSON = "person"
    val destination = "crew_search_details/{$PERSON}"
    val arguments = listOf(
        navArgument(PERSON) { type = NavType.StringType
        })
    /*
    val arguments = listOf(
        navArgument(PERSON) { type = PeopleResultModelNavArgType()
        })
     */

    fun crewSearchDetailsPerson(
        person: String? = null
    ) = object : NavigationCommand {

        override val name = "Details"
        override val arguments = CrewSearchDetailsDestinations.arguments
        override val route  = "crew_search_details_route"
        override val destination = "crew_search_details/$person"
    }
     */
    private const val PERSON_ID = "person_id"
    val destination = "crew_search_details/{$PERSON_ID}"
    val arguments = listOf(
        navArgument(PERSON_ID) { type = NavType.StringType }
    )
    /*
    val arguments = listOf(
        navArgument(PERSON) { type = PeopleResultModelNavArgType()
        })
     */


    //Pass multiple arguments:
    //private const val PERSON_NAME = "person_name"
    //private const val PERSON_PICTURE_PATH = "person_picture_path"
    //val destination = "crew_search_details/{$PERSON_NAME}/{$PERSON_PICTURE_PATH}"
    /*
    val arguments = listOf(
        navArgument(PERSON_NAME) { type = NavType.StringType },
        navArgument(PERSON_PICTURE_PATH) { type = NavType.StringType },
    )
     */


    fun crewSearchDetailsPerson(
        personId: String? = null
        //Pass multiple arguments:
        //personName: String? = null,
        //personPicturePath : String? = null
    ) = object : NavigationCommand {

        override val name = "Details"
        override val arguments = CrewSearchDetailsDestinations.arguments
        override val route  = "crew_search_details_route"
        ////Pass multiple arguments:
        //override val destination = "crew_search_details/$personName/$personPicturePath"
        override val destination = "crew_search_details/$personId"
    }
}