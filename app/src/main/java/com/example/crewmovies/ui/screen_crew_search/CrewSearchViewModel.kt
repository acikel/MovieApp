package com.example.crewmovies.ui.screen_crew_search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import com.example.crewmovies.core.domain.usecases.GetPeopleDetailsByNameUseCase
import com.example.crewmovies.core.domain.usecases.GetPopularPeopleUseCase
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import com.example.crewmovies.ui.navigation.CrewSearchDetailsDestinations
import com.example.crewmovies.ui.navigation.NavigationManager
import com.example.crewmovies.ui.navigation.PeopleResultModelNavArgType
import com.example.crewmovies.ui.navigation.getJsonParse
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrewSearchViewModel @Inject constructor(
    private val getProfilePictureUseCase: GetProfilePictureByUrlUseCase,
    private val getPeopleDetailsByNameUseCase: GetPeopleDetailsByNameUseCase,
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase,
    private val navigationManager: NavigationManager,
    val peopleRepo: PeopleRepository
) : ViewModel() {

    var peopleSearchName by mutableStateOf("")
        private set

    private var _uiPeopleSearchListState: MutableStateFlow<ArrayList<PeopleResultModel>> =
        MutableStateFlow(ArrayList(mutableListOf()))
    val uiPeopleSearchListState: StateFlow<ArrayList<PeopleResultModel>> = _uiPeopleSearchListState

    private var popularPeopleList : ArrayList<PeopleResultModel> = arrayListOf()
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

    init {
        showPopularPeople()
    }

    private fun showPopularPeople(){
        if(popularPeopleList.isEmpty()) {
            viewModelScope.launch {
                try {
                    _uiPeopleSearchListState.value = ArrayList(mutableListOf())
                    val response = getPopularPeopleUseCase()
                    response.collect { list ->
                        _uiPeopleSearchListState.value = list
                        popularPeopleList = list.clone() as ArrayList<PeopleResultModel>
                    }
                } catch (ex: Exception) {
                    // println("size: forth: $ex")
                }
            }
        }
        else {
            //println("i was already fetched and return a copy")
            _uiPeopleSearchListState.value = popularPeopleList.clone() as ArrayList<PeopleResultModel>
        }
    }

    private fun showSearchResultPeople(){
        viewModelScope.launch {
            try {
                //println("size: fifth:")
                //var tmpState = _uiPeopleSearchListState.value
                //tmpState = ArrayList(mutableListOf())
                _uiPeopleSearchListState.value = ArrayList(mutableListOf())
                val response = getPeopleDetailsByNameUseCase(peopleSearchName)
                //println("size: sixth:")
                response.collect { list -> _uiPeopleSearchListState.value = list }
            }

            //peopleRepo.getPeopleDetailsByName()
            catch (ex: Exception) {
                // println("size: forth: $ex")
            }
        }
    }


    fun getPainterFromUrl(urlEnd: String): String {
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

        if(peopleSearchName.isNullOrEmpty()){
            showPopularPeople()
        }else{
            showSearchResultPeople()
        }
        //println("size: second:" + _uiPeopleSearchListState.value.size)
    }

    fun updateTextSearch(value: String) {
        peopleSearchName = value
    }

    fun navigateToDetailsScreen(person : PeopleResultModel){
        val navCom = CrewSearchDetailsDestinations.crewSearchDetailsPerson(person.getJsonParse())
        //println("Destination: ${navCom.destination}")
        navigationManager.navigate(navCom)

    }
}


