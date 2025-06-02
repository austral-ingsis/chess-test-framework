# Pawn valid movements

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |  |BP|  |BK|  |  |  |  |
2 |BP|  |BP|  |  |  |BP|  |
3 |  |  |  |WP|  |  |  |  |
4 |  |  |  |  |WP|WP|  |  |
5 |  |  |  |  |BP|BP|  |  |
6 |  |  |  |BP|  |  |  |  |
7 |WP|WP|WP|  |  |  |WP|  |
8 |  |  |  |WK|  |  |  |  |
```
# Moves
1. a7-a6
2. a2-a4
3. b7-b6
4. b1-b3
5. b6-b5
6. b3-b4
7. c7-d6
8. c2-d3
9. d6-e5
10. d3-e4
11. g7-g6
12. g2-g3
13. g6-f5
14. g3-f4


# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |BK|  |  |  |  |
2 |  |  |  |  |  |  |  |  |
3 |  |  |  |  |  |  |  |  |
4 |BP|BP|  |  |BP|BP|  |  |
5 |  |WP|  |  |WP|WP|  |  |
6 |WP|  |  |  |  |  |  |  |
7 |  |  |  |  |  |  |  |  |
8 |  |  |  |WK|  |  |  |  |
```