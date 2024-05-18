# Undo pawn movement still white turn

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |WK|  |  |  |
2 |  |  |  |  |WP|  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |BP|  |  |  |
8 |  |  |  |  |BK|  |  |  |
```
# Moves
1. e2-e3
2. e7-e6
3. UNDO
4. e7-e5


# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |WK|  |  |  |
2 |  |  |  |  |  |  |  |  |
3 |  |  |  |  |WP|  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |BP|  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |  |  |  |  |BK|  |  |  |
```
