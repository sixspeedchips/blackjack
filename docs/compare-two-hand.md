# Comparing Two Hands In Blackjack

    Given two hands with one being a dealer and one a player.
    
 1. First need to generate sums for each player
    * all cards are sorted with order low to high with aces in the highest position
    * then sum over the sorted cards in the hand, treat face cards as ten and aces as eleven initially.
        * if the sum breaks eleven on an ace subtract 10
    * return the calculated sum
    
 2. Next we have the following cases:
    * Case 1: both are higher than 21
        * return dealer wins
    * Case 2: dealer > 21 and player < 21
        * return player wins
    * Case 3: dealer < 21 and player > 21
        * return dealer wins
    * Case 4: dealer same value player or the dealer more than the player
        * return dealer wins
    * Case 5: dealer less than the player
        * return the player wins
