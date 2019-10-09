package edu.cnm.deepdive.blackjack.viewmodel;


import android.app.Application;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.blackjack.model.dao.CardDao;
import edu.cnm.deepdive.blackjack.model.entity.Card;
import edu.cnm.deepdive.blackjack.model.entity.Card.Rank;
import edu.cnm.deepdive.blackjack.model.entity.Card.Suit;
import edu.cnm.deepdive.blackjack.model.entity.Hand;
import edu.cnm.deepdive.blackjack.model.entity.Round;
import edu.cnm.deepdive.blackjack.model.entity.Shoe;
import edu.cnm.deepdive.blackjack.model.pojo.HandWithCards;
import edu.cnm.deepdive.blackjack.model.pojo.RoundWithDetails;
import edu.cnm.deepdive.blackjack.service.BlackjackDatabase;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainViewModel extends AndroidViewModel {

  private final BlackjackDatabase db;
  private long shoeId;
  private long markerId;
  private boolean shuffleNeeded;

  private Random rng;


  private MutableLiveData<Long> roundId;
  private LiveData<RoundWithDetails> round;
  private MutableLiveData<Long> dealerHandId;
  private MutableLiveData<Long> playerHandId;
  private LiveData<HandWithCards> dealerHand;
  private LiveData<HandWithCards> playerHand;


  public MainViewModel(@NonNull Application application) {
    super(application);
    rng = new SecureRandom();
    db = BlackjackDatabase.getInstance();
    roundId = new MutableLiveData<>();
    round = Transformations.switchMap(roundId,
        (id) -> db.getRoundDao().getRoundWithDetails(id));
    dealerHandId = new MutableLiveData<>();
    playerHandId = new MutableLiveData<>();
    dealerHand = Transformations
        .switchMap(dealerHandId, (id) -> db.getHandDao().getHandWithCards(id));
    playerHand = Transformations
        .switchMap(playerHandId, (id) -> db.getHandDao().getHandWithCards(id));


  }

  public LiveData<RoundWithDetails> getRound() {
    return round;
  }

  private void createShoe() {
    Shoe shoe = new Shoe();

    shoeId = db.getShoeDao().insert(shoe);

    List<Card> cards = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      for (Rank rank : Rank.values()) {
        for (Suit suit : Suit.values()) {
          Card card = new Card();
          card.setShoeId(shoeId);
          card.setRank(rank);
          card.setSuit(suit);
          cards.add(card);
        }
      }
    }
    Collections.shuffle(cards);
    int startIndex = cards.size() * 2 / 3;
    int endIndex = cards.size() * 3 / 4;
    int markerIndex = startIndex + rng.nextInt(endIndex - startIndex);

    List<Long> cardsIds = db.getCardDao().insert(cards);
    markerId = cardsIds.get(markerIndex);
    shoe.setMarkerId(markerId);
    db.getShoeDao().update(shoe);
    shuffleNeeded = false;
  }

  public void startRound() {

    new Thread(() ->
    {
      Round round = new Round();
      if (shoeId == 0 || shuffleNeeded) {
        createShoe();
      }
      round.setShoeId(shoeId);
      long roundId = db.getRoundDao().insert(round);
      Hand dealer = new Hand();
      Hand player = new Hand();

      dealer.setRoundId(roundId);
      player.setRoundId(roundId);
      dealer.setDealer(true);
      long[] handIds = db.getHandDao().insert(dealer, player);
      for (long handId : handIds) {
        for (int i = 0; i < 2; i++) {
          Card card = getTopCardInShoe(handId);
        }
      }
      this.roundId.postValue(roundId);
      this.dealerHandId.postValue(handIds[0]);
      this.playerHandId.postValue(handIds[1]);
    }).start();
  }

  private Card getTopCardInShoe(long handId) {

    CardDao cardDao = db.getCardDao();

    Card card = cardDao.getTopCardInShoe(shoeId);
    card.setShoeId(null);
    card.setHandId(handId);
    cardDao.update(card);

    if(card.getId()==markerId){
      shuffleNeeded = true;
    }
    return card;
  }

  public void hitPlayer() {

    new Thread(() -> {

      long handIdValue = playerHandId.getValue();
      Card card = getTopCardInShoe(handIdValue);
      playerHandId.postValue(handIdValue);
    }).start();

  }

  public void hitDealer() {

  }

  public LiveData<HandWithCards> getDealerHand() {
    return dealerHand;
  }

  public LiveData<HandWithCards> getPlayerHand() {
    return playerHand;
  }

}
