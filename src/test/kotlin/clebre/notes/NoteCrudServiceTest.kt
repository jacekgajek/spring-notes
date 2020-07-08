package clebre.notes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class NoteCrudServiceTest() {

    @Autowired
    private lateinit var repo: NotesRepository
    @Autowired
    private lateinit var crud: NoteCrudService

    @BeforeEach
    fun cleanupDb() {
        repo.deleteAll()
    }

    @Test
    fun startup() {
    }

    @Test
    fun add() {
        val id = crud.save(Note(
                title = "title",
                description = "desc",
                starred = true)).id
        assertNotNull(id)

        val note = crud.get(id!!).orElseThrow()
        assertEquals("title", note.title)
        assertEquals("desc", note.description)
        assertEquals(true, note.starred)
    }


    @Test
    fun delete() {
        val id1 = crud.save(Note(
                title = "title",
                description = "desc",
                starred = true)).id!!
        val id2 = crud.save(Note(
                title = "title2",
                description = "desc2",
                starred = true)).id!!

        assertEquals(2, crud.all().size)
        crud.delete(id1)
        assertEquals(1, crud.all().size)
        crud.delete(id1)
        assertEquals(1, crud.all().size)
        crud.delete(id2)
        assertEquals(0, crud.all().size)
    }

    @Test
    fun list() {
        crud.save(Note(
                title = "title",
                description = "desc",
                starred = true))
        crud.save(Note(
                title = "title2",
                description = "desc2",
                starred = false))

        val notes = crud.all()
        assertEquals("title", notes[0].title)
        assertEquals("desc", notes[0].description)
        assertEquals(true, notes[0].starred)
        assertEquals("title2", notes[1].title)
        assertEquals("desc2", notes[1].description)
        assertEquals(false, notes[1].starred)
    }
}
