package com.example.crewmovies.core.domain.repositories

import com.example.crewmovies.core.domain.models.PeopleResultModel
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
        fun getPeopleDetailsByName(peopleSearchName : String): Flow<ArrayList<PeopleResultModel>>
        fun getProfilePictureByUrl(urlEnd : String): String
}