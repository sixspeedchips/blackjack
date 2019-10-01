package edu.cnm.deepdive.blackjack.model;

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
          childColumns = {"marker_id"},
          parentColumns = {"card_id"})
    }
)
public class Shoe {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "shoe_id")
  private long id;

  @ColumnInfo(index = true)
  @NonNull
  private Date create = new Date();

  @ColumnInfo(name = "marker_id",index = true)
  private Long markerId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Date getCreate() {
    return create;
  }

  public void setCreate(@NonNull Date create) {
    this.create = create;
  }

  public Long getMarkerId() {
    return markerId;
  }

  public void setMarkerId(Long markerId) {
    this.markerId = markerId;
  }
}
