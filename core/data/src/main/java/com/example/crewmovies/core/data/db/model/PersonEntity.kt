package com.example.crewmovies.core.data.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.crewmovies.core.domain.db.model.Person
import com.example.crewmovies.core.domain.models.KnownForModel

@Entity(tableName = "person")
class PersonEntity{
    @PrimaryKey (autoGenerate = true)
    //@PrimaryKey
    @NonNull
    @ColumnInfo(name = "personId")
    var id: Int = 0
    var idNetwork : Int? = null
    var name: String? = null
    var popularity: Double? = null
    @ColumnInfo(name = "profile_picture_path")
    var profilePicturePath: String? = null
    var knownForDepartment: String? = null
    var bio: String? = ""
    //var knownFor : ArrayList<KnownForModel> = arrayListOf()

    constructor() {}

    constructor(id: Int, idNetwork: Int, name: String, popularity: Double, profilePicturePath: String?, knownForDepartment: String, bio: String) {
        this.id = id
        this.idNetwork = idNetwork
        this.name = name
        this.popularity = popularity
        this.profilePicturePath = profilePicturePath
        this. knownForDepartment = knownForDepartment
        this.bio = bio
    }

    constructor(idNetwork: Int?, name: String?, popularity: Double?, profilePicturePath: String?, knownForDepartment: String?, bio: String?) {
        this.idNetwork = idNetwork
        this.name = name
        this.popularity = popularity
        this.profilePicturePath = profilePicturePath
        this. knownForDepartment = knownForDepartment
        this.bio = bio
    }
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