## Rock Paper Scissors game
### Prerequisites
This project implementation requires `Java 8` and `Maven 3`.
### Technical notes
#### Configuring and tuning
This Rock-Paper-Scissors implementation currently supports the following strategies:
 * `Rotation strategy` (last player's move is "rotated by 1" according to the rotation table, i.e., last player's move is responded next round with corresponding winning item. E.g., if last round player played `ROCK`, this round computer will play `PAPER`)
 * `Weighted strategy` (player's moves are being assigned weighted factor which determines popular items the player use and corresponding winning item is played in response)
 * `Plain pattern strategy` (player's moves are being analyzed for repeatable patterns which are being revealed then, his/her further moves are predicted and corresponding winning items are played by computer)
 * `Pair pattern strategy` (same as previous strategy, but the player moves and corresponding computer responses are considered as unique moves and patterns are being sought within them)

These strategies are configured in `com.wowapp.Engine.Engine()`. It is not recommended to use all of them or many of them due to the meta-strategy logic (or better say strategy choosing logic). The main approach in choosing the current strategy is simple. First several rounds (`com.wowapp.Engine.ROUNDS_PER_STRATEGY`) each strategy is being tested sequentially and after all of them are tested, the success scores are calculated. Then the most successful strategy is chosen. When the computer loses several rounds in a row (`com.wowapp.Engine.MAXIMUM_ALLOWED_LOSES`), then the decision to choose a new strategy is taken.   
 
### Building and running
All you need to run this game is 
```bash
$ mvn test exec:java

```
All necessary controls are printed on game start. Have fun.