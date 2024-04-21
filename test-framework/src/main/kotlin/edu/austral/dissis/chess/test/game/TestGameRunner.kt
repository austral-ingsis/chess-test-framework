package edu.austral.dissis.chess.test.game

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition

sealed interface TestMoveResult
data class TestMoveSuccess(val testGameRunner: TestGameRunner) : TestMoveResult
data object TestMoveDraw : TestMoveResult
data object WhiteCheckMate : TestMoveResult
data object BlackCheckMate : TestMoveResult
data object TestMoveFailure : TestMoveResult

interface TestGameRunner {

    fun withBoard(board: TestBoard): TestGameRunner

    fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult

}