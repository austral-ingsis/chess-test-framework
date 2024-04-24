package edu.austral.dissis.chess.test

data class TestBoard(val size: TestSize, val pieces: Map<TestPosition, TestPiece>) {
    override fun toString(): String {
        val builder = StringBuilder()
        val maxColumn = 8  // Assuming an 8x8 board, adjust if needed
        val maxRow = 8     // Assuming an 8x8 board, adjust if needed
        // Top-down, since row 8 is at the top for standard chess boards
        for (column in 1 .. maxColumn) {
            for (row in 1..maxRow) {
                val position = TestPosition.fromZeroBased(column - 1, row - 1)
                val piece = pieces.get(position)
                val pieceChar = piece?.let {
                    // Two character representation for each piece: type and color
                    piece.toString()
                } ?: " - "  // Single dot centered in three spaces for empty squares
                // Ensure that each cell in the grid has the same width
                builder.append(pieceChar.padEnd(3, ' '))
            }
            builder.append("\n")
        }
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        return this.toString() == other.toString()
    }

    override fun hashCode(): Int {
        var result = size.hashCode()
        result = 31 * result + pieces.hashCode()
        return result
    }
}