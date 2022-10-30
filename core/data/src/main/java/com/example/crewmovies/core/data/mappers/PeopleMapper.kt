package com.example.crewmovies.core.data.mappers

import com.example.crewmovies.core.data.models.KnownForDataModel
import com.example.crewmovies.core.data.models.PeopleResultDataModel
import com.example.crewmovies.core.domain.models.KnownForModel
import com.example.crewmovies.core.domain.models.PeopleResultModel
import javax.inject.Inject

//class PeopleMapper  @Inject constructor(private val knownForMapper: dagger.Lazy<KnownForMapper>) {
class PeopleMapper @Inject constructor() {
    fun toPeopleModel(peopleModelServer: PeopleResultDataModel): PeopleResultModel {
        return PeopleResultModel(
            peopleModelServer.id ?: null,
            peopleModelServer.name ?: "",
            peopleModelServer.popularity ?: null,
            peopleModelServer.profilePath ?: "",
            peopleModelServer.knownForDepartment ?: "",
            //knownForMapper.get().toKnownForModelList(peopleModelServer.knownFor)
            toKnownForModelList(peopleModelServer.knownFor)
        )
    }
    fun toPeopleModelList(peopleModelServer: ArrayList<PeopleResultDataModel>): ArrayList<PeopleResultModel> {
        if (peopleModelServer.isEmpty())
            return arrayListOf()
        return peopleModelServer.map { toPeopleModel(it) } as ArrayList<PeopleResultModel>
    }

    private fun toKnownForModel(knownForModelServer: KnownForDataModel): KnownForModel {
        return KnownForModel(
            knownForModelServer.id ?: null,
            knownForModelServer.originalTitle ?: "",
            knownForModelServer.title ?: "",
            knownForModelServer.overview ?: "",
            knownForModelServer.posterPath ?: "",
            knownForModelServer.releaseDate ?: "",
            knownForModelServer.video ?: null,
            knownForModelServer.voteAverage ?: null

        )
    }

    private fun toKnownForModelList(knownForModelListServer: ArrayList<KnownForDataModel>): ArrayList<KnownForModel> {
        if(knownForModelListServer.isEmpty())
            return arrayListOf()
        return knownForModelListServer.map { toKnownForModel(it) } as ArrayList<KnownForModel>
    }
}