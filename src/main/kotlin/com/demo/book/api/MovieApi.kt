package com.demo.book.api

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.exception.InvalidMovieDurationException
import com.demo.book.movie.service.MovieService
import com.demo.book.movie.request.MovieRequest
import io.micronaut.context.annotation.Parameter
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.netty.handler.codec.http.QueryStringDecoder
import javax.inject.Inject

@Controller("/movies")
class MovieApi(@Inject val movieService: MovieService) {

    @Get
    fun allMovies(): HttpResponse<List<Movie>> {
        return HttpResponse.ok(movieService.allMovies())
    }

    @Get("/{movieId}")
    fun singleMovie(@PathVariable movieId:Int): HttpResponse<Movie> {
        return HttpResponse.ok(movieService.oneMovie(movieId))
    }
    @Post
    fun saveMovie(@Body movieRequest: MovieRequest): MutableHttpResponse<Int> {
        return try{
            HttpResponse.ok(movieService.save(movieRequest).id)
        }catch (e: InvalidMovieDurationException){
            HttpResponse.badRequest()
        }
    }
}
