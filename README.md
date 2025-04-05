# Library Back-end

## About

A **back-end API** for an Android book library app I'm currently developing, made with **Spring Boot** (w/ **Kotlin**) and **PostgreSQL**.

The API currently has support for GETting, POSTing and DELETEing books stored in the database. Each book record stores information not only about the book itself (title, author, etc.), but also information helpful to the "library", such as to whom the book is lent and when the book's due date is.

## Installation guide

### Requirements:
1. **JDK** version **23**
2. **PostgreSQL 17**. The default configuration the app uses for connecting to the database is below. To change it, go to [application.properties](./src/main/resources/application.properties):
    1. Username: **postgres**
   2. Password: **password**
   3. Database name: **LibraryBackEnd**
   4. Port: **5432**

### Generating the database:


```
CREATE TABLE IF NOT EXISTS public."Books"
(
"ID" integer NOT NULL DEFAULT nextval('"Books_ID_seq"'::regclass),
"Title" character varying(160) COLLATE pg_catalog."default" NOT NULL,
"Author" character varying(50) COLLATE pg_catalog."default" NOT NULL DEFAULT 'Unknown'::character varying,
"DatePublished" date NOT NULL,
"ISBN-13" character varying(20) COLLATE pg_catalog."default",
"ISBN-10" character varying(20) COLLATE pg_catalog."default",
"Language" character varying(30) COLLATE pg_catalog."default",
"Genre" character varying(50) COLLATE pg_catalog."default",
"NumberOfPages" smallint,
"Format" character varying(15) COLLATE pg_catalog."default",
"GiftedBy" character varying(50) COLLATE pg_catalog."default",
"LentStatus" boolean NOT NULL DEFAULT false,
"LentTo" character varying(50) COLLATE pg_catalog."default",
"DueDate" date,
CONSTRAINT "Books_pkey" PRIMARY KEY ("ID")
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Books"
OWNER to postgres;
```

Note that the last query needs to be changed if the username is different.

### Building, running and using the app:

1. You can now build and run the app using **gradlew**.
2. There are example POST requests and a DELETE request in [src/requests.http](src/requests.http). Example REST clients you can use to send them are VSCode's "REST Client" extension and IntelliJ IDEA Ultimate's built-in one.
3. To GET all books in the database, use
```
http://localhost:8080
```
4. To GET a book with a specific ID, for example 2, use
```
http://localhost:8080/2
```