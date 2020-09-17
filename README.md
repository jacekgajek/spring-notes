# Notes App

Use `docker-compose up` to start cassandra and initialize a keyspace. The project can be run and developed with openjdk version "11.0.7". `notes.iml` is a project file for the Intellij Idea IDE.

## API

sample requests are in this file: `/src/test/kotlin/clebre/notes/sample_requests.http`

POST /api/notes

GET /api/notes

GET /api/notes/{id}

DELETE /api/notes/{id}

