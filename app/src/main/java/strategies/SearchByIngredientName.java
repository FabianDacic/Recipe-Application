package strategies;

import domain.Ingredient;
import domain.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByIngredientName implements SearchStrategy {
    @Override
    public void search(String searchString, List<Recipe> recipes) {
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if(ingredient.getName().equalsIgnoreCase(searchString)) {
                    foundRecipes.add(recipe);
                }
            }
        }
        System.out.println("Following recipes found for search criteria : "+searchString);
        for (Recipe foundRecipe : foundRecipes) {
            System.out.println(foundRecipe.getName());
        }
        System.out.println("End of found recipes");

    }
}
