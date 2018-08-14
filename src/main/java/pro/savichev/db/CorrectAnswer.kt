package pro.savichev.db

import javax.persistence.*

@Table(name = "correct_answers")
@Entity
data class CorrectAnswer(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,
        val answer: String = "",
        val tetramino: String = "")
