package com.demo.book.show.entity

import com.demo.book.movie.entity.Movie
import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Timestamp
import java.time.LocalDateTime

data class Show(
        val id: Int,
        val title: String,
        val movieId: Int,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        val startTime: Timestamp?,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        val endTime: Timestamp?
    )
