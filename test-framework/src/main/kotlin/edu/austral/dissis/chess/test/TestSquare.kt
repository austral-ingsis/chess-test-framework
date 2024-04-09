package edu.austral.dissis.chess.test

sealed interface TestSquare

data class TestExpectation(val position: TestPosition, val validity: Validity) : TestSquare
data class PieceSetup(val position: TestPosition, val piece: TestPiece) : TestSquare