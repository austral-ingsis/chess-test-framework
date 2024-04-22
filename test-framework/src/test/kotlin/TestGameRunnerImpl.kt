import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Side
import com.github.bhlangonijr.chesslib.move.Move
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.game.*

class TestGameRunnerImpl(private val gameBoard: Board = Board() ) : TestGameRunner {

    private val adapter = ModelAdapter()

    override fun withBoard(board: TestBoard): TestGameRunner {
        val newBoard = Board()
        board
            .pieces
            .entries
            .forEach { (position, piece) -> newBoard.setPiece(adapter.testPieceToPiece(piece), adapter.testPositionToSquare(position)) }
        return TestGameRunnerImpl(newBoard)
    }

    override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
        val newBoard = gameBoard.clone()
        val fromSquare = adapter.testPositionToSquare(from)
        val toSquare = adapter.testPositionToSquare(to)
        return if (newBoard.doMove(Move(fromSquare, toSquare))) {
            if (newBoard.isMated){
                return if (newBoard.sideToMove == Side.WHITE){
                    BlackCheckMate(adapter.boardToTestBoard(newBoard))
                } else {
                    WhiteCheckMate(adapter.boardToTestBoard(newBoard))
                }
            }
            TestMoveSuccess(TestGameRunnerImpl(newBoard))
        } else {
            TestMoveFailure(adapter.boardToTestBoard(newBoard))
        }
    }

    override fun getBoard(): TestBoard {
        return adapter.boardToTestBoard(gameBoard)
    }


}