package com.demo.book.show.service

import com.demo.book.show.entity.Show
import com.demo.book.show.repository.ShowRepository
import com.demo.book.show.request.ShowRequest
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import java.sql.Timestamp
import java.time.ZoneId
import java.time.ZonedDateTime

class ShowServiceTest: StringSpec() {
    private val mockShowRepository = mockk<ShowRepository>()

    override fun afterEach(testCase: TestCase, result: TestResult) {
        super.afterEach(testCase, result)
        clearAllMocks()
    }

    init {
        "should add an Show & return registered Show id" {
            val referenceDate = ZonedDateTime.of(2022, 6, 1, 9, 15, 0, 0, ZoneId.systemDefault())
            val showRequest = ShowRequest("Avengers Endgame Morning Show", 1, referenceDate.toEpochSecond(), referenceDate.plusHours(2).toEpochSecond())
            val expected = Show(0,"Avengers Endgame Morning Show", 1, Timestamp(2022, 6, 1, 9, 15, 0, 0), Timestamp(2022, 6, 1, 11, 15, 0, 0))
            every { mockShowRepository.save(showRequest) } returns expected
            val showService = ShowService(mockShowRepository)
            val response = showService.save(showRequest)
            response shouldBe expected
        }
    }
}
