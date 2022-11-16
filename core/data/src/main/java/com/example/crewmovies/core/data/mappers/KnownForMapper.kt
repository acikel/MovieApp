package com.example.crewmovies.core.data.mappers

import com.example.crewmovies.core.data.apiservice.models.KnownForDataModel
import com.example.crewmovies.core.domain.models.KnownForModel
import javax.inject.Inject

/*
class KnownForMapper @Inject constructor(){
    fun toKnownForModel(knownForModelServer: KnownForDataModel): KnownForModel {
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

    fun toKnownForModelList(knownForModelListServer: ArrayList<KnownForDataModel>): ArrayList<KnownForModel> {
        if(knownForModelListServer.isEmpty())
            return arrayListOf()
        return knownForModelListServer.map { toKnownForModel(it) } as ArrayList<KnownForModel>
    }
}

 */