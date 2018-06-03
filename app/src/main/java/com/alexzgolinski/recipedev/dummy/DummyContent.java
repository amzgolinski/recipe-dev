package com.alexzgolinski.recipedev.dummy;

import com.alexzgolinski.recipedev.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

  /**
   * An array of sample (dummy) items.
   */
  public static final List<Recipe> ITEMS = new ArrayList<Recipe>();

  /**
   * A map of sample (dummy) items, by ID.
   */
  public static final Map<String, Recipe> ITEM_MAP = new HashMap<String, Recipe>();

  private static final int COUNT = 15;

  static {
    // Add some sample items.
    for (int i = 1; i <= COUNT; i++) {
      addItem(createRecipe(i));
    }
  }

  private static void addItem(Recipe recipe) {
    ITEMS.add(recipe);
    ITEM_MAP.put(recipe.getId(), recipe);
  }

  private static Recipe createRecipe(int position) {
    return new Recipe(String.valueOf(position), "Recipe " + position);
  }


}
