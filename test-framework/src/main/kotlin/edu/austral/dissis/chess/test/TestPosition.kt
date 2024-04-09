package edu.austral.dissis.chess.test

data class TestPosition(
    /**
     * 1 based value row index
     */
    val row: Int,
    /**
     * 1 based value colum index
     */
    val col: Int
) {
    companion object {
        fun fromZeroBased(row: Int, col: Int): TestPosition = TestPosition(row + 1, col + 1)
    }
}