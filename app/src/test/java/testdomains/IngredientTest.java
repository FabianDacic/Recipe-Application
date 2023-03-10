package testdomains;
import org.junit.jupiter.api.Test;
import domain.Ingredient;
import domain.Unit;
import managers.IngredientManager;
import org.junit.After;
import org.junit.Before;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class IngredientTest {

    private int id = 999;
    private Unit unit = Unit.Piece;
    private int price = 50;
    private String name = "hazelnut";
    private int amountOfUnit = 100;
    private IngredientManager im = new IngredientManager();
    private ArrayList<Ingredient> ingredients = im.getAllIngredients();
    private final Ingredient testIng = new Ingredient(id, name, unit, price, amountOfUnit);

        /**
         * Setting up variables prior to testing
         *
         * @see IngredientManager
         */
        @Before
        public void setUp() {
            // Make sure that the ingredient is deleted if it exists in the arraylist
            for(Ingredient i : ingredients){
                if(i.getId() == testIng.getId()){
                    im.deleteIngredient(testIng);
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
            for(Ingredient i : ingredients){
                if(i.getName().equalsIgnoreCase(testIng.getName())){
                    im.deleteIngredient(testIng);
                    break;
                }
            }
        }

        /**
         * Tests the 'addIngredient()' method
         *
         * @see IngredientManager
         */
        @Test
        public void testAddIngredient() {

            ingredients.add(testIng);
            boolean successfulUpdate = false;

            for (Ingredient i : ingredients) {
                if (i.getName().equalsIgnoreCase(testIng.getName())) {
                    successfulUpdate = true;
                    break;
                }
            }
            assertTrue(successfulUpdate);
        }

        /**
         * Tests 'deleteIngredient()' method
         *
         * @see IngredientManager
         */
        @Test
        public void testDeleteIngredient() {
            im.deleteIngredient(testIng);
            boolean successfulUpdate = true;

            for (Ingredient i : ingredients) {
                if (i.getName().equalsIgnoreCase(testIng.getName())) {
                    successfulUpdate = false;
                    break;
                }
            }
            assertTrue(successfulUpdate);
        }
}

