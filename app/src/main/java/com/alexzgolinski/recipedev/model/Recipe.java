package com.alexzgolinski.recipedev.model;


public class Recipe {

  private String mId;
  private String mTitle;

  public Recipe(String id, String title) {
    this.mId = id;
    this.mTitle = title;
  }

  public String getId() {
    return this.mId;
  }

  public String getTitle() {
    return this.mTitle;
  }


}
