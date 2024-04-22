package edu.austral.dissis.chess.test.game

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition

sealed interface TestMoveResult

sealed interface FinalTestMoveResult : TestMoveResult {
    val finalBoard: TestBoard
}
data class TestMoveSuccess(val testGameRunner: TestGameRunner) : TestMoveResult
data class TestMoveDraw(override val finalBoard: TestBoard) : FinalTestMoveResult
data class WhiteCheckMate(override val finalBoard: TestBoard) : FinalTestMoveResult
data class BlackCheckMate(override val finalBoard: TestBoard) : FinalTestMoveResult
data class TestMoveFailure(override val finalBoard: TestBoard) : FinalTestMoveResult

interface TestGameRunner {

    fun withBoard(board: TestBoard): TestGameRunner

    fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult

    fun getBoard(): TestBoard
}