package Interfaces.InterfaceHandler

import Interfaces.InterfaceEntry.InterfaceEntry
import MainClasses.Client
import MainClasses.Task

const val MAIN_INTERFACE_STRING: String =
    "TODO LIST MANAGER\nSelect an option:\n1) Check To-Do List\n2) Refresh To-Do List\n3) Edit List\n4) Exit"

const val EDIT_INTERFACE_STRING: String =
    "EDIT MANAGER\nSelect an option:\n1) Edit a Task\n2) Remove a Task\n3) Add a Task\n4) Return"

const val EDIT_TASK_INTERFACE_STRING: String =
    "EDIT TASK\nSelect an option:\n1) Change Task Description\n2) Change Task Date\n3) Change is Task completed\n4) Exit"

const val REMOVE_TASK_INTERFACE_STRING: String =
    "REMOVE TASK\n1) Choose a task\n2) Return"

const val ADD_TASK_INTERFACE_STRING: String =
    "ADD TASK\n1) Add a task\n2) Return"

fun MAIN_INTERFACE(msg: String): Boolean {
    println(msg)
    val response = readlnOrNull()
    when (response) {
        "1" -> Client.CheckList()
        "2" -> Client.RefreshList()
        "3" -> switchInterface(INTERFACES["EDIT_INTERFACE"])
        "4" -> return true
        else -> println("Invalid input! Please choose a valid option.")
    }
    return false
}

private fun EDIT_INTERFACE(msg: String): Boolean {
    println(msg)
    val response = readlnOrNull()
    when (response) {
        "1" -> switchInterface(INTERFACES["EDIT_TASK_INTERFACE"])
        "2" -> switchInterface(INTERFACES["REMOVE_TASK_INTERFACE"])
        "3" -> switchInterface(INTERFACES["ADD_TASK_INTERFACE"])
        "4" -> return true
        else -> println("Invalid input! Please choose a valid option.")
    }
    return false
}

private fun EDIT_TASK_INTERFACE(msg: String): Boolean {
    println(msg)
    val response = readlnOrNull()
    when (response) {
        "1" -> {
            val task = Client.PickATask()
            if (task != null) {
                println("Enter a new description:")
                val newDescription = readlnOrNull()
                if (!newDescription.isNullOrBlank()) {
                    Client.changeDescriptionOfTask(task, newDescription)
                } else {
                    println("Description cannot be empty.")
                }
            }
        }
        "2" -> {
            val task = Client.PickATask()
            if (task != null) {
                println("Enter a new date:")
                val newDate = readlnOrNull()
                if (!newDate.isNullOrBlank()) {
                    Client.ChangeToDateOfTask(task, newDate)
                } else {
                    println("Date cannot be empty.")
                }
            }
        }
        "3" -> {
            val task = Client.PickATask()
            if (task != null) {
                println("Is task completed yet? (true/false):")
                val newIsCompleted = readlnOrNull()
                if (newIsCompleted == "true" || newIsCompleted == "false") {
                    Client.ChangeIsCompletedTask(task, newIsCompleted)
                } else {
                    println("Please enter true or false.")
                }
            }
        }
        "4" -> return true
        else -> println("Invalid input! Please choose a valid option.")
    }
    return false
}

private fun REMOVE_TASK_INTERFACE(msg: String): Boolean {
    println(msg)
    val response = readlnOrNull()
    when (response) {
        "1" -> {
            val task = Client.PickATask()
            if (task == null) return true
            Client.RemoveTask(task)
        }
        "2" -> return true
        else -> println("Invalid input! Please choose a valid option.")
    }
    return false
}

private fun ADD_TASK_INTERFACE(msg: String): Boolean {
    println(msg)
    val response = readlnOrNull()
    when (response) {
        "1" -> {
            val task = Task()
            println("Enter a description of the task:")
            val description = readlnOrNull()
            if (description.isNullOrBlank()) {
                println("Description cannot be empty.")
                return false
            }
            task.description = description
            while (true) {
                println("Enter a target date (dd.mm.yyyy) or 'cancel' to return:")
                val date = readlnOrNull()
                if (date == null || date.lowercase() == "cancel") {
                    println("Add task canceled.")
                    return false
                }
                if (task.isValidDate(date)) {
                    task.toDate = date
                    break
                } else {
                    println("Invalid date format or date. Please try again.")
                }
            }
            task.isCompleted = "false"
            Client.AddTask(task)
            println("Task added successfully!")
        }
        "2" -> return true
        else -> println("Invalid input! Please choose a valid option.")
    }
    return false
}

val INTERFACES = mapOf(
    "MAIN_INTERFACE" to InterfaceEntry(MAIN_INTERFACE_STRING, ::MAIN_INTERFACE),
    "EDIT_INTERFACE" to InterfaceEntry(EDIT_INTERFACE_STRING, ::EDIT_INTERFACE),
    "EDIT_TASK_INTERFACE" to InterfaceEntry(EDIT_TASK_INTERFACE_STRING, ::EDIT_TASK_INTERFACE),
    "REMOVE_TASK_INTERFACE" to InterfaceEntry(REMOVE_TASK_INTERFACE_STRING, ::REMOVE_TASK_INTERFACE),
    "ADD_TASK_INTERFACE" to InterfaceEntry(ADD_TASK_INTERFACE_STRING, ::ADD_TASK_INTERFACE)
)

fun switchInterface(interfaceEntry: InterfaceEntry?) {
    if (interfaceEntry == null) throw Exception("Null interface!!!")
    var isFinished = false
    while (!isFinished) {
        isFinished = interfaceEntry.action(interfaceEntry.text)
    }
}
