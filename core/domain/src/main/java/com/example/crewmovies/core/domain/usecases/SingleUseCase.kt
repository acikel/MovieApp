package com.example.crewmovies.core.domain.usecases
import io.reactivex.Single

interface SingleUseCase<R>  {

        fun execute(): Single<R>
}