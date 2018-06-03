package com.alexzgolinski.recipedev.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.alexzgolinski.recipedev.data.RecipeRepository;
import com.alexzgolinski.recipedev.db.RecipeEntity;

import java.util.List;

import javax.inject.Inject;


public class RecipeViewModel extends ViewModel {

  private LiveData<List<RecipeEntity>> mRecipes;
  private RecipeRepository mRecipeRepository;

  @Inject // provided by Dagger 2
  public RecipeViewModel(RecipeRepository recipeRepository) {
    this.mRecipeRepository = recipeRepository;
  }

  public void init() {
    if (this.mRecipes != null) {
      // ViewModel is created per Fragment
      return;
    }
    this.mRecipes = mRecipeRepository.getRecipes();
    //mRecipeRepository.downloadRecipes(mRecipes);
  }

  public LiveData<List<RecipeEntity>> getRecipes() {
    return this.mRecipes;
  }
}
