package edu.austral.dissis.chess.test

enum class Validity(val serializationText: String, val serializationSuffix: String) {
    VALID("OO", "O"),
    INVALID("XX", "X");

    companion object {
        fun extractSquareValidity(squareText: String): Validity? = entries
            .find { squareText == it.serializationText || squareText.endsWith(it.serializationSuffix) }

        fun isJustValidity(squareText: String): Boolean = entries
            .any { squareText == it.serializationText }
    }
}