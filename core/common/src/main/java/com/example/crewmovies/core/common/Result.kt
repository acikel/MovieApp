package com.example.crewmovies.core.common

import com.example.crewmovies.core.domain.models.PeopleResultModel

sealed class Result {
    object Loading : Result()
    class Failure(val msg:Throwable) : Result()
    class Success(val data:List<PeopleResultModel>) : Result()
    object Empty : Result()
}