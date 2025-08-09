package MainClasses

import Interfaces.InterfaceHandler.INTERFACES
import Interfaces.InterfaceHandler.switchInterface

class Client {
    companion object {
        val todolist = ToDoList()

        fun RefreshList() = todolist.refresh()

        fun CheckList() = todolist.printContent()

        fun RemoveTask(task: Task) = todolist.content.remove(task)

        fun AddTask(task: Task) = todolist.content.add(task)

        fun changeDescriptionOfTask(task: Task, newDesc: String) {
            val index = todolist.content.indexOf(task)
            if (index != -1) {
                todolist.content[index].description = newDesc
            }
        }

        fun ChangeIsCompletedTask(task: Task, response: String?) {
            val index = todolist.content.indexOf(task)
            if (index != -1 && response != null) {
                todolist.content[index].isCompleted = response
            } else {
                println("Invalid value!")
            }
        }

        fun ChangeToDateOfTask(task: Task, newDate: String?) {
            val index = todolist.content.indexOf(task)
            if (index != -1 && task.isValidDate(newDate)) {
                todolist.content[index].toDate = newDate.toString()
            } else {
                println("Invalid Date!")
            }
        }

        fun PickATask(): Task? {
            while (true) {
                todolist.printContent()
                println("Choose a task (or 0 to return):")
                val response = readlnOrNull()

                if (response == null) {
                    println("Invalid input!")
                    continue
                }
                if (response == "0") return null
                val choice = response.toIntOrNull()
                if (choice != null && choice in 1..todolist.content.size) {
                    return todolist.content[choice - 1]
                }
                println("Invalid task! Please enter a valid number or 0 to cancel.")
            }
        }
    }

    fun Fire() {
        switchInterface(INTERFACES["MAIN_INTERFACE"])
    }
}
