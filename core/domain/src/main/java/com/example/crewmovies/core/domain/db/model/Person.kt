package com.example.crewmovies.core.domain.db.model

data class Person(
    var id: Int = 0,
    var idNetwork: Int? = 0,
    var name: String? = "",
    var popularity: Double? = 0.0,
    var profilePicturePath: String? = null,
    var knownForDepartment: String? = null,
    var bio: String? = ""
    //var knownFor : ArrayList<KnownForModel> = arrayListOf()
)

