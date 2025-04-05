package com.libraryapp.librarybackend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI

@RestController
@RequestMapping("/")
class BookController(private val service: BookService) {

    @GetMapping
    fun listBooks() = ResponseEntity.ok(service.getBooks())

    @PostMapping
    fun post(@RequestBody book: Book): ResponseEntity<Book> {
        book.id = null // so that clients can't provide custom values for id. See Book.kt for more
        val savedBook = service.postBook(book)
        return ResponseEntity.created(URI("/${savedBook.id}")).body(savedBook)
    }

    @GetMapping("/{id}")
    fun bookByID(@PathVariable id: Int): ResponseEntity<Book> = service.getBookByID(id).toResponseEntity()

    @DeleteMapping("/{id}")
    fun deleteBookByID(@PathVariable id: Int): ResponseEntity<Book> {
        service.deleteBookByID(id)
        return ResponseEntity.noContent().build()
    }

    private fun Book?.toResponseEntity(): ResponseEntity<Book> =
        this?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
}