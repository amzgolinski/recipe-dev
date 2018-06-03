package com.alexzgolinski.recipedev.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void save(RecipeEntity recipe);

  @Query("SELECT * FROM recipes WHERE id = :recipeId")
  LiveData<RecipeEntity> getRecipe(String recipeId);

  @Query("SELECT * FROM recipes ORDER BY title ASC")
  LiveData<List<RecipeEntity>> getAllRecipes();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(List<RecipeEntity> products);

  @Query("DELETE FROM recipes")
  void deleteAllRecipes();

}
