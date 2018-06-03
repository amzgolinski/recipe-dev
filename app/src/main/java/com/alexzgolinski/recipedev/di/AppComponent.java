package com.alexzgolinski.recipedev.di;

import android.app.Application;

import com.alexzgolinski.recipedev.RecipeDevApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    BuildersModule.class
})
public interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(RecipeDevApp application);
    AppComponent build();
  }
  void inject(RecipeDevApp application);
}
