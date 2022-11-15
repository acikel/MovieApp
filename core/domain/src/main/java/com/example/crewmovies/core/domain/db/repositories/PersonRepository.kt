package com.example.crewmovies.core.domain.db.repositories


import com.example.crewmovies.core.domain.db.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface PersonRepository {
    fun insertPerson(person: Person)

    fun deletePerson(name: String)

    fun findPersonByIdStream(id: Int) : Flow<Person>

    fun getAllPeopleStream(): Flow<List<Person>>

    fun findPersonByNameStream(name: String) : Flow<List<Person>>
}