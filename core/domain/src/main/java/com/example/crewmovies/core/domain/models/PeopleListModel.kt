package com.example.crewmovies.core.domain.models

import android.media.Image
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

data class PeopleListModel (
        var id: Int? = null,
        var name: String? = null,
        var image : String? = null
)