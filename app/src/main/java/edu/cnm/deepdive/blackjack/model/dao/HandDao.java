package edu.cnm.deepdive.blackjack.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.blackjack.model.entity.Hand;
import edu.cnm.deepdive.blackjack.model.pojo.HandWithCards;
import java.util.List;


@Dao
public interface HandDao {


  @Insert
  long[] insert(Hand... hands);

  @Query("SELECT * FROM hand")
  LiveData<List<Hand>> getAll();


  @Query("SELECT * FROM hand WHERE round_id=:roundId ORDER BY dealer DESC")
  LiveData<List<Hand>> getAllByRoundId(long roundId);

  @Query("SELECT * FROM hand WHERE hand_id=:handId")
  LiveData<Hand> getHandById(long handId);

  @Query("SELECT * FROM hand WHERE hand_id=:handId")
  LiveData<HandWithCards> getHandWithCards(long handId);

  @Update
  int update(Hand hand);

}
