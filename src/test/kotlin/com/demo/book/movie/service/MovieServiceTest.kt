package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.mockk.MockK
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertThrows
import java.lang.Exception
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.sql.DataSource
import javax.xml.crypto.Data

class MovieServiceTest: StringSpec() {
    private val mockMovieRepository = mockk<MovieRepository>(relaxed = true)

    override fun afterEach(testCase: TestCase, result: TestResult) {
        super.afterEach(testCase, result)
        clearAllMocks()
    }

    init {
        "should get an error if the movie duration is less than 5 min" {
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val movieService = MovieService(mockMovieRepository)
            val movieRequest = MovieRequest("Shaktiman", referenceDate.toInstant().toEpochMilli(), referenceDate.plusHours(-2).toInstant().toEpochMilli())
            assertThrows<Exception> {
                movieService.save(movieRequest)
            }
        }

        "should get an error if the movie duration is 6 hours" {
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val movieService = MovieService(mockMovieRepository)
            val movieRequest = MovieRequest("Shaktiman", referenceDate.toInstant().toEpochMilli(), referenceDate.plusHours(7).toInstant().toEpochMilli())
            assertThrows<Exception> {
                movieService.save(movieRequest)
            }
        }

        "should get an Movie if the movie duration is between 5 min to 6 hours" {
            val mockMovieRepository = mockk<MovieRepository>()
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val movieRequest = MovieRequest("Shaktiman", referenceDate.toInstant().toEpochMilli(), referenceDate.plusHours(2).toInstant().toEpochMilli())
            val expected = Movie(0,"Shaktiman", LocalDateTime.of(2021, 6, 1, 9, 15, 0, 0), LocalDateTime.of(2021, 6, 1, 11, 15, 0, 0))
            every { mockMovieRepository.save(movieRequest) } returns expected
            val movieService = MovieService(mockMovieRepository)
            val response = movieService.save(movieRequest);
            response shouldBe expected
        }
    }
}
