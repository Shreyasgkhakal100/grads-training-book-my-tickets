package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.exception.InvalidMovieDurationException
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

const val MOVIE_DURATION_LOWER_LIMIT_IN_MILLISECONDS = 300000
const val MOVIE_DURATION_UPPER_LIMIT_IN_MILLISECONDS = 21600000
@Singleton
class MovieService(@Inject val movieRepository: MovieRepository) {
    fun save(movieRequest: MovieRequest): Movie {
        val movieDuration = movieRequest.endTime - movieRequest.startTime
        if(movieDuration < MOVIE_DURATION_LOWER_LIMIT_IN_MILLISECONDS || movieDuration > MOVIE_DURATION_UPPER_LIMIT_IN_MILLISECONDS){
            throw InvalidMovieDurationException("Invalid movie duration: Check the movie duration time")
        }
        return movieRepository.save(movieRequest)
    }

    fun allMovies(): List<Movie> {
        return movieRepository.findAll()
    }
}
