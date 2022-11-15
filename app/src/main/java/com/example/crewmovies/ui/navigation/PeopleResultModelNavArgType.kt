package com.example.crewmovies.ui.navigation

import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.google.gson.Gson

//THIS IS AN ANTIPATTERN (passing complex elements as arguments should be avoided)
class PeopleResultModelNavArgType : JsonNavType<PeopleResultModel>() {
    override fun fromJsonParse(value: String): PeopleResultModel = Gson().fromJson(value.replace("$$$", "/"), PeopleResultModel::class.java) //replace is need as / is replaced by $$$ before as the navigation graüh cant find the destination if the json contains a /

    override fun PeopleResultModel.getJsonParse(): String = Gson().toJson(this)

}

fun PeopleResultModel.getJsonParse(): String = Gson().toJson(this)
    .toString().replace("/", "$$$") //this replacement is needed because if the json contains a / the navigation graph cant resolve the destination

