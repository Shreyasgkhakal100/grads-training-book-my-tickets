package com.demo.book.show.service

import com.demo.book.show.entity.Show
import com.demo.book.show.repository.ShowRepository
import com.demo.book.show.request.ShowRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowService(@Inject val showRepository: ShowRepository) {
    fun save(showRequest: ShowRequest): Show {
        return showRepository.save(showRequest)
    }

    fun allShows(): List<Show>{
        return showRepository.findAll()
    }
}
