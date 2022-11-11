package com.example.crewmovies.core.data.repositories
import com.example.crewmovies.core.data.apiservice.MovieService
import com.example.crewmovies.core.data.mappers.PeopleMapper
import com.example.crewmovies.core.data.models.PeopleResponseDataModel
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.net.URLEncoder
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor (
    private val movieService: MovieService,
    //private val peopleMapper: dagger.Lazy<PeopleMapper>
    private val peopleMapper: PeopleMapper
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

    override fun getPeopleDetailsByName(peopleSearchName : String): Flow<ArrayList<PeopleResultModel>> {
        //URL encoded string for the required for the get request
        val queryPeopleSearch: String = URLEncoder.encode(peopleSearchName, Charsets.UTF_8.name())
        return  flow {
            emit( peopleMapper.toPeopleModelList(movieService.getPeopleDetailsByName(api_key, queryPeopleSearch).results))
        }.flowOn(Dispatchers.IO)

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
    }


    override fun getPopularPeople(): Flow<ArrayList<PeopleResultModel>> {
        var popularPeople : PeopleResponseDataModel = PeopleResponseDataModel()

        return  flow {
            movieService.getPopularPeople(api_key).collect {
                popularPeople = it
            }

            emit( peopleMapper.toPeopleModelList(popularPeople.results))
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        var api_key = "7742db1161b96d5da49d8268ec472eae"
        //TODO von eingabr in diese form bringen und übergeben
        //var personName = "Spielberg"
        //var personName = "Steven"
        //var profileImageUrl = "https://image.tmdb.org/t/p/original"
    }
}