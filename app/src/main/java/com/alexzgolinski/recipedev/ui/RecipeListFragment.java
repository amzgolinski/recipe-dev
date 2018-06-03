package com.alexzgolinski.recipedev.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexzgolinski.recipedev.adapter.RecipeAdapter;
import com.alexzgolinski.recipedev.dummy.DummyContent;
import com.alexzgolinski.recipedev.model.Recipe;
import com.alexzgolinski.recipedev.viewmodel.RecipeViewModel;
import com.alexzgolinski.recipedev.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class RecipeListFragment extends Fragment {

  private static final String LOG_TAG = "RecipeFragment";

  @Inject
  ViewModelProvider.Factory mViewModelFactory;

  private RecipeViewModel mViewModel;
  private RecipeAdapter mAdapter;


  @Override
  public void onAttach(Activity activity) {
    AndroidSupportInjection.inject(this);
    super.onAttach(activity);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    Log.d(LOG_TAG, "onActivityCreated");
    super.onActivityCreated(savedInstanceState);

    mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel.class);
    mViewModel.init();
    mViewModel.getRecipes().observe(this, recipes -> {
      // update UI
      Log.i(LOG_TAG, "Recipes data changed " + recipes);
      mAdapter.addItems(recipes);
    });

  }

  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    Log.d(LOG_TAG, "onCreateView");
    View root = inflater.inflate(R.layout.fragment_recipe_list, container, false);
    ButterKnife.bind(this, root);
    RecyclerView recyclerView = root.findViewById(R.id.recipe_list);
    setRecyclerView(recyclerView);
    return root;
  }

  @Override
  public void onResume() {
    Log.d(LOG_TAG, "onResume");
    super.onResume();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    Log.d(LOG_TAG, "onSaveInstanceState");
    super.onSaveInstanceState(outState);
  }

  private void setRecyclerView(RecyclerView recyclerView) {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mAdapter = new RecipeAdapter(getContext(), new ArrayList<>());
    recyclerView.setAdapter(mAdapter);
  }

}

