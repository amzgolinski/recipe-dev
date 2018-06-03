package com.alexzgolinski.recipedev.data;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alexzgolinski.recipedev.db.RecipeDao;
import com.alexzgolinski.recipedev.db.RecipeEntity;
import com.alexzgolinski.recipedev.model.Recipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RecipeRepository {

  private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

  private RecipeDao recipeDao;
  private LiveData<List<RecipeEntity>> mRecipes;

  @Inject
  public RecipeRepository(RecipeDao recipeDao) {
    Log.i(LOG_TAG, "CTR");
    this.recipeDao = recipeDao;
    this.mRecipes = this.recipeDao.getAllRecipes();
  }

  public LiveData<List<RecipeEntity>> getRecipes() {
    Log.i(LOG_TAG, "getRecipes");

    /*
    LiveData<List<RecipeEntity>> recipeData = recipeDao.getAllRecipes();
    if (recipeData.getValue().size() == 0) {
      downloadRecipes();
      recipeData = recipeDao.getAllRecipes();
    }
    */

    return mRecipes;
  }

}
