package com.example.crewmovies.core.data.models

import com.example.crewmovies.core.data.db.model.PersonEntity
import com.google.gson.annotations.SerializedName

/*
Data Model class for following json (converted with a json to kotlin converter with GSON SerializedName Mapping, online online via https://json2kt.com/)
{
    "page": 1,
    "results": [
        {
            "adult": false,
            "gender": 2,
            "id": 488,
            "known_for": [
                {
                    "adult": false,
                    "backdrop_path": "/8gdIKyQ587Gdo4XCc99usA1eyA7.jpg",
                    "genre_ids": [
                        12,
                        878
                    ],
                    "id": 329,
                    "media_type": "movie",
                    "original_language": "en",
                    "original_title": "Jurassic Park",
                    "overview": "A wealthy entrepreneur secretly creates a theme park featuring living dinosaurs drawn from prehistoric DNA. Before opening day, he invites a team of experts and his two eager grandchildren to experience the park and help calm anxious investors. However, the park is anything but amusing as the security systems go off-line and the dinosaurs escape.",
                    "poster_path": "/b1xCNnyrPebIc7EWNZIa6jhb1Ww.jpg",
                    "release_date": "1993-06-11",
                    "title": "Jurassic Park",
                    "video": false,
                    "vote_average": 7.9,
                    "vote_count": 14043
                },
                {
                    "adult": false,
                    "backdrop_path": "/3E5pvJLNEz5WmYWl6GfFW8hEVV3.jpg",
                    "genre_ids": [
                        18,
                        36,
                        10752
                    ],
                    "id": 857,
                    "media_type": "movie",
                    "original_language": "en",
                    "original_title": "Saving Private Ryan",
                    "overview": "As U.S. troops storm the beaches of Normandy, three brothers lie dead on the battlefield, with a fourth trapped behind enemy lines. Ranger captain John Miller and seven men are tasked with penetrating German-held territory and bringing the boy home.",
                    "poster_path": "/1wY4psJ5NVEhCuOYROwLH2XExM2.jpg",
                    "release_date": "1998-07-24",
                    "title": "Saving Private Ryan",
                    "video": false,
                    "vote_average": 8.2,
                    "vote_count": 13538
                },
                {
                    "adult": false,
                    "backdrop_path": "/zb6fM1CX41D9rF9hdgclu0peUmy.jpg",
                    "genre_ids": [
                        18,
                        36,
                        10752
                    ],
                    "id": 424,
                    "media_type": "movie",
                    "original_language": "en",
                    "original_title": "Schindler's List",
                    "overview": "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
                    "poster_path": "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
                    "release_date": "1993-12-15",
                    "title": "Schindler's List",
                    "video": false,
                    "vote_average": 8.6,
                    "vote_count": 13358
                }
            ],
            "known_for_department": "Directing",
            "name": "Steven Spielberg",
            "popularity": 27.804,
            "profile_path": "/tZxcg19YQ3e8fJ0pOs7hjlnmmr6.jpg"
        }
    ],
    "total_pages": 1,
    "total_results": 1
}

 */
data class PeopleResponseDataModel (
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<PeopleResultDataModel> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
        )

data class PeopleResultDataModel (

    @SerializedName("adult"                ) var adult              : Boolean?            = null,
    @SerializedName("gender"               ) var gender             : Int?                = null,
    @SerializedName("id"                   ) var id                 : Int?                = null,
    @SerializedName("known_for"            ) var knownFor           : ArrayList<KnownForDataModel> = arrayListOf(),
    @SerializedName("known_for_department" ) var knownForDepartment : String?             = null,
    @SerializedName("name"                 ) var name               : String?             = null,
    @SerializedName("popularity"           ) var popularity         : Double?             = null,
    @SerializedName("profile_path"         ) var profilePath        : String?             = null

)

data class KnownForDataModel (

    @SerializedName("adult"             ) var adult            : Boolean?       = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("genre_ids"         ) var genreIds         : ArrayList<Int> = arrayListOf(),
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("media_type"        ) var mediaType        : String?        = null,
    @SerializedName("original_language" ) var originalLanguage : String?        = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("video"             ) var video            : Boolean?       = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?           = null

)



