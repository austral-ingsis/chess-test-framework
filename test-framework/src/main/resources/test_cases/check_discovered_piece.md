# Discovered Piece Check

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |WR|  |  |  |  |  |  |WK|
2 |  |  |  |  |  |  |  |  |
3 |  |  |  |  |  |  |  |BN|
4 |  |  |  |  |  |  |  |BR|
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |  |BK|  |  |  |  |  |  |
```
# Moves
1. a1-a2
2. h3-f4 
3. a2-a1


# Result
`LAST_MOVE_INVALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |  |  |  |WK|
2 |WR|  |  |  |  |  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |BN|  |BR|
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |  |BK|  |  |  |  |  |  |
```