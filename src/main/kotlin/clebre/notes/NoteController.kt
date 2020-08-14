package clebre.notes

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/api")
class NoteController(val crud: NoteCrudService) {

    @GetMapping("/ping")
    fun ping() : ResponseEntity<String> {
        return ResponseEntity.ok(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
    }

    @DeleteMapping("/notes/{id}")
    fun deleteNote(@PathVariable("id") id: UUID) : ResponseEntity<UUID> {
        return when(crud.delete(id)) {
            true -> ResponseEntity.ok(id)
            false -> ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/notes/{id}")
    fun getNote(@PathVariable("id") id: UUID) : ResponseEntity<Note> {
        return crud.get(id)
                .map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.notFound().build() )
    }

    @PostMapping("/notes")
    fun createNote(@RequestBody request: CreateNoteRequest) : ResponseEntity<Note> {
        return ResponseEntity.ok(crud.save(Note(
                title = request.title,
                description = request.description,
                starred = request.starred
        )))
    }

    @GetMapping("/notes")
    fun getNotes() : ResponseEntity<List<Note>> {
        return ResponseEntity.ok(crud.all())
    }

    data class CreateNoteRequest(val title: String, val description: String, val starred: Boolean = false)
}

