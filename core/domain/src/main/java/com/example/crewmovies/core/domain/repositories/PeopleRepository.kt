package com.example.crewmovies.core.domain.repositories

import com.example.crewmovies.core.domain.models.PeopleResultModel
import io.reactivex.Single

interface PeopleRepository {
        fun getPeopleDetailsByName(): Single<ArrayList<PeopleResultModel>>
}