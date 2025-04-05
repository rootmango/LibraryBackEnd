package com.libraryapp.librarybackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class LibrarybackendApplication

fun main(args: Array<String>) {
    runApplication<LibrarybackendApplication>(*args)
}