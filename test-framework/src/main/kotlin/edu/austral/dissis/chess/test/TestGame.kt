package edu.austral.dissis.chess.test

class TestGame(
    val title: String,
    val initialBoard: TestBoard,
    val movements: List<Pair<TestPosition, TestPosition>>,
    val testResult: TestGameResult,
    val finalBoard: TestBoard
)