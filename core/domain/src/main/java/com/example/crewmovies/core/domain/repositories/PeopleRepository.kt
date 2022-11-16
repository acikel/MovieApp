package com.example.crewmovies.core.domain.repositories

import com.example.crewmovies.core.domain.models.Person
import com.example.crewmovies.core.domain.models.PeopleResultModel
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
        suspend fun getPeopleDetailsByName(peopleSearchName : String): ArrayList<PeopleResultModel>
        fun getPopularPeople(): Flow<ArrayList<PeopleResultModel>>

        fun insertPerson(person: Person)

        fun deletePerson(name: String)

        fun findPersonByIdStream(id: Int) : Flow<Person>

        fun getAllPeopleStream(): Flow<List<Person>>

        fun findPersonByNameStream(name: String) : Flow<List<Person>>
}