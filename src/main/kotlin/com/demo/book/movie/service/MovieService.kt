package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.exception.InvalidMovieDurationException
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import javax.inject.Inject
import javax.inject.Singleton

const val MOVIE_DURATION_LOWER_LIMIT_IN_MILLISECONDS = 300
const val MOVIE_DURATION_UPPER_LIMIT_IN_MILLISECONDS = 21600
@Singleton
class MovieService(@Inject val movieRepository: MovieRepository) {
    fun save(movieRequest: MovieRequest): Movie {
//        val movieDuration = movieRequest.endTime - movieRequest.startTime
        if(movieRequest.duration < MOVIE_DURATION_LOWER_LIMIT_IN_MILLISECONDS || movieRequest.duration > MOVIE_DURATION_UPPER_LIMIT_IN_MILLISECONDS){
            throw InvalidMovieDurationException("Invalid movie duration: Check the movie duration time")
        }
        return movieRepository.save(movieRequest)
    }

    fun allMovies(): List<Movie> {
        return movieRepository.findAll()
    }

    fun oneMovie(movieId: Int): Movie {
        return movieRepository.findOne(movieId)
    }
}
