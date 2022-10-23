package com.example.crewmovies.core.domain.usecases

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPeopleDetailsByNameUseCase @Inject constructor(val peopleRepo: PeopleRepository):
    SingleUseCase<ArrayList<PeopleResultModel>>  {
    override fun execute(): Single<ArrayList<PeopleResultModel>> {
        return peopleRepo.getPeopleDetailsByName()

    }
}