package edu.austral.dissis.chess.test

data class TestPiece(val pieceTypeSymbol: Char, val playerColorSymbol: Char)

object TestPieceSymbols {
    // Player colors
    const val BLACK = 'B'
    const val WHITE = 'W'

    // Base pieces
    const val KING = 'K'
    const val PAWN = 'P'
    const val BISHOP = 'B'
    const val ROOK = 'R'
    const val KNIGHT = 'N'
    const val QUEEN = 'Q'

    // Extension pieces
    const val ARCHBISHOP = 'A'
    const val CHANCELLOR = 'C'
}