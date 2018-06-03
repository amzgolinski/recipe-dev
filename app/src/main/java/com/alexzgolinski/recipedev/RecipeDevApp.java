package com.alexzgolinski.recipedev;

import android.app.Activity;
import android.app.Application;

import com.alexzgolinski.recipedev.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class RecipeDevApp extends Application implements HasActivityInjector {

  @Inject
  DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

  @Override
  public void onCreate() {
    super.onCreate();

    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this);
  }

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return dispatchingAndroidInjector;
  }

}
