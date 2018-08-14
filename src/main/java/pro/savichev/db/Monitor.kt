package pro.savichev.db

import javax.persistence.*

@Entity
@Table(name = "monitor")
data class Monitor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int  = 0,
        val team: String = "",
        val game_id: Int = 0,
        val board: String = "",
        val answers: String = "",
        val removed_lines: Int = 0,
        val win: Boolean = false,
        val timestamp: Long = 0L)
