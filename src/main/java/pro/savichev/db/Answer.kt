package pro.savichev.db

import javax.persistence.*

@Table(name = "answer")
@Entity
data class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var team: String = "",
    var answer: String = "",
    var correct: Boolean = false,
    var timestamp: Long = 0L
)