package edu.austral.dissis.chess.test

import edu.austral.dissis.chess.test.game.TestInput

class TestGame(
    val title: String,
    val initialBoard: TestBoard,
    val inputs: List<TestInput>,
    val testResult: TestGameResult,
    val finalBoard: TestBoard
)