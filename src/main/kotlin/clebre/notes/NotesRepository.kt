package clebre.notes

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import org.springframework.data.cassandra.repository.CassandraRepository
import java.util.*


@Table
data class Note(@PrimaryKey val id: UUID? = null,
                val title: String,
                val description: String,
                val starred: Boolean = false)

interface NotesRepository : CassandraRepository<Note, UUID>
