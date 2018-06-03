package com.alexzgolinski.recipedev.common;

import android.util.Log;

import com.alexzgolinski.recipedev.db.RecipeEntity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

  private static String LOG_TAG = FirebaseService.class.getSimpleName();

  // TODO: move this to a config
  private static final String COLLECTION_NAME = "recipes";

  public static void downloadRecipes(OnCompleteListener<QuerySnapshot> listener) {
    Log.i(LOG_TAG, "downloadRecipes");
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    Log.i(LOG_TAG, "downloadRecipes 2");
    firestore.collection(COLLECTION_NAME)
        .get()
        .addOnCompleteListener(listener);
  }
}
