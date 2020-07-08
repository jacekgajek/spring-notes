package clebre.notes

import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteCrudService(val notesRepository: NotesRepository) {

    fun delete(id: UUID) : Boolean {
        return when (notesRepository.existsById(id)) {
            true -> {
                notesRepository.deleteById(id)
                true
            }
            false -> false
        }
    }

    fun save(note: Note) : Note {
        return notesRepository.save(note.copy(id = UUID.randomUUID()))
    }

    fun all() : List<Note> {
        return notesRepository.findAll()
    }

    fun get(id: UUID) : Optional<Note> {
        return notesRepository.findById(id)
    }
}
