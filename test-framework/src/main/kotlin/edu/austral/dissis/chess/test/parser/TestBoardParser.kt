package edu.austral.dissis.chess.test.parser

import edu.austral.dissis.chess.test.PieceSetup
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestExpectation
import edu.austral.dissis.chess.test.TestPiece
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize
import edu.austral.dissis.chess.test.TestSquare
import edu.austral.dissis.chess.test.Validity
import edu.austral.dissis.chess.test.Validity.Companion.isJustValidity

class TestBoardParser {
    companion object {
        private const val SQUARE_DELIMITER = '|'

    }

    fun parse(boardContent: String, parseSettings: ParseSettings): Pair<TestBoard, List<TestExpectation>> {
        val boardLines = boardContent.lines()
            // First line is free text
            .drop(1)

        val squareContents: Map<TestPosition, String> = parseMatrix(boardLines)

        val indications = squareContents.flatMap { parseSquare(it.key, it.value, parseSettings) }
        val size = calculateSize(squareContents)
        val board = createBoard(size, indications)

        return Pair(board, indications.filterIsInstance<TestExpectation>())
    }


    private fun calculateSize(squares: Map<TestPosition, String>): TestSize {
        val rows = squares.keys.maxOfOrNull { it.row + 1 } ?: 0
        val cols = squares.keys.maxOfOrNull { it.col + 1 } ?: 0

        return TestSize(rows, cols)
    }

    private fun createBoard(size: TestSize, indications: List<TestSquare>): TestBoard {
        val pieceMap = indications
            .filterIsInstance<PieceSetup>()
            .associate { Pair(it.position, it.piece) }
        return TestBoard(size, pieceMap)
    }

    private fun parseMatrix(boardLines: List<String>): Map<TestPosition, String> {
        return boardLines
            .flatMapIndexed { rowIndex, rowText ->
                parseRow(rowText)
                    .mapIndexed { colIndex, squareText ->
                        val position = TestPosition.fromZeroBased(rowIndex, colIndex)
                        Pair(position, squareText)
                    }
            }.toMap()
    }

    private fun parseRow(boardRowString: String): List<String> {
        val rowWithoutPrefix = boardRowString.dropWhile { it != SQUARE_DELIMITER }
        val squaresText = parseSquaresText(rowWithoutPrefix)
        return squaresText
    }

    private fun parseSquaresText(rowWithoutNumber: String): List<String> =
        rowWithoutNumber.split(SQUARE_DELIMITER).drop(1)

    private fun parseSquare(
        position: TestPosition,
        squareText: String,
        parseSettings: ParseSettings
    ): List<TestSquare> {
        val validity = Validity.extractSquareValidity(squareText) ?: parseSettings.defaultValidity
        val expectations = validity?.let { listOf(TestExpectation(position, it)) } ?: emptyList()

        val piece = if (squareText.isNotBlank() && !isJustValidity(squareText)) {
            parsePiece(squareText)
        } else null
        val pieceSetups = piece?.let { listOf(PieceSetup(position, it)) } ?: emptyList()

        return expectations + pieceSetups
    }

    private fun parsePiece(text: String): TestPiece = TestPiece(text[1], text[0])
}