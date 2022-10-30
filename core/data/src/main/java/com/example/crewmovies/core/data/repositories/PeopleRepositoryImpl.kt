package com.example.crewmovies.core.data.repositories
import com.example.crewmovies.core.data.apiservice.MovieService
import com.example.crewmovies.core.data.mappers.PeopleMapper
import com.example.crewmovies.core.domain.models.PeopleResultModel
import com.example.crewmovies.core.domain.repositories.PeopleRepository
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor (
    private val movieService: MovieService,
    //private val peopleMapper: dagger.Lazy<PeopleMapper>
    private val peopleMapper: PeopleMapper
    ) : PeopleRepository {

    override fun getPeopleDetailsByName(): Flow<ArrayList<PeopleResultModel>> {
        return  movieService.getPeopleDetailsByName(api_key, personName)
            .map {
                //peopleMapper.get().toPeopleModelList(it.results)
                peopleMapper.toPeopleModelList(it.results)
            }
    }

    override fun getProfilePictureByUrl(urlEnd: String): String {
        return "$profileImageUrl$urlEnd"
    }

    companion object {

        var api_key = "7742db1161b96d5da49d8268ec472eae"
        //TODO von eingabr in diese form bringen und übergeben
        var personName = "Spielberg+Steven"
        var profileImageUrl = "https://image.tmdb.org/t/p"
    }
}