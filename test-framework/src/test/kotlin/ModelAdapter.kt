import com.github.bhlangonijr.chesslib.Board
import com.github.bhlangonijr.chesslib.Piece
import com.github.bhlangonijr.chesslib.Rank
import com.github.bhlangonijr.chesslib.File
import com.github.bhlangonijr.chesslib.Square
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPiece
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize

class ModelAdapter {

    private fun positionToTestPosition(x: Int, y: Int) = TestPosition.fromZeroBased(y, x)

    private fun pieceToTestPiece(piece: Piece): TestPiece {
        return when (piece) {
            Piece.WHITE_KING ->  TestPiece('K', 'W')
            Piece.WHITE_QUEEN -> TestPiece('Q', 'W')
            Piece.WHITE_ROOK -> TestPiece('R', 'W')
            Piece.WHITE_BISHOP -> TestPiece('B', 'W')
            Piece.WHITE_KNIGHT -> TestPiece('N', 'W')
            Piece.WHITE_PAWN -> TestPiece('P', 'W')
            Piece.BLACK_KING -> TestPiece('K', 'B')
            Piece.BLACK_QUEEN -> TestPiece('Q', 'B')
            Piece.BLACK_ROOK -> TestPiece('R', 'B')
            Piece.BLACK_BISHOP -> TestPiece('B', 'B')
            Piece.BLACK_KNIGHT -> TestPiece('N', 'B')
            Piece.BLACK_PAWN -> TestPiece('P', 'B')
            else -> throw IllegalArgumentException("Invalid piece type")
        }
    }
    fun boardToTestBoard(board: Board): TestBoard {

        val pieces: List<Pair<TestPosition, TestPiece>> =  board.boardToArray()
            .toSet()
            .flatMap { piece -> board.getPieceLocation(piece).map {location  -> Pair(location, piece)}}
            .map { (location, piece) -> Triple(location.ordinal % 8, location.ordinal / 8, piece) }
            .map { (x, y, piece) -> Pair(positionToTestPosition(x, y), pieceToTestPiece(piece)) }

        return TestBoard(
            TestSize(8, 8),
            mapOf(*pieces.toTypedArray())
        )
    }

    fun testPieceToPiece(piece: TestPiece): Piece? {
        return when (piece) {
            TestPiece('K', 'W') -> Piece.WHITE_KING
            TestPiece('Q', 'W') -> Piece.WHITE_QUEEN
            TestPiece('R', 'W') -> Piece.WHITE_ROOK
            TestPiece('B', 'W') -> Piece.WHITE_BISHOP
            TestPiece('N', 'W') -> Piece.WHITE_KNIGHT
            TestPiece('P', 'W') -> Piece.WHITE_PAWN
            TestPiece('K', 'B') -> Piece.BLACK_KING
            TestPiece('Q', 'B') -> Piece.BLACK_QUEEN
            TestPiece('R', 'B') -> Piece.BLACK_ROOK
            TestPiece('B', 'B') -> Piece.BLACK_BISHOP
            TestPiece('N', 'B') -> Piece.BLACK_KNIGHT
            TestPiece('P', 'B') -> Piece.BLACK_PAWN
            else -> null
        }
    }

    fun testPositionToSquare(position: TestPosition): Square? {
        return Square.encode(Rank.fromValue("RANK_${position.row}"), File.allFiles[position.col - 1])
    }

}