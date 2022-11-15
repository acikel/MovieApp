package com.example.crewmovies.ui.screen_crew_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import com.example.crewmovies.core.domain.usecases.GetPeopleDetailsByNameUseCase
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.crewmovies.core.common.Result

@HiltViewModel
class CrewSearchViewModelWithResult @Inject constructor(
    private val getProfilePictureUseCase : GetProfilePictureByUrlUseCase,
    private val getPeopleDetailsByNameUseCase : GetPeopleDetailsByNameUseCase,
    val peopleRepo: PeopleRepository
): ViewModel() {

    var peopleSearchName by mutableStateOf("")
        private set

    private var _uiPeopleSearchListState : MutableStateFlow<Result> = MutableStateFlow(Result.Empty)
    //val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>> = _uiPeopleSearchListState
    val uiPeopleSearchListState: StateFlow<Result>
        get () = getSearch()

    fun getSearch() : StateFlow<Result>{
        //println("size: " + _uiPeopleSearchListState.value)
        return _uiPeopleSearchListState
    }
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
        //println("size: fillListWithSearchResult")

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
            println("size: fifth:")
            //var tmpState = _uiPeopleSearchListState.value
            //tmpState = ArrayList(mutableListOf())
            _uiPeopleSearchListState.value = Result.Loading
            getPeopleDetailsByNameUseCase(peopleSearchName)
            //peopleRepo.getPeopleDetailsByName()
                .catch { e ->
                    //println("size: forth: $e")
                    _uiPeopleSearchListState.value = Result.Failure(e)
                }.collect { data ->
                    //tmpState = data
                    _uiPeopleSearchListState.value = Result.Success(data)
                    println("size: third: $data.toString()")
                }
        }
        println("size: second:" + _uiPeopleSearchListState.value)
    }

    fun updateTextSearch(value : String) {
        peopleSearchName = value
    }

}


