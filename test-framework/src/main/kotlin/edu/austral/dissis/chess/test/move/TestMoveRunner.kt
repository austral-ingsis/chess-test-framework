package edu.austral.dissis.chess.test.move

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity

interface TestMoveRunner {
    fun executeMove(testBoard: TestBoard, from: TestPosition, to: TestPosition): Validity
}