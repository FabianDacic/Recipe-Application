package testdomains;
import domain.Ingredient;
import domain.Portion;
import managers.RecipeManager;
import org.junit.jupiter.api.Test;
import domain.Recipe;
import domain.Unit;
import managers.IngredientManager;
import org.junit.After;
import org.junit.Before;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RecipeTest {
    private IngredientManager im = new IngredientManager();
    private RecipeManager rm = new RecipeManager(im);
    private String name = "testRecipe";
    private Portion portion = new Portion(2, "pieces");
    private ArrayList<Ingredient> recIngredients = im.getAllIngredients();
    private ArrayList<String> comments = new ArrayList<>();
    private final Recipe testRec = new Recipe(name, portion, recIngredients, comments);
    private ArrayList<Recipe> recipes = rm.getAllRecipes();

    /**
     * Setting up variables prior to testing
     *
     * @see IngredientManager
     */
    @Before
    public void setUp() {
        // Make sure that the recipe is deleted if it exists in the arraylist
        for(Recipe r : recipes){
            if(r.getName().equalsIgnoreCase(testRec.getName())){
                rm.deleteRecipe(testRec.getName(), recipes);
                break;
            }
        }
    }

    /**
     * Ensure there are no remnants of ingredient afterwards
     *
     * @see IngredientManager
     */
    @After
    public void tearDown() {
        for(Recipe r : recipes){
            if(r.getName().equalsIgnoreCase(testRec.getName())){
                rm.deleteRecipe(testRec.getName(), recipes);
                break;
            }
        }
    }

    /**
     * Tests the 'addRecipe()' method
     *
     * @see RecipeManager
     */
    @Test
    public void testAddRecipe() {
        recipes.add(testRec);
        boolean successfulUpdate = false;

        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(testRec.getName())) {
                successfulUpdate = true;
                break;
            }
        }
        assertTrue(successfulUpdate);
    }

    /**
     * Tests 'deleteRecipe()' method
     *
     * @see RecipeManager
     */
    @Test
    public void testDeleteIngredient() {
        rm.deleteRecipe(testRec.getName(), recipes);
        boolean successfulUpdate = true;

        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(testRec.getName())) {
                successfulUpdate = false;
                break;
            }
        }
        assertTrue(successfulUpdate);
    }
}

