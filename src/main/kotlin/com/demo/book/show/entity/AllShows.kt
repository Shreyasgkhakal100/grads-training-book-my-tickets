package com.demo.book.show.entity

data class AllShows(
    val pastShows: List<Show>,
    val ongoingShows: List<Show>,
    val upcomingShows: List<Show>
)
