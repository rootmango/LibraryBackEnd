package com.libraryapp.librarybackend

data class Book(
    // ID is set nullable so that a Book can be instantiated without providing value for ID.
    // When the DB receives the NULL ID (column is set to NOT NULL), it will ignore it and proceed
    // with the auto-increment (nextval)
    var id: Int? = null,
    var title: String,
    var author: String = "Unknown",
    var datePublished: String,
    var isbn13: String? = null,
    var isbn10: String? = null,
    var language: String? = null,
    var genre: String? = null,
    var numberOfPages: Short? = null,
    var format: String? = null,
    var giftedBy: String? = null,
    var lentStatus: Boolean = false,
    var lentTo: String? = null,
    var dueDate: String? = null,
)
