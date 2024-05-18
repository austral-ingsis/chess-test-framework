package edu.austral.dissis.chess.test.game

import edu.austral.dissis.chess.test.TestPosition

sealed interface TestInput {
}

data class TestMove(val from: TestPosition, val to: TestPosition) : TestInput

data object Undo : TestInput

data object Redo : TestInput

