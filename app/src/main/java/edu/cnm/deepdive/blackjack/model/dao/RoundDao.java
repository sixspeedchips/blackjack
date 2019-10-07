package edu.cnm.deepdive.blackjack.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.blackjack.model.entity.Round;
import java.util.List;

@Dao
public interface RoundDao {

  @Insert
  long insert(Round round);



  @Query("SELECT * FROM round ORDER BY created ASC")
  LiveData<List<Round>> getAll();

  @Query("SELECT * FROM round WHERE shoe_id = :shoeId ORDER BY created ASC")
  LiveData<List<Round>> getShoeId(long shoeId);

  @Query("SELECT * FROM round WHERE round_id=:roundId")
  LiveData<Round> getSingleRound(long roundId);




}
