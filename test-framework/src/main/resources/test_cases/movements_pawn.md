# Pawn movements valid

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |WR|WN|WB|WQ|WK|WB|WN|WR|
2 |WP|WP|WP|WP|WP|WP|WP|WP|
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |BP|BP|BP|BP|BP|BP|BP|BP|
8 |BR|BN|BB|BQ|BK|BB|BN|BR|
```
# Moves
1- e2-e3
2- e7-e6
3- b2-b4
4- c7-c5



# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |WR|WN|WB|WQ|WK|WB|WN|WR|
2 |WP|  |WP|WP|  |WP|WP|WP|
3 |  |  |  |  |WP|  |  |  |
4 |  |WP|  |  |  |  |  |  |
5 |  |  |BP|  |  |  |  |  |
6 |  |  |  |  |BP|  |  |  |
7 |BP|BP|  |BP|  |BP|BP|BP|
8 |BR|BN|BB|BQ|BK|BB|BN|BR|
```
