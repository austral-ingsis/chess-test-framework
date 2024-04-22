package edu.austral.dissis.chess.test.game

import edu.austral.dissis.chess.test.*
import edu.austral.dissis.chess.test.gameParser.GameBoardParser
import org.junit.jupiter.api.DynamicTest
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.test.fail

class GameTester(private val runner: TestGameRunner) {

    private val parser: GameBoardParser = GameBoardParser()

    fun test(): Stream<DynamicTest> {
       return getTestPaths().stream().map { gameTest(it) }
    }

    private fun gameTest(resource: String): DynamicTest {

        val content = content(resource) ?: fail("$resource not found in classpath")
        val testGame = parser.parse(content)

        return DynamicTest.dynamicTest(testGame.title) {
            when (testGame.testResult) {
                TestGameResult.ALL_MOVES_VALID -> assertAllMovesValid(testGame)
                TestGameResult.LAST_MOVE_INVALID -> assertLastMove(testGame) { it is TestMoveFailure }
                TestGameResult.WHITE_MATE -> assertLastMove(testGame) { it is WhiteCheckMate }
                TestGameResult.BLACK_MATE -> assertLastMove(testGame) { it is BlackCheckMate }
                TestGameResult.DRAW -> assertLastMove(testGame) { it is TestMoveDraw }
            }
        }
    }

    private fun runMoves(title: String, runner: TestGameRunner, moves: List<Pair<TestPosition, TestPosition>>): TestGameRunner {
        return moves.fold(runner) { currentRunner, (from, to) ->
            when (val result = currentRunner.executeMove(from, to)) {
                is TestMoveSuccess -> result.testGameRunner
                else -> fail("$title failed with move from $from to $to should be valid but was not")
            }
        }
    }

    private fun assertAllMovesValid(testGame: TestGame) {
        val initialRunner = runner.withBoard(testGame.initialBoard)
        val resultingRunner = runMoves(testGame.title, initialRunner, testGame.movements)
        checkFinalBoardMatches(resultingRunner.getBoard(), testGame.finalBoard)
    }

    private fun assertLastMove(testGame: TestGame, checkResult: (TestMoveResult) -> Boolean) {
        val initialRunner = runner.withBoard(testGame.initialBoard)
        val preparatoryMoves = testGame.movements.dropLast(1)
        val lastMove = testGame.movements.last()
        val finalRunner = runMoves(testGame.title, initialRunner, preparatoryMoves)
        val result = finalRunner.executeMove(lastMove.first, lastMove.second)
        if (!checkResult(result)) {
            fail("$testGame.title failed, last move did not result in expected outcome")
        }
        checkFinalBoardMatches(finalRunner.getBoard(), testGame.finalBoard)
    }

    private fun checkFinalBoardMatches(actualBoard: TestBoard, expectedBoard: TestBoard) {
        if(actualBoard != expectedBoard){
            fail("$actualBoard did not match expected board $expectedBoard")
        }
    }

    private fun content(resource: String): String? {
        return this::class.java
            .getResourceAsStream(resource)
            ?.bufferedReader()
            ?.readLines()
            ?.joinToString("\n")
    }

    private fun getTestPaths(): List<String> {
        val resourcePath = "test_cases/"
        val uri = Thread.currentThread().contextClassLoader.getResource(resourcePath)?.toURI()
        val testPaths = mutableListOf<String>()
        uri?.let {
            val path: Path = if (uri.scheme == "jar") {
                // For JAR file, create a file system if not created
                FileSystems.newFileSystem(uri, emptyMap<String, Any>()).getPath(resourcePath)
            } else {
                // For files directly on the file system
                Paths.get(uri)
            }

            // Stream the paths under the directory and process each file
            Files.walk(path, 1).use { paths ->
                paths.filter { p -> Files.isRegularFile(p) }
                    .forEach { p: Path ->
                        testPaths.add("/test_cases/${p.fileName}")
                    }
            }
        } ?: println("Resource not found: $resourcePath")

        return testPaths
    }


}

