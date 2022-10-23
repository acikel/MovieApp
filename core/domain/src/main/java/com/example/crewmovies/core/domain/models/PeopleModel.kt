package com.example.crewmovies.core.domain.models

data class PeopleResultModel (
    var id: Int? = null,
    var name: String? = null,
    var popularity: Double? = null,
    var profilePicturePath: String? = null,
    var knownForDepartment: String? = null,
    var knownFor : ArrayList<KnownForModel> = arrayListOf()
    )


class KnownForModel (
    var id: Int? = null,
    var original_title: String? = null,
    var title : String? = null,
    var overview : String? = null,
    var posterPath : String? = null,
    var releaseDate : String? = null,
    var video : Boolean? = null,
    var voteAverag : Double? = null
)
