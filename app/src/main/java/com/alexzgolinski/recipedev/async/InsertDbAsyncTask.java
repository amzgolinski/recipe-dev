package com.alexzgolinski.recipedev.async;

import android.os.AsyncTask;
import android.util.Log;

import com.alexzgolinski.recipedev.db.RecipeDao;
import com.alexzgolinski.recipedev.db.RecipeEntity;

import java.util.List;

public class InsertDbAsyncTask extends AsyncTask<List<RecipeEntity>, Void, Void> {

  private final String LOG_TAG = InsertDbAsyncTask.class.getSimpleName();

  private final RecipeDao mDao;

  public InsertDbAsyncTask(RecipeDao recipeDao) {
    this.mDao = recipeDao;
  }

  protected Void doInBackground(List<RecipeEntity>... recipeList) {
    mDao.insertAll(recipeList[0]);
    return null;
  }

  protected void onPostExecute(Void result) {
    Log.i(LOG_TAG, "Inserted rows into database.");
  }
}
