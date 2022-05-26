package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieService(@Inject val movieRepository: MovieRepository) {
    fun save(movieRequest: MovieRequest): Movie {
        return if(movieRequest.endTime - movieRequest.startTime < 300000 || movieRequest.endTime - movieRequest.startTime > 21600000){
            throw Exception("NOT POSSIBLE")
        }else{
            movieRepository.save(movieRequest)
        }
    }

    fun allMovies(): List<Movie> {
        return movieRepository.findAll()
    }
}
