package clebre.notes

import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteCrudService(val notesRepository: NotesRepository) {

    fun delete(id: UUID) : Boolean {
        val exists = notesRepository.existsById(id)
        if (exists) {
            notesRepository.deleteById(id)
        }
        return exists;
    }

    fun save(note: Note) : Note {
        return notesRepository.save(note)
    }

    fun all() : List<Note> {
        return notesRepository.findAll()
    }

    fun get(id: UUID) : Optional<Note> {
        return notesRepository.findById(id)
    }
}
