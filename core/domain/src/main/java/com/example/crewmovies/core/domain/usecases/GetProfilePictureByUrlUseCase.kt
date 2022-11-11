package com.example.crewmovies.core.domain.usecases

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilePictureByUrlUseCase  @Inject constructor(val peopleRepo: PeopleRepository) {
    //information is joined up in use case and not in repository as this information dosent need to be fetched from repository
    operator fun invoke(urlEnd : String): String =
        "$profileImageUrl$urlEnd"

    companion object {
        var profileImageUrl = "https://image.tmdb.org/t/p/original"
    }
}