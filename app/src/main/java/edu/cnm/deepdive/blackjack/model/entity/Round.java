package edu.cnm.deepdive.blackjack.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Shoe.class,
            parentColumns = {"shoe_id"},
            childColumns = {"shoe_id"},
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Round {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "round_id")
  private long id;

  @NonNull
  private Date created = new Date();

  @ColumnInfo(name = "shoe_id", index = true)
  private long shoeId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  public long getShoeId() {
    return shoeId;
  }

  public void setShoeId(long shoeId) {
    this.shoeId = shoeId;
  }

}
