package com.example.crewmovies.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crewmovies.core.domain.models.PeopleListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
) : ViewModel() {
    var isDialogOpen by mutableStateOf(false) // Initially dialog is closed
        private set

    var listName by mutableStateOf("")
        private set

    //TODO list typ an database anpassen
    private var _uiPeopleListsState: MutableStateFlow<ArrayList<PeopleListModel>> =
        MutableStateFlow(ArrayList(mutableListOf()))
    val uiPeopleListsState: StateFlow<ArrayList<PeopleListModel>> = _uiPeopleListsState

    fun openAddListDialogClearName() {
        listName = ""
        isDialogOpen = true;
    }

    fun closeAddListDialog() {
        isDialogOpen = false;
    }

    fun updateListName(value: String) {
        listName = value
    }

    fun createNewListAndCloseDialog() {
        //TODO create new list and add new list to database
        if (listName.isNullOrEmpty()) {
            return
        }

        try {
            //val tmp: ArrayList<PeopleListModel> = arrayListOf()
            //tmp.addAll(_uiPeopleListsState.value)
            //tmp.add(PeopleListModel(name = listName))
            //_uiPeopleListsState.value = tmp

            _uiPeopleListsState.value.add(PeopleListModel(name = listName))
            closeAddListDialog()
        }

        //peopleRepo.getPeopleDetailsByName()
        catch (ex: Exception) {
        }
    }

    fun getApplicationContext(): Context {
        return applicationContext
    }
}
