package com.demo.book.api

import com.demo.book.movie.request.MovieRequest
import com.demo.book.show.entity.Show
import com.demo.book.show.entity.SingleShow
import com.demo.book.show.request.ShowRequest
import com.demo.book.show.service.ShowService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/shows")
class ShowApi(@Inject val showService: ShowService) {

    @Get
    fun allShows(): HttpResponse<List<Show>> {
        return HttpResponse.ok(showService.allShows())
    }

    @Get("/{id}")
    fun singleShow(id: Int): HttpResponse<SingleShow> {
        return HttpResponse.ok(showService.singleShow(id))
    }

    @Post
    fun saveShows(@Body showRequest: ShowRequest): MutableHttpResponse<Int>{
        return HttpResponse.ok(showService.save(showRequest).id)
    }

}
