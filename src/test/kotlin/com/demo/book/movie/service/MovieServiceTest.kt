package com.demo.book.movie.service

import com.demo.book.movie.entity.Movie
import com.demo.book.movie.exception.InvalidMovieDurationException
import com.demo.book.movie.repository.MovieRepository
import com.demo.book.movie.request.MovieRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class MovieServiceTest: StringSpec() {
    private val mockMovieRepository = mockk<MovieRepository>()

    override fun afterEach(testCase: TestCase, result: TestResult) {
        super.afterEach(testCase, result)
        clearAllMocks()
    }

    init {
        "should get an error if the movie duration is less than 5 min" {
            val movieService = MovieService(mockMovieRepository)
            val movieRequest = MovieRequest("Shaktiman", 100)
            assertThrows<InvalidMovieDurationException> {
                movieService.save(movieRequest)
            }
        }

        "should get an error if the movie duration is 6 hours" {
            val movieService = MovieService(mockMovieRepository)
            val movieRequest = MovieRequest("Shaktiman", 300000)
            assertThrows<InvalidMovieDurationException> {
                movieService.save(movieRequest)
            }
        }

        "should add an Movie if the movie duration is between 5 min to 6 hours" {
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val movieRequest = MovieRequest("Shaktiman", 5000)
            val expected = Movie(0,"Shaktiman", 5000)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val movieService = MovieService(mockMovieRepository)
            val response = movieService.save(movieRequest);
            response shouldBe expected
        }

        "should add an Movie if the movie duration is exactly 5 minutes" {
            val movieRequest = MovieRequest("Shaktiman", 300)
            val expected = Movie(0,"Shaktiman", 300)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val movieService = MovieService(mockMovieRepository)
            val response = movieService.save(movieRequest)
            response shouldBe expected
        }

        "should add an Movie if the movie duration is exactly 6 hours" {
            val referenceDate = ZonedDateTime.of(2021, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val movieRequest = MovieRequest("Shaktiman", 21600)
            val expected = Movie(0,"Shaktiman", 21600)
            every { mockMovieRepository.save(movieRequest) } returns expected
            val movieService = MovieService(mockMovieRepository)
            val response = movieService.save(movieRequest);
            response shouldBe expected
        }
    }
}
