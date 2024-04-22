package edu.austral.dissis.chess.test.gameParser

import edu.austral.dissis.chess.test.*
import edu.austral.dissis.chess.test.parserUtils.MatrixParser

class GameBoardParser {
    companion object {
        private val matrixParser = MatrixParser()
    }

    fun parse(gameContent: String): TestGame {
        val lines = gameContent.lines()

        val title = parseTitle(lines)
        val size = parseSize(lines)
        val board = parseStartingBoard(lines, size)
        val movements = parseMoves(lines)
        val result = parseResult(lines)
        val finalBoard = parseFinalBoard(lines, size)

        return TestGame(
            title,
            board,
            movements,
            result,
            finalBoard
        )
    }

    private fun parseTitle(lines:List<String>): String {
        /*
        * Format is:
        * # Title
        * */
        val line = lines.get(0)
        return line.replace("#", "").trim()
    }

    private fun parseSize(lines: List<String>): TestSize {
        /*
        * Format is:
        *
        * # Size
        * width = 8
        * height = 8
        * */
        val headerLine = lines.indexOfFirst { it.startsWith("# Size") }
        val width = lines[headerLine + 1].split("=")[1].trim().toInt()
        val height = lines[headerLine + 2].split("=")[1].trim().toInt()
        return TestSize(height, width)
    }

    private fun parseStartingBoard(lines: List<String>, size: TestSize): TestBoard {
        /*
        * Format is:
        *
        # Starting board
        ```
           a  b  c  d  e  f  g  h
        8 |BR|BN|BB|BQ|BK|BB|BN|BR|
        7 |BP|BP|BP|BP|BP|BP|BP|BP|
        6 |  |  |  |  |  |  |  |  |
        5 |  |  |  |  |  |  |  |  |
        4 |  |  |  |  |  |  |  |  |
        3 |  |  |  |  |  |  |  |  |
        2 |WP|WP|WP|WP|WP|WP|WP|WP|
        1 |WR|WN|WB|WQ|WK|WB|WN|WR|
        ```
        */
        return parseBoard("# Starting board", lines, size)
    }

    private fun parseFinalBoard(lines: List<String>, size: TestSize): TestBoard {
        /*
        * Format is:
        *
        # Final board
        ```
           a  b  c  d  e  f  g  h
        8 |BR|BN|BB|BQ|BK|BB|BN|BR|
        7 |BP|BP|BP|BP|BP|BP|BP|BP|
        6 |  |  |  |  |  |  |  |  |
        5 |  |  |  |  |  |  |  |  |
        4 |  |  |  |  |  |  |  |  |
        3 |  |  |  |  |  |  |  |  |
        2 |WP|WP|WP|WP|WP|WP|WP|WP|
        1 |WR|WN|WB|WQ|WK|WB|WN|WR|
        ```
        */
        return parseBoard("# Final board", lines, size)
    }

    private fun parseBoard(
        header: String,
        lines: List<String>,
        size: TestSize
    ): TestBoard {
        val headerLine = lines.indexOfFirst { it.startsWith(header) }
        val boardLines = lines.subList(headerLine + 3, headerLine + 3 + size.rows)
        val squareContents: Map<TestPosition, String> = matrixParser.parseMatrix(boardLines)
        val pieces = squareContents.flatMap { parseSquare(it.key, it.value) }
        return createBoard(size, pieces)
    }

    private fun parseMoves(lines: List<String>): List<Pair<TestPosition, TestPosition>> {
        /*
        * Format is:

        # Moves
        1. e2 e4
        2. e7 e5
        3. g1 f3

        * */
        val headerLine = lines.indexOfFirst { it.startsWith("# Moves") }
        val nextEmptyLine = lines
            .subList(headerLine, lines.size)
            .indexOfFirst { it.isBlank() }
        val moves = lines.subList(headerLine + 1, headerLine + nextEmptyLine)
        return moves
            .map { it.split(" ")[1] }
            .map { it.split("-") }
            .map { Pair(TestPosition.fromAlgebraic(it[0]), TestPosition.fromAlgebraic(it[1])) }
    }

    private fun parseResult(lines: List<String>): TestGameResult {
        /*
        * Format is:
        # Result
        `WHITE_MATE`
        * */
        val headerLine = lines.indexOfFirst { it.startsWith("# Result") }
        val result = lines[headerLine + 1]
        return TestGameResult.valueOf(result.replace('`', ' ').trim())
    }

    private fun createBoard(size: TestSize, indications: List<PieceSetup>): TestBoard {
        val pieceMap = indications
            .associate { Pair(it.position, it.piece) }
        return TestBoard(size, pieceMap)
    }

    private fun parseSquare(
        position: TestPosition,
        squareText: String,
    ): List<PieceSetup> {

        val piece = if (squareText.isNotBlank()) {
            parsePiece(squareText)
        } else null
        return piece?.let { listOf(PieceSetup(position, it)) } ?: emptyList()
    }

    private fun parsePiece(text: String): TestPiece = TestPiece(text[1], text[0])
}