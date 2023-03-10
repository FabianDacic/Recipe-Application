package managers;

import domain.Recipe;
import domain.Search;
import strategies.SearchByIngredientName;
import strategies.SearchByRecipePrice;
import strategies.SearchStrategy;

import java.util.List;

public class SearchManager {
    public void search(String searchString, List<Recipe> recipes, Search search) {
        SearchStrategy strategy = null;
        switch (search) {
            case BY_INGREDIENT_NAME:
                strategy = new SearchByIngredientName();
                break;
            case BY_RECIPE_PRICE:
                strategy = new SearchByRecipePrice();
                break;
        }
        strategy.search(searchString, recipes);
    }
}
