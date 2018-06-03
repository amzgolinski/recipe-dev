package com.alexzgolinski.recipedev.di;

import com.alexzgolinski.recipedev.ui.RecipeListActivity;
import com.alexzgolinski.recipedev.ui.RecipeListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;



@Module
public abstract class BuildersModule {

  @ContributesAndroidInjector(modules = RecipeListActivityModule.class)
  abstract RecipeListActivity bindRecipeListActivity();

  // Add bindings for other sub-components here
  @ContributesAndroidInjector(modules = RecipeListFragmentModule.class)
  abstract RecipeListFragment bindRecipeListFragment();
}
