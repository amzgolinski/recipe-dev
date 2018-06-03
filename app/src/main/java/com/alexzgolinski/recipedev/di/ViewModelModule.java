package com.alexzgolinski.recipedev.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.alexzgolinski.recipedev.viewmodel.RecipeViewModel;
import com.alexzgolinski.recipedev.viewmodel.RecipeViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(RecipeViewModel.class)
  abstract ViewModel bindRecipeViewModel(RecipeViewModel recipeViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(RecipeViewModelFactory factory);

}