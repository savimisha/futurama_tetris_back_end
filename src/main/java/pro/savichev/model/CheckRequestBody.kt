package pro.savichev.model


data class CheckRequestBody(val board: String = "",
                            val answers: String = "",
                            val removed_lines: String = "",
                            val game_id: Int = 0)