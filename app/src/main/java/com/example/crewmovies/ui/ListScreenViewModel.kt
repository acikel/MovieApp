package com.example.crewmovies.ui

import androidx.lifecycle.ViewModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import com.example.crewmovies.core.domain.usecases.GetPeopleDetailsByNameUseCase
import com.example.crewmovies.core.domain.usecases.GetProfilePictureByUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel @Inject constructor(

): ViewModel() {

}
