package MainClasses

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Task() {
    private var _description: String = ""
    var description: String
        get() = this._description
        set(value) {
            if (value.isBlank()) {
                println("Invalid description! It cannot be empty.")
                return
            }
            this._description = value
        }

    private var _toDate: String = ""
    var toDate: String
        get() = this._toDate
        set(value) {
            if (isValidDate(value)) {
                this._toDate = value
            } else {
                println("Please enter a valid date (dd.mm.yyyy)")
            }
        }

    private var _isCompleted: String = "Not Completed!"
    var isCompleted: String
        get() = this._isCompleted
        set(value) {
            when (value.lowercase()) {
                "true", "t", "y" -> _isCompleted = "Completed!"
                "false", "f", "n" -> _isCompleted = "Not Completed!"
                else -> println("Invalid value! Use true/false.")
            }
        }

    companion object {
        private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

    fun isValidDate(s: String?): Boolean {
        if (s == null) return false
        return try {
            val date = LocalDate.parse(s, formatter)
            date.isAfter(LocalDate.now().minusDays(1))
        } catch (e: DateTimeParseException) {
            false
        }
    }
}
