package com.example.crewmovies.core.data.mappers

import com.example.crewmovies.core.data.db.model.PersonEntity
import com.example.crewmovies.core.data.apiservice.models.KnownForDataModel
import com.example.crewmovies.core.data.apiservice.models.PeopleResultDataModel
import com.example.crewmovies.core.domain.models.KnownForModel
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.models.Person
import javax.inject.Inject

//class PeopleMapper  @Inject constructor(private val knownForMapper: dagger.Lazy<KnownForMapper>) {
/*
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
 */


//Better to write as extension function then mapper above so there is no need for an extra variable which is then dependency injected it can
//directly be accessed by a function without the need of an object instance instead.
fun PeopleResultDataModel.asPeopleResultModel() : PeopleResultModel =
    PeopleResultModel(
        this.id ?: null,
        this.name ?: "",
        this.popularity ?: null,
        this.profilePath ?: "",
        this.knownForDepartment ?: "",
        //knownForMapper.get().toKnownForModelList(peopleModelServer.knownFor)
        knownFor = this.knownFor.map { it.asKnownForModel() } as ArrayList<KnownForModel>
    )


fun KnownForDataModel.asKnownForModel() : KnownForModel {
   return KnownForModel(
        this.id ?: null,
        this.originalTitle ?: "",
        this.title ?: "",
        this.overview ?: "",
        this.posterPath ?: "",
        this.releaseDate ?: "",
        this.video ?: null,
        this.voteAverage ?: null

    )
}

fun PersonEntity.asExternalModel() = Person(
    id = id,
    idNetwork = idNetwork,
    name = name,
    popularity = popularity,
    profilePicturePath = profilePicturePath,
    knownForDepartment = knownForDepartment,
    bio = bio
)

fun Person.asEntity() = PersonEntity(
    idNetwork = idNetwork,
    name = name,
    popularity = popularity,
    profilePicturePath = profilePicturePath,
    knownForDepartment = knownForDepartment,
    bio = bio
)

fun PeopleResultDataModel.asEntity() = PersonEntity(
    idNetwork = id,
    name = name,
    popularity = popularity,
    profilePicturePath = profilePath,
    knownForDepartment = knownForDepartment,
    bio = ""
)
