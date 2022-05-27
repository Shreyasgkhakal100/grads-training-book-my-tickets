package com.demo.book.api

import com.demo.book.BaseIntegrationSpec
import com.demo.book.movie.entity.Movie
import com.demo.book.movie.request.MovieRequest
import com.demo.book.utils.get
import com.demo.book.utils.post
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.junit.jupiter.api.assertThrows
import java.time.ZoneId
import java.time.ZonedDateTime

class MovieApiTest : BaseIntegrationSpec() {

    init {
        "should save movie" {
            // Given
             val avengersMovie = newMovieRequest(duration=5000)

            // When
            val response = createNewMovie(avengersMovie)

            // Then
            response.status shouldBe HttpStatus.OK
            response.body.get() shouldBe 1
        }

        "should get all saved movies" {
            // Given
            createNewMovie(
                newMovieRequest(duration = 5000)
            )

            // When
            val response = httpClient.get<List<Movie>>("/movies")

            // Then
            response.status shouldBe HttpStatus.OK
            val savedMovies = response.body.get()
            savedMovies.size shouldBe 1
            jsonString(savedMovies[0]) shouldBe """
                |{
                |  "id" : 1,
                |  "title" : "Avengers",
                |  "duration" : 5000
                |}
            """.trimMargin().trimIndent()
        }
        "should throw error if the data is invalid" {
            //Given
            val avengersMovie = newMovieRequest(duration = 5000)

            //When
            try {
                createNewMovie(avengersMovie)
            }catch (e: HttpClientResponseException){
                e.status shouldBe HttpStatus.BAD_REQUEST
            }

        }
    }

    private fun createNewMovie(avengersMovie: MovieRequest): HttpResponse<Any> {
        return httpClient.post(
            url = "/movies",
            body = jsonMapper.writeValueAsString(avengersMovie)
        )
    }

    private fun newMovieRequest(duration:Int): MovieRequest {
        return MovieRequest(
            "Avengers",
            duration
        )
    }
}
