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

        // positions can be: a4, h6, c3, etc
        fun fromAlgebraic(position: String): TestPosition {
            val col = position[0] - 'a' + 1
            val row = position[1] - '1' + 1
            return TestPosition(row, col)
        }
    }
}