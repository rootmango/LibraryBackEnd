package com.libraryapp.librarybackend

import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper

@Service
class BookService(private val db: JdbcTemplate) {
    fun getBooks(): List<Book> = db.query("select * from \"Books\"") { response, _ ->
        Book(
            response.getInt("ID"),
            response.getString("Title"),
            response.getString("Author"),
            response.getString("DatePublished"),
            response.getString("ISBN-13"),
            response.getString("ISBN-10"),
            response.getString("Language"),
            response.getString("Genre"),
            // since getShort() in Java returns a primitive, numberOfPages cannot be null.
            // casting getObject() to Short? fixes that:
            response.getObject("NumberOfPages") as Short?,
            response.getString("Format"),
            response.getString("GiftedBy"),
            response.getBoolean("LentStatus"),
            response.getString("LentTo"),
            response.getString("DueDate")
        )
    }

    fun postBook(book: Book): Book {
        db.update("insert into \"Books\" (" +
                "\"Title\", " +
                "\"Author\", " +
                "\"DatePublished\", " +
                "\"ISBN-13\", " +
                "\"ISBN-10\", " +
                "\"Language\", " +
                "\"Genre\", " +
                "\"NumberOfPages\", " +
                "\"Format\", " +
                "\"GiftedBy\", " +
                "\"LentStatus\", " +
                "\"LentTo\", " +
                "\"DueDate\") " +
                "values " +
                "(?, ?, to_date(?, \'YYYY-MM-DD\'), ?, ?, ?, ?, ?, ?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'))",
            book.title,
            book.author,
            book.datePublished,
            book.isbn13,
            book.isbn10,
            book.language,
            book.genre,
            book.numberOfPages,
            book.format,
            book.giftedBy,
            book.lentStatus,
            book.lentTo,
            book.dueDate
        )
        return book
    }

    fun getBookByID(id: Int): Book? = db.query(
        "select * from \"Books\" where \"ID\" = ?",
        RowMapper { response, _ ->
            Book(
                response.getInt("ID"),
                response.getString("Title"),
                response.getString("Author"),
                response.getString("DatePublished"),
                response.getString("ISBN-13"),
                response.getString("ISBN-10"),
                response.getString("Language"),
                response.getString("Genre"),
                // since getShort() in Java returns a primitive, numberOfPages cannot be null.
                // casting getObject() to Short? fixes that:
                response.getObject("NumberOfPages") as Short?,
                response.getString("Format"),
                response.getString("GiftedBy"),
                response.getBoolean("LentStatus"),
                response.getString("LentTo"),
                response.getString("DueDate")
            )
        },
        id
    ).singleOrNull()

    // Not yet implemented
    fun getBooksByTitle(title: String): List<Book> = db.query(
        "select * from \"Books\" where \"Title\" = ?",
        RowMapper { response, _ ->
            Book(
                response.getInt("ID"),
                response.getString("Title"),
                response.getString("Author"),
                response.getString("DatePublished"),
                response.getString("ISBN-13"),
                response.getString("ISBN-10"),
                response.getString("Language"),
                response.getString("Genre"),
                // since getShort() in Java returns a primitive, numberOfPages cannot be null.
                // casting getObject() to Short? fixes that:
                response.getObject("NumberOfPages") as Short?,
                response.getString("Format"),
                response.getString("GiftedBy"),
                response.getBoolean("LentStatus"),
                response.getString("LentTo"),
                response.getString("DueDate")
            )
        },
        title
    )

    // Not yet implemented
    fun getBooksByAuthor(author: String): List<Book> = db.query(
        "select * from \"Books\" where \"Author\" = ?",
        RowMapper { response, _ ->
            Book(
                response.getInt("ID"),
                response.getString("Title"),
                response.getString("Author"),
                response.getString("DatePublished"),
                response.getString("ISBN-13"),
                response.getString("ISBN-10"),
                response.getString("Language"),
                response.getString("Genre"),
                // since getShort() in Java returns a primitive, numberOfPages cannot be null.
                // casting getObject() to Short? fixes that:
                response.getObject("NumberOfPages") as Short?,
                response.getString("Format"),
                response.getString("GiftedBy"),
                response.getBoolean("LentStatus"),
                response.getString("LentTo"),
                response.getString("DueDate")
            )
        },
        author
    )

    fun deleteBookByID(id: Int) {
        db.update("delete from \"Books\" where \"ID\" = ?", id)
    }
}