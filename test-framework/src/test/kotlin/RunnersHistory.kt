import edu.austral.dissis.chess.test.game.TestGameRunner

data class RunnersHistory(
    val history: List<TestGameRunnerImpl> = emptyList(),
    val undone: List<TestGameRunnerImpl> = emptyList()
) {

    fun add(runner: TestGameRunnerImpl): RunnersHistory {
        return RunnersHistory(history + runner, emptyList())
    }

    fun undo(current: TestGameRunnerImpl): TestGameRunner {
        return if (history.isNotEmpty()) {
            val last = history.last()
            last.withHistory(RunnersHistory(history.dropLast(1), undone + current))
        } else {
            throw IllegalStateException("No moves to undo")
        }
    }

    fun redo(): TestGameRunner {
        if (undone.isNotEmpty()) {
            val lastUndone = undone.last()
            val newUndone = undone.dropLast(1)
            return lastUndone.withHistory(RunnersHistory(history + lastUndone, newUndone))
        } else {
            throw IllegalStateException("No moves to undo")
        }
    }
}