package com.alexzgolinski.recipedev.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alexzgolinski.recipedev.async.InsertDbAsyncTask;
import com.alexzgolinski.recipedev.common.FirebaseService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {RecipeEntity.class}, version = RecipeDatabase.VERSION,
    exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

  private static String LOG_TAG = RecipeDatabase.class.getSimpleName();
  private static final String DATABASE_NAME = "recipe_database";

  static final int VERSION = 1;

  public abstract RecipeDao recipeDao();

  private static RecipeDatabase INSTANCE;

  public static RecipeDatabase getDatabase(final Context context) {
    Log.d(LOG_TAG, "Getting the database");
    if (INSTANCE == null) {
      synchronized (RecipeDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              RecipeDatabase.class, RecipeDatabase.DATABASE_NAME)
              // Wipes and rebuilds instead of migrating if no Migration object.
              // Migration is not part of this codelab.
              .fallbackToDestructiveMigration()
              .addCallback(sRoomDatabaseCallback)
              .build();
          Log.d(LOG_TAG, "Created new database");
        }
      }
    }
    return INSTANCE;
  }

  /**
   * Override the onOpen method to populate the database.
   * For this sample, we clear the database every time it is created or opened.
   */
  private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      Log.i(LOG_TAG, "onOpen");
      super.onOpen(db);
      // If you want to keep the data through app restarts,
      // comment out the following line.
      FirebaseService.downloadRecipes(new DatabaseOnCompleteListener(INSTANCE));
    }
  };

  /**
   * Populate the database in the background.
   */
  private static class DatabaseOnCompleteListener implements OnCompleteListener<QuerySnapshot> {

    private final RecipeDao mDao;

    DatabaseOnCompleteListener(RecipeDatabase database) {
      this.mDao = database.recipeDao();
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
      Log.i(LOG_TAG, "onComplete");
      List<RecipeEntity> recipeList = new ArrayList<>();
      if (task.isSuccessful()) {
        for (DocumentSnapshot document : task.getResult()) {
          Log.d(LOG_TAG, document.getId() + " => " + document.getData());
          RecipeEntity recipe
              = new RecipeEntity(document.getId(), (String) document.get("title"));
          recipeList.add(recipe);
        }
        new InsertDbAsyncTask(mDao).execute(recipeList, null, null);

      } else {
        Log.w(LOG_TAG, "Error getting documents.", task.getException());
      }
    }
  }
}


