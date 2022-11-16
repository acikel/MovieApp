package com.example.crewmovies.core.data.repositories
import com.example.crewmovies.core.data.apiservice.MovieService
import com.example.crewmovies.core.data.db.dao.PersonDao
import com.example.crewmovies.core.data.db.model.PersonEntity
//import com.example.crewmovies.core.data.db.model.asEntity
//import com.example.crewmovies.core.data.db.model.asExternalModel
import com.example.crewmovies.core.data.mappers.asEntity
import com.example.crewmovies.core.data.mappers.asExternalModel
//import com.example.crewmovies.core.data.mappers.PeopleMapper
import com.example.crewmovies.core.data.mappers.asPeopleResultModel
import com.example.crewmovies.core.domain.models.Person
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

//The data access to api and database is done in a repository. They can be put into the same repository.
//Business logic is done with a use case by accessing a repository (or even without). A synchronization
//is part of a repository instead of a use case.
class PeopleRepositoryImpl @Inject constructor (
    private val movieService: MovieService,
    //private val peopleMapper: dagger.Lazy<PeopleMapper>
    //private val peopleMapper: PeopleMapper
    private val personDao: PersonDao
    ) : PeopleRepository {

    /*
    override fun getPeopleDetailsByName(): Flow<ArrayList<PeopleResultModel>> {
        println("size: Repo")
        return  movieService.getPeopleDetailsByName(api_key, personName)
            .map {
                //peopleMapper.get().toPeopleModelList(it.results)
                peopleMapper.toPeopleModelList(it.results)
            }
    }
     */

    override suspend fun getPeopleDetailsByName(peopleSearchName : String): ArrayList<PeopleResultModel> {

        /*
        //URL encoded string for the required for the get request
        val queryPeopleSearch: String = URLEncoder.encode(peopleSearchName, Charsets.UTF_8.name())
        return  flow {
            emit( peopleMapper.toPeopleModelList(movieService.getPeopleDetailsByName(api_key, queryPeopleSearch).results))
        }.flowOn(Dispatchers.IO)
         */

        /*
         return flow {
            flowPeopleResultDataModel.collect{list -> list.map {
                    emit(peopleMapper.toPeopleModelList(it.results))
            }}
        }.flowOn(Dispatchers.IO)
         */
         /*
        return flow {
            // exectute API call and map to UI object
            val flowPeopleResultDataModel = movieService.getPeopleDetailsByName(api_key, personName)
                .map { peopleResultModel ->
                    peopleMapper.toPeopleModelList(peopleResultModel.results)
                }


            // Emit the list to the stream
            emit(flowPeopleResultDataModel)
        }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow
         */
        //instead of collecting a flow inside a flow use following mapping to map PeopleResponseDataModel to PeopleResultModel and return it:
        //URL encoded string for the required for the get request
        val queryPeopleSearch: String = URLEncoder.encode(peopleSearchName, Charsets.UTF_8.name())

        /*
        return flow{
            emit (movieService.getPeopleDetailsByName(api_key, queryPeopleSearch).results.map{it.asPeopleResultModel()} as ArrayList<PeopleResultModel>)
        }
         */
        return movieService.getPeopleDetailsByName(api_key, queryPeopleSearch).results.map{it.asPeopleResultModel()} as ArrayList<PeopleResultModel>

    }


    override fun getPopularPeople(): Flow<ArrayList<PeopleResultModel>> {
        /*
        var popularPeople : PeopleResponseDataModel = PeopleResponseDataModel()

        return  flow {
            movieService.getPopularPeople(api_key).collect {
                popularPeople = it
            }

            emit( peopleMapper.toPeopleModelList(popularPeople.results))
        }.flowOn(Dispatchers.IO)
         */

        //instead of collecting a flow inside a flow use following mapping to map PeopleResponseDataModel to PeopleResultModel and return it:
        /*
        return movieService.getPopularPeople(api_key).map{
            peopleMapper.toPeopleModelList(it.results)}
         */
        //instead of using usual mapper use extension function:
        return movieService.getPopularPeople(api_key).map{
            it.results.map { it2 -> it2.asPeopleResultModel() } as ArrayList<PeopleResultModel>}
    }

    companion object {
        var api_key = "7742db1161b96d5da49d8268ec472eae"
        //TODO von eingabr in diese form bringen und übergeben
        //var personName = "Spielberg"
        //var personName = "Steven"
        //var profileImageUrl = "https://image.tmdb.org/t/p/original"
    }


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