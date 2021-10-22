package daveho.co.auntypasty.clearscoredonut.api.model

data class CoachingSummary(
    val activeTodo: Boolean,
    val activeChat: Boolean,
    val numberOfTodoItems: Int,
    val numberOfCompletedTodoItems: Int,
    val selected: Boolean
) {
    companion object {
        val EMPTY = CoachingSummary(
            activeTodo = false,
            activeChat = false,
            numberOfTodoItems = 0,
            numberOfCompletedTodoItems = 0,
            selected = false
        )
    }
}
