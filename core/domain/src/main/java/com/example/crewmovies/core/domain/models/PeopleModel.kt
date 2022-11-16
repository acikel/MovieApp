package com.example.crewmovies.core.domain.models

import android.net.Uri
import com.google.gson.Gson

data class PeopleResultModel (
    var id: Int? = null,
    var name: String? = null,
    var popularity: Double? = null,
    var profilePicturePath: String? = null,
    var knownForDepartment: String? = null,
    var knownFor : ArrayList<KnownForModel> = arrayListOf()
    ){
    //Method to parse this onject into a json object to be able to pass it as an arguement in the navigation graph (see CrewNavHost.kt)
    //override fun toString(): String = Uri.encode(Gson().toJson(this))
}


class KnownForModel (
    var id: Int? = null,
    var original_title: String? = null,
    var title : String? = null,
    var overview : String? = null,
    var posterPath : String? = null,
    var releaseDate : String? = null,
    var video : Boolean? = null,
    var voteAverag : Double? = null
){
    //override fun toString(): String = Uri.encode(Gson().toJson(this))
}
