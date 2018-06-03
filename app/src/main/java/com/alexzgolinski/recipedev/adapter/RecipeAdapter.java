package com.alexzgolinski.recipedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alexzgolinski.recipedev.db.RecipeEntity;
import com.alexzgolinski.recipedev.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

  private static final String LOG_TAG = RecipeAdapter.class.getName();

  private List<RecipeEntity> mRecipes;
  // Store the context for easy access
  private Context mContext;

  public RecipeAdapter(Context context, List<RecipeEntity> recipes) {
    this.mContext = context;
    this.mRecipes = recipes;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recipe_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    RecipeEntity recipe = mRecipes.get(position);
    holder.mRecipeTitle.setText(recipe.getTitle());
    /*
    holder.mItem = mValues.get(position);
    holder.mIdView.setText(mValues.get(position).id);
    holder.mContentView.setText(mValues.get(position).content);

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          // Notify the active callbacks interface (the activity, if the
          // fragment is attached to one) that an item has been selected.
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
    */
  }

  public void addItems(List<RecipeEntity> recipes) {
    this.mRecipes = recipes;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mRecipes.size();
  }

  // Easy access to the context object in the recyclerview
  private Context getContext() {
    return mContext;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;
    TextView mRecipeTitle;

    ViewHolder(View view) {
      super(view);
      mView = view;
      mRecipeTitle = view.findViewById(R.id.recipe_title);

    }

    @Override
    public String toString() {
      return super.toString();
    }
  }
}
