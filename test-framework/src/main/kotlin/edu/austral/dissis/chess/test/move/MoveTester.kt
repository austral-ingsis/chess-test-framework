package edu.austral.dissis.chess.test.move

import edu.austral.dissis.chess.test.parser.ParseSettings
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.parser.TestBoardParser
import edu.austral.dissis.chess.test.TestExpectation
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.fail

class MoveTester(val runner: TestMoveRunner) {
    private val parser: TestBoardParser = TestBoardParser()

    fun testMove(resource: String, from: TestPosition, parseSettings: ParseSettings) {
        val content = content(resource) ?: fail("$resource not found in classpath")
        val (board, expectations) = parser.parse(content, parseSettings)

        assertValidMoves(board, from, expectations)
        assertInvalidMoves(board, from, expectations)
    }

    private fun assertValidMoves(board: TestBoard, from: TestPosition, expectations: List<TestExpectation>) {
        val validMoves = expectations.filter { it.validity == Validity.VALID }

        val invalidPositions = validMoves
            .map { Pair(it.position, runner.executeMove(board, from, it.position)) }
            .filter { it.second == Validity.INVALID }
            .map { it.first }

        assertTrue(invalidPositions.isEmpty()) {
            "There are invalid position that should be valid: ${invalidPositions.joinToString()}}"
        }
    }

    private fun assertInvalidMoves(board: TestBoard, from: TestPosition, expectations: List<TestExpectation>) {
        val invalidMoves = expectations.filter { it.validity == Validity.INVALID }

        val validPositions = invalidMoves
            .map { Pair(it.position, runner.executeMove(board, from, it.position)) }
            .filter { it.second == Validity.VALID }
            .map { it.first }

        assertTrue(validPositions.isEmpty()) {
            "There are valid position that should be invalid: ${validPositions.joinToString()}}"
        }
    }

    private fun content(resource: String): String? {
        return this::class.java
            .getResourceAsStream(resource)
            ?.bufferedReader()
            ?.readLines()
            ?.joinToString("\n")
    }
}