package pro.savichev


import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.Base64Utils
import org.springframework.web.bind.annotation.*
import pro.savichev.db.*
import pro.savichev.db.log.Log
import pro.savichev.model.AnswerRequestBody
import pro.savichev.model.CheckRequestBody
import pro.savichev.utils.*
import java.util.*

@Controller
open class Controller {

    companion object {
        val TAG = Controller::class.simpleName!!
    }

    @Autowired
    private lateinit var log: Log
    @Autowired
    private lateinit var answerRepository: AnswerRepository
    @Autowired
    private lateinit var correctAnswerRepository: CorrectAnswerRepository
    @Autowired
    private lateinit var monitorRepository: MonitorRepository
    @Autowired
    private lateinit var linesToWinRepository: LinesToWinRepository
    @Autowired
    private lateinit var gson: Gson

    @RequestMapping("/{team:[a-zA-Z0-9]+}/answer", method = [RequestMethod.POST])
    @ResponseStatus(value = HttpStatus.OK)
    open fun answer(@PathVariable team: String, @RequestBody answerRequestBody: AnswerRequestBody) {
        try {
            val correctAnswer = correctAnswerRepository.findByAnswer(answerRequestBody.answer)
            val answer = Answer(0, team, answerRequestBody.answer, correctAnswer.isPresent, System.currentTimeMillis())
            answerRepository.save(answer)
        } catch (e: Throwable) {
            log.e(TAG, "", e)
        }
    }

    @RequestMapping("/{team:[a-zA-Z0-9]+}/check/{signature:[a-zA-Z0-9-_]+}", method = [RequestMethod.POST])
    open fun check(@PathVariable team: String,
                   @PathVariable signature: String,
                   @RequestBody checkRequestBody: CheckRequestBody): ResponseEntity<JsonObject> {

        val json = JsonObject()
        json.add("status", JsonPrimitive(-1))
        val failResponse = ResponseEntity.ok(json)

        if (!Config.TEAMS.contains(team)) {
            log.i(TAG, "WARNING!!! INCORRECT TEAM ID!!!")
            return failResponse
        }

        val linesToWin = linesToWinRepository.findById(1).orElse(LinesToWin()).lines_to_win

        val inArray = byteArrayOf(0x50.toByte(), 0xf0.toByte(), 0x0c.toByte(), 0x24.toByte(), 0xa1.toByte(), 0xe5.toByte(), 0x58.toByte(), 0x2e.toByte(), 0x55.toByte(), 0x0b.toByte(), 0x66.toByte(), 0xca.toByte(), 0x04.toByte(), 0xf2.toByte(), 0x90.toByte(), 0x1b.toByte(),
                0xde.toByte(), 0x3f.toByte(), 0x5d.toByte(), 0xfd.toByte(), 0x38.toByte(), 0xf0.toByte(), 0x27.toByte(), 0x3d.toByte(), 0x10.toByte(), 0x6e.toByte(), 0x98.toByte(), 0x19.toByte(), 0x04.toByte(), 0x3d.toByte(), 0xa5.toByte(), 0x85.toByte(),
                0x6c.toByte(), 0xc6.toByte(), 0x99.toByte(), 0x45.toByte(), 0x67.toByte(), 0xce.toByte(), 0x5c.toByte(), 0xd4.toByte(), 0xa3.toByte(), 0xbe.toByte(), 0x60.toByte(), 0x7f.toByte(), 0xb5.toByte(), 0x84.toByte(), 0xd6.toByte(), 0x18.toByte(),
                0x9c.toByte(), 0x09.toByte(), 0x86.toByte(), 0xb1.toByte(), 0x81.toByte(), 0xc0.toByte(), 0x7d.toByte(), 0x33.toByte(), 0xff.toByte(), 0x0b.toByte(), 0xee.toByte(), 0x34.toByte(), 0xf8.toByte(), 0x10.toByte(), 0xa6.toByte(), 0x60.toByte(),
                0xa4.toByte(), 0xd8.toByte(), 0xaa.toByte(), 0xb2.toByte(), 0x94.toByte(), 0xd4.toByte(), 0x7a.toByte(), 0xe1.toByte(), 0x78.toByte(), 0x61.toByte(), 0x8e.toByte(), 0x4f.toByte(), 0x4c.toByte(), 0x5b.toByte(), 0x40.toByte(), 0x3b.toByte(),
                0x43.toByte(), 0xd2.toByte(), 0x3c.toByte(), 0xdc.toByte(), 0xc7.toByte(), 0xc2.toByte(), 0xfa.toByte(), 0x42.toByte(), 0x43.toByte(), 0xef.toByte(), 0xe7.toByte(), 0x2d.toByte(), 0x1d.toByte(), 0x51.toByte(), 0x93.toByte(), 0x4d.toByte(),
                0x79.toByte(), 0xdc.toByte(), 0x33.toByte(), 0xcf.toByte(), 0x0c.toByte(), 0x69.toByte(), 0x10.toByte(), 0x0c.toByte(), 0x56.toByte(), 0x50.toByte(), 0xef.toByte(), 0x91.toByte(), 0xc6.toByte(), 0xf9.toByte(), 0xa8.toByte(), 0x51.toByte(),
                0xe7.toByte(), 0x8f.toByte(), 0x20.toByte(), 0xad.toByte(), 0xc6.toByte(), 0xe7.toByte(), 0xa7.toByte(), 0xd7.toByte(), 0x1c.toByte(), 0xf6.toByte(), 0xc8.toByte(), 0x0a.toByte(), 0xa4.toByte(), 0x72.toByte(), 0xa0.toByte(), 0x2a.toByte(),
                0xa0.toByte(), 0x43.toByte(), 0xdb.toByte(), 0x6e.toByte(), 0xc5.toByte(), 0x80.toByte(), 0x09.toByte(), 0x0b.toByte(), 0x70.toByte(), 0x60.toByte(), 0x90.toByte(), 0xfb.toByte(), 0xf3.toByte(), 0x85.toByte(), 0x14.toByte(), 0x4e.toByte(),
                0x14.toByte(), 0x5e.toByte(), 0xa7.toByte(), 0xa3.toByte(), 0x87.toByte(), 0xf7.toByte(), 0xdc.toByte(), 0x4b.toByte(), 0xe5.toByte(), 0xfb.toByte(), 0x4d.toByte(), 0xdf.toByte(), 0xf5.toByte(), 0xe5.toByte(), 0x07.toByte(), 0xa8.toByte(),
                0xcb.toByte(), 0x89.toByte(), 0xc0.toByte(), 0xda.toByte(), 0xe6.toByte(), 0x10.toByte(), 0x1b.toByte(), 0xa2.toByte(), 0x63.toByte(), 0x73.toByte(), 0x02.toByte(), 0x8e.toByte(), 0x88.toByte(), 0x2f.toByte(), 0xcf.toByte(), 0x5d.toByte(),
                0x18.toByte(), 0x06.toByte(), 0xf3.toByte(), 0xcc.toByte(), 0x0e.toByte(), 0x87.toByte(), 0x78.toByte(), 0x51.toByte(), 0x05.toByte(), 0x55.toByte(), 0xdc.toByte(), 0x0d.toByte(), 0x01.toByte(), 0x68.toByte(), 0xc5.toByte(), 0xf3.toByte(),
                0xad.toByte(), 0x5e.toByte(), 0xbd.toByte(), 0xb8.toByte(), 0x79.toByte(), 0x1c.toByte(), 0x43.toByte(), 0x4f.toByte(), 0x25.toByte(), 0x6a.toByte(), 0xd3.toByte(), 0x2b.toByte(), 0x44.toByte(), 0x61.toByte(), 0x28.toByte(), 0xc6.toByte(),
                0x83.toByte(), 0x94.toByte(), 0x2c.toByte(), 0xbe.toByte(), 0xfa.toByte(), 0xc2.toByte(), 0x0a.toByte(), 0x30.toByte(), 0x65.toByte(), 0x44.toByte(), 0xa1.toByte(), 0xfb.toByte(), 0x49.toByte(), 0xd9.toByte(), 0x99.toByte(), 0xa2.toByte(),
                0x85.toByte(), 0xb9.toByte(), 0x06.toByte(), 0x80.toByte(), 0x6a.toByte(), 0x7e.toByte(), 0x92.toByte(), 0x24.toByte(), 0x3d.toByte(), 0x94.toByte(), 0x94.toByte(), 0x8f.toByte(), 0x2d.toByte(), 0xe8.toByte(), 0x02.toByte(), 0x18.toByte(),
                0xf6.toByte(), 0x8e.toByte(), 0x61.toByte(), 0xa7.toByte(), 0x63.toByte(), 0x43.toByte(), 0x67.toByte(), 0x68.toByte(), 0x89.toByte(), 0x62.toByte(), 0x67.toByte(), 0x73.toByte(), 0x28.toByte(), 0x85.toByte(), 0x80.toByte(), 0xd3.toByte(),
                0xe1.toByte())
        try {
            val key = AESKey.generate(inArray)
            val encBoard = checkRequestBody.board
            if (encBoard.isEmpty()) {
                throw Exception("CheckRequestBody is empty")
            }

            val encAnswers = checkRequestBody.answers
            if (encAnswers.isEmpty()) {
                throw Exception("CheckRequestBody is empty")
            }

            val encRemovedLines = checkRequestBody.removed_lines
            if (encRemovedLines.isEmpty()) {
                throw Exception("CheckRequestBody is empty")
            }

            val encBoardBytes = Base64Utils.decodeFromUrlSafeString(encBoard)
            var iv = Arrays.copyOf(SHA.sha3_256(key), 16)
            val boardBytes = AES.decrypt(encBoardBytes, key, iv)
            val encAnswersBytes = Base64Utils.decodeFromUrlSafeString(encAnswers)
            val answersBytes = AES.decrypt(encAnswersBytes, key, iv)
            val encRemovedLinesBytes = Base64Utils.decodeFromUrlSafeString(encRemovedLines)
            val removedLinesBytes = AES.decrypt(encRemovedLinesBytes, key, iv)

            val encSalt = "Z6p4N34ZPzUOuiUMlc98WRfSzrLiuS1z4e8jchv2QFIq1xhaOERy8ftkmFrEjUcXl5wQ88tsFK8G_q47UPvHgV5bK0WTSfPkHG49LN-gPqR_csxzeNXDtvtEytUJloCsNs7mIA2RO38vODCdyRob4w4zcAnohixxboRZ4gF1UnY"
            val saltKey = AESKey.generate(inArray)
            iv = Arrays.copyOf(SHA.sha3_256(saltKey), 16)
            val decSalt = AES.decrypt(Base64Utils.decodeFromUrlSafeString(encSalt), saltKey, iv)
            val salt = SHA.sha3_256(SHA.sha3_256(decSalt) + "c6bb8bac3d74dfc7f20efd477352c1ca".hexStringToByteArray())
            val shaAnswers = SHA.sha3_256(answersBytes)
            val shaBoard = SHA.sha3_256(boardBytes)
            val shaRemovedLines = SHA.sha3_256(removedLinesBytes)
            val mySignature = SHA.sha3_256(shaBoard
                    + "b9e714343567c0a497e54257835516afc3f81a7e1d55498b71fc605cadca275e".hexStringToByteArray()
                    + shaAnswers
                    + salt
                    + shaRemovedLines)
            val signatureBytes = Base64Utils.decodeFromUrlSafeString(signature)
            if (Arrays.equals(signatureBytes, mySignature)) {
                log.i(TAG, "WARNING!!! INVALID SIGNATURE!!!\nMy: ${mySignature.toHex()},\nReceived: ${signatureBytes.toHex()}\nTeam: $team")
                return failResponse
            }

            var prettyBoard = ""
            val board = gson.fromJson<Array<Array<Boolean>>>(String(boardBytes), Array<Array<Boolean>>::class.java)
            for (i: Int in 0 until Config.BOARD_HEIGHT) {
                for (j: Int in 0 until Config.BOARD_WIDTH) {
                    prettyBoard += if (board[i][j]) {
                        "1"
                    } else {
                        "0"
                    }
                }
                prettyBoard += "\n"
            }

            val answers = gson.fromJson<Array<String>>(String(answersBytes), Array<String>::class.java)
            var answersString = ""
            for (s: String in answers) {
                answersString += s
                answersString += "\n"
            }

            val removedLines = String(removedLinesBytes).toInt()

            var win = false

            if (removedLines >= linesToWin) {
                win = true
            }

            val check = Monitor(
                    team = team,
                    game_id = checkRequestBody.game_id,
                    board = prettyBoard,
                    answers = answersString,
                    removed_lines = removedLines,
                    win = win,
                    timestamp = System.currentTimeMillis())

            monitorRepository.save(check)

            if (win && answers.size > Config.BOARD_WIDTH * linesToWin / 4 && !isArrayWithRepeats(answers)) {
                val winJson = JsonObject()
                winJson.add("status", JsonPrimitive(0))
                winJson.add("answer", JsonPrimitive(Config.FINAL_ANSWER))
                return ResponseEntity.ok(winJson)
            }

        } catch (e: Exception) {
            log.e(TAG, "", e)
        }
        return failResponse
    }

    @GetMapping("/lines")
    @ResponseStatus(value = HttpStatus.OK)
    open fun lines(): ResponseEntity<JsonObject> {
        val json = JsonObject()
        val linesToWin = linesToWinRepository.findById(1).orElse(LinesToWin()).lines_to_win
        json.add("lines", JsonPrimitive(linesToWin))
        return ResponseEntity.ok(json)
    }

    private fun <T> isArrayWithRepeats(array: Array<out T>): Boolean {
        for (i: Int in 0 until array.size) {
            val elem = array[i]
            for (j: Int in 0 until array.size) {
                if (elem != array[i] && elem == array[j]) {
                    return true
                }
            }
        }
        return false
    }

}
