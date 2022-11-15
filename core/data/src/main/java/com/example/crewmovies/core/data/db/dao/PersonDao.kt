package com.example.crewmovies.core.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crewmovies.core.data.db.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAllPeople(): Flow<List<PersonEntity>>
    @Query("SELECT * FROM person WHERE name = :customerName")
    fun findPersonByName(customerName: String): Flow<List<PersonEntity>>
    @Query("SELECT * FROM person WHERE personId = :personId")
    fun findPersonById(personId: Int): Flow<PersonEntity>
    @Insert
    suspend fun addPerson(person : PersonEntity)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPeople(people : List<PersonEntity>)
    @Query("DELETE FROM person WHERE name = :name")
    suspend fun deletePerson(name : String)
    @Delete
    suspend fun deletePeople(people : List<PersonEntity>)
    /*
    OR:
    The DAO methods for these types of database operations may also be declared to return an int value indicating the number of rows affected by the transaction, for example:
    @Delete
    fun deleteCustomers(people : List<PersonEntity>): int
     */
    @Update
    suspend fun updatePeople(people : List<PersonEntity>)
}