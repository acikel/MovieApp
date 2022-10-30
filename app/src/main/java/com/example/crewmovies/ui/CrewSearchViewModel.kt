package com.example.crewmovies.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.usecases.GetPeopleDetailsByNameUseCase
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CrewSearchViewModel @Inject constructor(
    private val getProfilePictureUseCase : GetProfilePictureByUrlUseCase,
    private val getPeopleDetailsByNameUseCase : GetPeopleDetailsByNameUseCase,
): ViewModel() {

    var peopleSearchName by mutableStateOf("")
        private set

    private var _uiPeopleSearchListState = MutableStateFlow(ArrayList<PeopleResultModel>(arrayListOf()))
    val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>> = _uiPeopleSearchListState

    fun getPainterFromUrl(urlEnd : String) : String {
        return getProfilePictureUseCase(urlEnd)
    }

    fun fillListWithSearchResult() {
        _uiPeopleSearchListState = getPeopleDetailsByNameUseCase() as MutableStateFlow<ArrayList<PeopleResultModel>>
    }
}