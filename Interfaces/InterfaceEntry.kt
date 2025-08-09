package Interfaces.InterfaceEntry

data class InterfaceEntry(
    val text: String,
    val action: (String) -> Boolean
)