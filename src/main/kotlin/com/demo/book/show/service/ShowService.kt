package com.demo.book.show.service

import com.demo.book.movie.repository.MovieRepository
import com.demo.book.show.entity.Show
import com.demo.book.show.entity.SingleShow
import com.demo.book.show.exception.OverlappingShowTimingException
import com.demo.book.show.repository.ShowRepository
import com.demo.book.show.request.ShowRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowService(@Inject val showRepository: ShowRepository, @Inject val movieRepository: MovieRepository) {
    fun save(showRequest: ShowRequest): Show {
        if(showRepository.findOverlap(showRequest.startTime, showRequest.endTime).isNotEmpty()) {
            throw OverlappingShowTimingException("Already have a show scheduled during that time")
        }
        return showRepository.save(showRequest)
    }


    fun allShows(): List<Show>{
        return showRepository.findAll()
    }

    fun singleShow(id: Int): SingleShow {
        val show = showRepository.findOne(id)
        val movie = movieRepository.findOne(show.movieId)
        return SingleShow(show, movie)
    }
}
