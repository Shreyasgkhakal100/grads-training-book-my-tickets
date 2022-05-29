package com.demo.book.show.repository

import com.demo.book.show.entity.Show
import com.demo.book.show.request.ShowRequest
import norm.query
import show.GetAllShowsParams
import show.GetAllShowsQuery
import show.SaveShowParams
import show.SaveShowQuery
import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class ShowRepository(@Inject private val datasource: DataSource) {
    fun save(showToSave: ShowRequest): Show = datasource.connection.use { connection ->
        SaveShowQuery().query(
            connection,
            SaveShowParams(
                showToSave.title,
                showToSave.movieId,
                Timestamp.from(Instant.ofEpochSecond(showToSave.startTime)),
                Timestamp.from(Instant.ofEpochSecond(showToSave.startTime)),
            )
        )
    }.map {
        Show(
            it.id,
            it.title,
            it.movieId,
            it.startTime,
            it.endTime
        )
    }.first()

    fun findAll(): List<Show> = datasource.connection.use { connection ->
        GetAllShowsQuery().query(
            connection,
            GetAllShowsParams()
        )
    }.map {
        Show(
            it.id,
            it.title,
            it.movieId,
            it.startTime,
            it.endTime
        )
    }

    fun findOne(id: Int): Show =  datasource.connection.use { connection ->
        GetAllShowsQuery().query(
            connection,
            GetAllShowsParams()
        )
    }.map {
        Show(
            it.id,
            it.title,
            it.movieId,
            it.startTime,
            it.endTime
        )
    }.find { it.id == id } ?: throw IllegalArgumentException("Movie not found")

}
