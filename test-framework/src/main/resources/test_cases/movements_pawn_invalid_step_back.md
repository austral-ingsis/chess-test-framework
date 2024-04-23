# Pawn invalid movement backwards

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
2- e3-e2



# Result
`LAST_MOVE_INVALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |WR|WN|WB|WQ|WK|WB|WN|WR|
2 |WP|WP|WP|WP|  |WP|WP|WP|
3 |  |  |  |  |WP|  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |BP|BP|BP|BP|BP|BP|BP|BP|
8 |BR|BN|BB|BQ|BK|BB|BN|BR|
```
