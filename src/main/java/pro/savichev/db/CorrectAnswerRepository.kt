package pro.savichev.db

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CorrectAnswerRepository: CrudRepository<CorrectAnswer, Int> {
    fun findByAnswer(answer: String): Optional<CorrectAnswer>
}