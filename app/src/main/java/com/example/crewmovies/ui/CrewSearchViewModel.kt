package com.example.crewmovies.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import com.example.crewmovies.core.domain.usecases.GetPeopleDetailsByNameUseCase
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewSearchViewModel @Inject constructor(
    private val getProfilePictureUseCase : GetProfilePictureByUrlUseCase,
    private val getPeopleDetailsByNameUseCase : GetPeopleDetailsByNameUseCase,
    val peopleRepo: PeopleRepository
): ViewModel() {

    var peopleSearchName by mutableStateOf("")
        private set

    private var _uiPeopleSearchListState : MutableStateFlow<ArrayList<PeopleResultModel>> = MutableStateFlow(ArrayList(mutableListOf()))
    val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>> = _uiPeopleSearchListState

    /*
    val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>>
        get () = getSearch()

fun getSearch() : StateFlow<ArrayList<PeopleResultModel>>{
    println("size: " + _uiPeopleSearchListState.value.size)
    return _uiPeopleSearchListState
}
     */

    /*
    val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>>
        get() =
            if(_uiPeopleSearchListState.value!=null)
                _uiPeopleSearchListState as StateFlow<ArrayList<PeopleResultModel>>
            else
                MutableStateFlow(ArrayList(arrayListOf()))
    */

            fun getPainterFromUrl(urlEnd : String) : String {
                return getProfilePictureUseCase(urlEnd)
            }

            fun fillListWithSearchResult() {
                //_uiPeopleSearchListState = getPeopleDetailsByNameUseCase() as MutableStateFlow<ArrayList<PeopleResultModel>>
                /*
                _uiPeopleSearchListState = getPeopleDetailsByNameUseCase().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = null
                ) as MutableStateFlow<ArrayList<PeopleResultModel>>
                 */
                println("size: fillListWithSearchResult")

                /*

                    _uiPeopleSearchListState = getPeopleDetailsByNameUseCase().stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(),
                        initialValue = ArrayList(arrayListOf())
                    )
                 */
                //viewModelScope.launch {
                    //_uiPeopleSearchListState.value = getPeopleDetailsByNameUseCase()
                //}

                viewModelScope.launch {
                    try {
                        println("size: fifth:")
                        //var tmpState = _uiPeopleSearchListState.value
                        //tmpState = ArrayList(mutableListOf())
                        _uiPeopleSearchListState.value = ArrayList(mutableListOf())
                        val response = getPeopleDetailsByNameUseCase(peopleSearchName)
                        println("size: sixth:")
                         response.collect{ list -> _uiPeopleSearchListState.value = list}
                    }

                    //peopleRepo.getPeopleDetailsByName()
                        catch (ex: Exception) {
                            println("size: forth: $ex")
                        }
                }
                println("size: second:" + _uiPeopleSearchListState.value.size)
            }

    fun updateTextSearch(value : String) {
        peopleSearchName = value
    }

}


