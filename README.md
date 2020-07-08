# Notes App

Cassandra node must be alive on localhost:9042

It needs to have keyspaces as specified in **schema.cql**

Project JDK which I've been using:
 **openjdk 11.0.7**

## API

sample requests:

`/src/test/kotlin/clebre/notes/sample_requests.http`

POST /api/notes

GET /api/notes

GET /api/notes/{id}

DELETE /api/notes/{id}

