package strategies;

import domain.Ingredient;
import domain.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchByRecipePrice implements SearchStrategy {
    @Override
    public void search(String searchString, List<Recipe> recipes) {
        List<Recipe> foundRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            int totalAmount = 0;
            for (Ingredient ingredient : recipe.getIngredients()) {
                totalAmount = totalAmount + ingredient.getPrice();
            }
            if(totalAmount<=Integer.valueOf(searchString)) {
                foundRecipes.add(recipe);
            }
        }
        System.out.println("Following recipes found for search criteria by price : " + searchString);
        for (Recipe foundRecipe : foundRecipes) {
            int priceOfRecipe = 0;
            for(Ingredient i : foundRecipe.getIngredients()) {
                priceOfRecipe = priceOfRecipe + i.getPrice();
            }
            System.out.println("Name: " + foundRecipe.getName() + "----Price: " + priceOfRecipe);
        }
        System.out.println("End of found recipes");

    }
}
