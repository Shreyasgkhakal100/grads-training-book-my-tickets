package com.demo.book.show.service

import com.demo.book.movie.repository.MovieRepository
import com.demo.book.show.entity.AllShows
import com.demo.book.show.entity.Show
import com.demo.book.show.entity.SingleShow
import com.demo.book.show.repository.ShowRepository
import com.demo.book.show.request.ShowRequest
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowService(@Inject val showRepository: ShowRepository, @Inject val movieRepository: MovieRepository) {
    fun save(showRequest: ShowRequest): Show {
        return showRepository.save(showRequest)
    }

    fun allShows(): AllShows {
        val shows : List<Show> = showRepository.findAll()
        val pastShows : MutableList<Show> = mutableListOf()
        val ongoingShows : MutableList<Show> = mutableListOf()
        val upcomingShows: MutableList<Show> = mutableListOf()
        val current = LocalDateTime.now()

        println(shows)
        shows.map {
            val endTime = it.endTime?.toLocalDateTime()
            val startTime = it.startTime?.toLocalDateTime()
//            val diffEndTime = it.endTime?.toLocalDateTime()?.compareTo(current)
//            val diffStartTime = it.startTime?.toLocalDateTime()?.compareTo(current)

            val isBeforeStartTime : Boolean = current.isBefore(startTime)
            val isAfterEndTime : Boolean = current.isAfter(endTime)

            if(isAfterEndTime) {
                pastShows.add(
                    Show(it.id,
                        it.title,
                        it.movieId,
                        it.startTime,
                        it.endTime)
                )
            } else if(isBeforeStartTime) {
                upcomingShows.add(
                    Show(it.id,
                        it.title,
                        it.movieId,
                        it.startTime,
                        it.endTime)
                )
            } else  {
                ongoingShows.add(
                    Show(it.id,
                        it.title,
                        it.movieId,
                        it.startTime,
                        it.endTime)
                )
            }
        }
        return AllShows(pastShows.sortedByDescending { it.endTime }, ongoingShows.sortedBy { it.endTime },
            upcomingShows.sortedBy { it.endTime })
    }


    fun singleShow(id: Int): SingleShow {
        val show = showRepository.findOne(id)
        val movie  = movieRepository.findOne(show.movieId)
        val singlShow: SingleShow = SingleShow(show, movie)
        return singlShow
    }
}
