package com.example.crewmovies.core.domain.usecases

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//Use cases represent business logic and can contain repository access to database or networks.
//They usually sum up a specific way (business logic) to do a task (example: converting and calculating data
// to create gps data by using latitude and longitude from network or database). Synchronizing data from
// network to database is not a use case it can be done in the repository itself as it docent matter what
// type of specific data I get.
// This logic is summed up into a use case to be used by multiple view-models with no need to reimplement it.
// Use cases which only make function calls like this one (usually in small projects) can be omitted and
// the repository can be accessed directly. But for the sake of example the use case with just a
// function call is still included to show case use cases and the domain layer.
class GetPeopleDetailsByNameUseCase @Inject constructor(val peopleRepo: PeopleRepository) {
    /*
    operator fun invoke(peopleSearchName : String): ArrayList<PeopleResultModel> =
        peopleRepo.getPeopleDetailsByName( peopleSearchName )
     */
    suspend fun run(peopleSearchName : String): ArrayList<PeopleResultModel> =
        peopleRepo.getPeopleDetailsByName( peopleSearchName )
}