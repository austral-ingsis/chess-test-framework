# Knight movement

# Size
width = 8
height = 8

# Starting board
```
   a  b  c  d  e  f  g  h
1 |  |WN|  |  |  |  |  |WK|
2 |WP|WP|WP|WP|WP|WP|WP|WP|
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |  |  |  |  |
5 |  |  |  |  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |BP|BP|BP|BP|BP|BP|BP|BP|
8 |BK|  |  |  |  |BN|  |  |
```
# Moves
1. b1-c3
2. f8-e6
3. c3-e4
4. e6-c5


# Result
`ALL_MOVES_VALID`

# Final board
```
   a  b  c  d  e  f  g  h
1 |  |  |  |  |  |  |  |WK|
2 |WP|WP|WP|WP|WP|WP|WP|WP|
3 |  |  |  |  |  |  |  |  |
4 |  |  |  |  |WN|  |  |  |
5 |  |  |BN|  |  |  |  |  |
6 |  |  |  |  |  |  |  |  |
7 |BP|BP|BP|BP|BP|BP|BP|BP|
8 |BK|  |  |  |  |  |  |  |
```