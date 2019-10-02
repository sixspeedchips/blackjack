package edu.cnm.deepdive.blackjack.model.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import edu.cnm.deepdive.blackjack.model.entity.Card;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Dao
public interface CardDao {

  @Insert
  List<Long> insert(Collection<Card> cards);



}
