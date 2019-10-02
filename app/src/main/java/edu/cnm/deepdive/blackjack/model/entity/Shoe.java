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
            entity = Card.class,
            childColumns = "marker_id",
            parentColumns = "card_id",
            onDelete = ForeignKey.NO_ACTION
        )
    }
)
public class Shoe {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "shoe_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private Date created = new Date();

  @ColumnInfo(name = "marker_id", index = true)
  private Long markerId;

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

  public Long getMarkerId() {
    return markerId;
  }

  public void setMarkerId(Long markerId) {
    this.markerId = markerId;
  }

}
