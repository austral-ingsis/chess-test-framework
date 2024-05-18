import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Side
import com.github.bhlangonijr.chesslib.move.Move
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.game.*

class TestGameRunnerImpl : TestGameRunner {

    private val adapter = ModelAdapter()
    private var gameBoard = Board()
    private var history = RunnersHistory()

    constructor() {
        val newBoard = Board()
        newBoard.clear()
        this.gameBoard = newBoard
    }

    private constructor(gameBoard: Board, history: RunnersHistory) : this() {
        this.gameBoard = gameBoard
        this.history = history
    }

    fun withHistory(history: RunnersHistory): TestGameRunnerImpl {
        return TestGameRunnerImpl(gameBoard, history)
    }

    override fun withBoard(board: TestBoard): edu.austral.dissis.chess.test.game.TestGameRunner {
        val newBoard = Board()
        newBoard.clear()
        board
            .pieces
            .entries
            .forEach { (position, piece) ->
                newBoard.setPiece(
                    adapter.testPieceToPiece(piece),
                    adapter.testPositionToSquare(position)
                )
            }
        return TestGameRunnerImpl(newBoard, RunnersHistory())
    }

    override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
        val newBoard = gameBoard.clone()
        val fromSquare = adapter.testPositionToSquare(from)
        val toSquare = adapter.testPositionToSquare(to)
        return if (newBoard.doMove(Move(fromSquare, toSquare), true)) {
            if (newBoard.isMated) {
                return if (newBoard.sideToMove == Side.WHITE) {
                    BlackCheckMate(adapter.boardToTestBoard(newBoard))
                } else {
                    WhiteCheckMate(adapter.boardToTestBoard(newBoard))
                }
            }
            TestMoveSuccess(TestGameRunnerImpl(newBoard, history.add(this)))
        } else {
            TestMoveFailure(adapter.boardToTestBoard(newBoard))
        }
    }

    override fun undo(): TestMoveResult {
        return TestMoveSuccess(history.undo(this))
    }

    override fun redo(): TestMoveResult {
        return TestMoveSuccess(history.redo())
    }

    override fun getBoard(): TestBoard {
        return adapter.boardToTestBoard(gameBoard)
    }


}