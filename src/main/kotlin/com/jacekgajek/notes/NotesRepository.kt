package com.jacekgajek.notes

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime
import java.util.*


data class Note(@Id val id: UUID = UUID.randomUUID(),
                val title: String,
                val description: String,
                val timestamp: LocalDateTime = LocalDateTime.now(),
                val starred: Boolean = false)

interface NotesRepository : MongoRepository<Note, UUID>
