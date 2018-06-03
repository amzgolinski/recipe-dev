package com.alexzgolinski.recipedev.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "recipes")
public class RecipeEntity {

  @PrimaryKey
  @NonNull
  private String id;
  private String title;

  public RecipeEntity(String id, String title) {
    this.id = id;
    this.title = title;
  }

  @NonNull
  public String getId() {
    return this.id;
  }

  public void setId(@NonNull String id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
