package com.example.crewmovies.core.domain.usecases

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePictureByUrlUseCase  @Inject constructor(val peopleRepo: PeopleRepository) {
    operator fun invoke(urlEnd : String): String =
        peopleRepo.getProfilePictureByUrl(urlEnd)
}