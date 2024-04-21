package edu.austral.dissis.chess.test.parserUtils

import edu.austral.dissis.chess.test.TestPosition

class MatrixParser {

    companion object {
        private const val SQUARE_DELIMITER = '|'

    }
    fun parseMatrix(boardLines: List<String>): Map<TestPosition, String> {
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

}