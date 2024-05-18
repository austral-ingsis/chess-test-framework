# 4 redos

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
3. e3-e4
4. e6-e5
5. UNDO
6. UNDO
7. UNDO
8. UNDO
9. REDO
10. REDO
11. REDO
12. REDO

# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |WK|  |  |  |
2 |  |  |  |  |  |  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |WP|  |  |  |
5 |  |  |  |  |BP|  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |  |  |  |  |BK|  |  |  |
```
