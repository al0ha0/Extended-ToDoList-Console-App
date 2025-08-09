package MainClasses

class ToDoList {
    private val _content = mutableListOf<Task>()
    val content: MutableList<Task> get() = _content

    fun addTask(task: Task) {
        if (task.description.isNotBlank() && task.toDate.isNotBlank()) {
            _content.add(task)
        } else {
            println("Task missing description or valid date. Not added.")
        }
    }

    fun printContent() {
        if (_content.isEmpty()) {
            println("No tasks found")
            return
        }
        _content.forEachIndexed { index, task ->
            println("•${index + 1} ${task.description} || ${task.toDate} || ${task.isCompleted}")
        }
        println()
    }

    fun refresh() = _content.clear()
}
