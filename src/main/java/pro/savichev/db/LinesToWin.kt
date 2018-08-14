package pro.savichev.db

import pro.savichev.Config
import javax.persistence.*

@Table(name = "lines_to_win")
@Entity
data class LinesToWin (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val lines_to_win: Int = Config.LINES_TO_WIN)