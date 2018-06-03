package com.alexzgolinski.recipedev.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.alexzgolinski.recipedev.RecipeDevApp;
import com.alexzgolinski.recipedev.data.RecipeRepository;
import com.alexzgolinski.recipedev.db.RecipeDao;
import com.alexzgolinski.recipedev.db.RecipeDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
class AppModule {


  @Singleton
  @Provides
  Context provideContext(RecipeDevApp application) {
    return application.getApplicationContext();
  }

  @Singleton
  @Provides
  RecipeRepository providesRepository(RecipeDevApp app) {
    RecipeDatabase database = RecipeDatabase.getDatabase(app.getApplicationContext());
    return new RecipeRepository(database.recipeDao());
  }

  @Singleton
  @Provides
  RecipeDatabase providesRecipeDatabase(RecipeDevApp app) {
    return RecipeDatabase.getDatabase(app.getApplicationContext());
  }

  @Singleton
  @Provides
  RecipeDao providesProductDao(RecipeDatabase recipeDatabase) {
    return recipeDatabase.recipeDao();
  }


}
