package edu.cnm.deepdive.blackjack.model.pojo;

import androidx.room.Ignore;
import androidx.room.Relation;
import edu.cnm.deepdive.blackjack.model.entity.Card;
import edu.cnm.deepdive.blackjack.model.entity.Card.Rank;
import edu.cnm.deepdive.blackjack.model.entity.Hand;
import java.util.List;

public class HandWithCards extends Hand {

  @Ignore
  private boolean hasAce;


  @Relation(entity = Card.class, entityColumn = "hand_id", parentColumn = "hand_id")
  private List<Card> cards;

  public List<Card> getCards(){
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public int getHardValue(){
    int sum = 0;
    for (Card card : cards) {
      if(card.getRank()== Rank.ACE){
        hasAce = true;
      }
      sum += Math.min(card.getRank().ordinal(),9) + 1;
    }

    return sum;
  }


  public int getSoftValue() {
    int hardValue = getHardValue();
    return hardValue + ((hasAce && hardValue < 12) ? 10 : 0);

  }
}
