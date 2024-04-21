package edu.austral.dissis.chess.test.game

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition

sealed interface TestMoveResult
data class TestMoveSuccess(val testGameRunner: TestGameRunner) : TestMoveResult
data class TestMoveDraw(val finalBoard: TestBoard) : TestMoveResult
data class WhiteCheckMate(val finalBoard: TestBoard) : TestMoveResult
data class BlackCheckMate(val finalBoard: TestBoard) : TestMoveResult
data class TestMoveFailure(val finalBoard: TestBoard) : TestMoveResult

interface TestGameRunner {

    fun withBoard(board: TestBoard): TestGameRunner

    fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult

    fun getBoard(): TestBoard
}