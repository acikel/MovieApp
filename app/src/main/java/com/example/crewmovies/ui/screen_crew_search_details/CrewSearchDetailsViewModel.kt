package com.example.crewmovies.ui.screen_crew_search_details

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CrewSearchDetailsViewModel @Inject constructor(
    private val getProfilePictureUseCase: GetProfilePictureByUrlUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var scrollState = mutableStateOf(ScrollState)
        private set

    var person : MutableState<PeopleResultModel> = mutableStateOf(PeopleResultModel())
    init{
        //val person = savedStateHandle.get<PeopleResultModel>("person")
    }
    fun updateScrollState(value : ScrollState){
        //scrollState = value
    }

    fun getPainterFromUrl(urlEnd: String): String {
        return getProfilePictureUseCase(urlEnd)
    }
}