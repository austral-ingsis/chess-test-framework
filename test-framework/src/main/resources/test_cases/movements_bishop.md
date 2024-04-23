# Bishop valid movement

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |  |WB|  |  |  |  |  |WK|
2 |  |  |  |  |  |  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |BK|  |  |  |  |  |BB|  |
```
# Moves
1. b1-d3
2. g8-e6
3. d3-f1
4. e6-c8
5. f1-d3
6. c8-e6
7. d3-c2
8. e6-f7


# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |  |  |  |WK|
2 |  |  |WB|  |  |  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |BB|  |  |
8 |BK|  |  |  |  |  |  |  |
```