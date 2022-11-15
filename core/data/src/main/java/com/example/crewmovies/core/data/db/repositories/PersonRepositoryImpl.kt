package com.example.crewmovies.core.data.db.repositories

import com.example.crewmovies.core.data.db.dao.PersonDao
import com.example.crewmovies.core.data.db.model.PersonEntity
import com.example.crewmovies.core.data.db.model.asEntity
import com.example.crewmovies.core.data.db.model.asExternalModel
import com.example.crewmovies.core.domain.db.model.Person
import com.example.crewmovies.core.domain.db.repositories.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val personDao: PersonDao) :PersonRepository {


    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun insertPerson(person: Person) {
        coroutineScope.launch(Dispatchers.IO) {
            personDao.addPerson(person.asEntity())
        }
    }

    override fun deletePerson(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            personDao.deletePerson(name)
        }
    }

    override fun findPersonByIdStream(id: Int) : Flow<Person> =
        personDao.findPersonById(id).map {
            it.asExternalModel()
        }

    override fun getAllPeopleStream(): Flow<List<Person>> =
            personDao.getAllPeople()
                .map{it.map(PersonEntity::asExternalModel)}

    override fun findPersonByNameStream(name: String) : Flow<List<Person>> =
        personDao.findPersonByName(name)
            .map {
                it.map(PersonEntity::asExternalModel)
            }

    /* See OfflineFirstAuthorsRepository in package com.google.samples.apps.nowinandroid.core.data.repository in nowinandroid
    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.changeListSync(
            versionReader = ChangeListVersions::authorVersion,
            changeListFetcher = { currentVersion ->
                network.getAuthorChangeList(after = currentVersion)
            },
            versionUpdater = { latestVersion ->
                copy(authorVersion = latestVersion)
            },
            modelDeleter = authorDao::deleteAuthors,
            modelUpdater = { changedIds ->
                val networkAuthors = network.getAuthors(ids = changedIds)
                authorDao.upsertAuthors(
                    entities = networkAuthors.map(NetworkAuthor::asEntity)
                )
            }
        )
     */

}