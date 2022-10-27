package com.example.crewmovies.core.domain.usecases

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleDetailsByNameUseCase @Inject constructor(val peopleRepo: PeopleRepository) {
    operator fun invoke(): Flow<ArrayList<PeopleResultModel>> =
        peopleRepo.getPeopleDetailsByName()
}