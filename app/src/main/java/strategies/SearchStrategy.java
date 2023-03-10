package strategies;

import domain.Recipe;

import java.util.List;

public interface SearchStrategy {

    void search(String searchString, List<Recipe> recipes);
}
