package managers;

import domain.Ingredient;
import domain.Portion;
import domain.Recipe;
import domain.Unit;
import utils.ReadWriteRecipe;

import java.util.ArrayList;
import java.util.Scanner;

import static domain.Search.BY_INGREDIENT_NAME;
import static domain.Search.BY_RECIPE_PRICE;

public class RecipeManager implements CommonIngredientsRecipeAction {

    private ArrayList<Recipe> recipes;
    private IngredientManager im;
    private SearchManager searchManager = new SearchManager();
    private ReadWriteRecipe readWriteRecipe = new ReadWriteRecipe();



    public RecipeManager(IngredientManager im) {
        this.recipes = readWriteRecipe.getRecipes(im);
        this.im = im;
    }

    private boolean recipeExists(String recipeName, ArrayList<Recipe> recipes) {
        boolean recipeExists = false;
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(recipeName)) {
                recipeExists = true;
                break;
            }
        }
        return recipeExists;
    }

    private void addRecipe(Recipe recipe) {
        if (!recipes.contains(recipe)) {
            recipes.add(recipe);
        }
    }

    public void deleteRecipe(String recipeName, ArrayList<Recipe> recipes) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(recipeName)) {
                recipes.remove(r);
                break;
            }
        }
    }

    private void updateRecipe(String oldRecipeName, Recipe recipe, ArrayList<Recipe> recipes) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(oldRecipeName)) {
                int index = recipes.indexOf(r);
                recipes.set(index, recipe);
                break;
            }
        }
    }

    private Recipe findRecipe(String recipe, ArrayList<Recipe> recipes) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(recipe)) {
                return r;
            }
        }
        return null;
    }

    public void search(Scanner sc) {
        System.out.println("\nWhat do you want to search the recipe by? ");
        System.out.println("1. By its recipe price");
        System.out.println("2. By its ingredients' name");
        System.out.println("0. Go back to main menu");
        int userChoice = -1;
        while (userChoice < 0 || userChoice > 2) {
            try {
                System.out.println("\nPlease choose the desired action! : ");
                userChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Something went wrong. A digit please!");
            }
        }

        switch (userChoice) {
            case 1:
                System.out.println("\nEnter recipe max price: ");
                while (!sc.hasNext("[0-9]+")) {
                    System.out.println("Only letters! Try again please!");
                    sc.next();
                }
                String recipeName = sc.nextLine().toLowerCase();
                searchManager.search(recipeName, recipes, BY_RECIPE_PRICE);
                break;
            case 2:
                System.out.println("\nEnter the ingredients name: ");
                while (!sc.hasNext("[A-Za-z]+")) {
                    System.out.println("Only letters! Try again please!");
                    sc.next();
                }
                String recipeIngredient = sc.nextLine().toLowerCase();

                searchManager.search(recipeIngredient, recipes, BY_INGREDIENT_NAME);

                break;
            case 0:
                break;
        }
    }


    public void updateRecipe(Scanner sc) {
        ArrayList<String> updatedComments = new ArrayList<>();
        ArrayList<Ingredient> updatedIngredients = new ArrayList<>();
        boolean recipeExists = false;
        String oldRecipe;

        System.out.println("Enter name of old recipe: ");
        oldRecipe = sc.nextLine().toLowerCase();

        if (!recipeExists(oldRecipe, getAllRecipes())) {
            do {
                System.out.println("Please enter an existing recipe: ");
                oldRecipe = sc.nextLine();

                if (recipeExists(oldRecipe, getAllRecipes())) {
                    recipeExists = true;
                }

            } while (!recipeExists);
        }

        System.out.println("Enter new name for recipe: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        String updateRecipe = sc.nextLine().toLowerCase();

        int updateRecPortion;
        System.out.println("Enter new portion of recipe: ");
        while (true) {
            try {
                updateRecPortion = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        System.out.println("Enter new portion unit of recipe: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        String updateRPorUnit = sc.nextLine().toLowerCase();

        int updateRecIng;
        System.out.println("How many ingredients will the new recipe have? ");
        while (true) {
            try {
                updateRecIng = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        for (int i = 1; i < updateRecIng + 1; i++) {
            int price;
            System.out.println("\n" + i + "." + "Enter new amount of ingredient: ");
            while (true) {
                try {
                    price = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("Please enter an integer!");
                }
            }

            System.out.println(i + "." + "Enter new unit of ingredient: ");
            while (!sc.hasNext("[A-Za-z]+") ) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String unitOfIng = sc.nextLine().toLowerCase();

            System.out.println(i + "." + "Enter new unit amount of ingredient: ");
            while (!sc.hasNext("[0-9]+")) {
                System.out.println("Only digits! Try again please!");
                sc.next();
            }
            int unitAmountOfIng = Integer.valueOf(sc.nextLine());


            System.out.println(i + "." + "Enter new name of ingredient: " + "\n");
            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String nameOfIng = sc.nextLine().toLowerCase();

            updatedIngredients.add(new Ingredient(im.getNextId(), nameOfIng, Unit.fromString(unitOfIng), unitAmountOfIng, price));
        }

        System.out.println("How many new comments does the new recipe have? ");
        int updatedComLoop;
        while (true) {
            try {
                updatedComLoop = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        for (int i = 1; i < updatedComLoop + 1; i++) {
            System.out.println("\n" + i + "." + "Enter new comment/s: ");

            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String recComment = sc.nextLine();

            updatedComments.add(recComment);
        }
        Recipe uRecipe = new Recipe(updateRecipe, new Portion(updateRecPortion, updateRPorUnit), updatedIngredients, updatedComments);
        updateRecipe(oldRecipe, uRecipe, getAllRecipes());
        System.out.println("Recipe updated. Proceeding...");
    }

    public void delete(Scanner sc) {
        String deleteRecipeName;
        boolean recipeExists = false;

        System.out.println("\nName of the recipe to be deleted: ");
        deleteRecipeName = sc.nextLine().toLowerCase();

        if (!recipeExists(deleteRecipeName, getAllRecipes())) {
            do {
                System.out.println("Please enter an existing recipe: ");
                deleteRecipeName = sc.nextLine();

                if (recipeExists(deleteRecipeName, getAllRecipes())) {
                    recipeExists = true;
                    deleteRecipe(deleteRecipeName, getAllRecipes());
                    System.out.println("Recipe has been deleted. Proceeding...");
                }
            } while (!recipeExists);
        }
        deleteRecipe(deleteRecipeName, getAllRecipes());
        System.out.println("Recipe has been deleted. Proceeding...");
    }

    @Override
    public void write() {
        readWriteRecipe.writeToRec(recipes);
    }

    public void view(Scanner sc) {
        for (Recipe r : getAllRecipes()) {
            System.out.println(r.getName());
        }
        System.out.println("\n What is the next course of action? ");
        System.out.println("1. View recipe by details");
        System.out.println("2. Go back to main menu");
        int userChoice = -1;
        while (userChoice < 0 || userChoice > 2) {
            try {
                System.out.println("\nPlease choose the desired action! : ");
                userChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Something went wrong. A digit please!");
            }
        }
        switch (userChoice) {
            case 1:
                System.out.println("\nEnter its name: ");

                while (!sc.hasNext("[A-Za-z]+")) {
                    System.out.println("Only letters! Try again please!");
                    sc.next();
                }
                String recipeName = sc.nextLine().toLowerCase();

                if (recipeExists(recipeName, getAllRecipes())) {
                    Recipe foundRecipe = findRecipe(recipeName, getAllRecipes());
                    System.out.println("Recipe found." + "\n");
                    System.out.println("----------Its details-----------");
                    System.out.println("Portion: " + foundRecipe.getPortion().getAmountOfPeople());
                    System.out.println("Portion unit: " + foundRecipe.getPortion().getUnit());
                    System.out.println("----Ingredients----");
                    for (Ingredient ingredient : foundRecipe.getIngredients()) {
                        System.out.println(ingredient.getName());
                    }
                    System.out.println("-----Comments-----");
                    for (String comment : foundRecipe.getComments()) {
                        System.out.println(comment);
                    }
                } else {
                    System.out.println("Recipe not found.");
                }
                break;
            case 2:
                break;
        }
    }


    public void add(Scanner sc) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<String> commentsList = new ArrayList<>();

        System.out.println("\nInsert name of new recipe: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        String newRecipeName = sc.nextLine().toLowerCase();

        System.out.println("Insert portion of new recipe: ");
        int newRecipePortion;
        while (true) {
            try {
                newRecipePortion = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        System.out.println("Insert portion unit of new recipe: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        String newRecipePortionUnit = sc.nextLine().toLowerCase();

        System.out.println("How many ingredients does the new recipe have? ");
        int ingLoop;
        while (true) {
            try {
                ingLoop = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        for (int i = 1; i < ingLoop + 1; i++) {
            System.out.println("\n" + i + "." + "Enter price of ingredient: ");
            int price;
            while (true) {
                try {
                    price = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException ne) {
                    System.out.println("Please enter an integer!");
                }
            }

            System.out.println(i + "." + "Enter unit of ingredient: ");
            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String unitOfIng = sc.nextLine().toLowerCase();

            System.out.println(i + "." + "Enter unit amount of ingredient: ");
            while (!sc.hasNext("[0-9]+")) {
                System.out.println("Only digits! Try again please!");
                sc.next();
            }
            int unitAmountOfIng = Integer.valueOf(sc.nextLine());

            System.out.println(i + "." + "Enter name of ingredient: " + "\n");
            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String nameOfIng = sc.nextLine().toLowerCase();

            Ingredient ingredient = im.getIngredient(nameOfIng, Unit.fromString(unitOfIng), price, unitAmountOfIng);
            if (ingredient == null) {
                ingredient = new Ingredient(im.getNextId(), nameOfIng, Unit.fromString(unitOfIng), unitAmountOfIng, price);
            }
            ingredients.add(ingredient);
        }

        System.out.println("How many comments does the new recipe have? ");
        int comLoop;
        while (true) {
            try {
                comLoop = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        for (int i = 1; i < comLoop + 1; i++) {
            System.out.println(i + "." + "Enter comment/s: ");
            while (!sc.hasNext("[A-Za-z]+")) {
                System.out.println("Only letters! Try again please!");
                sc.next();
            }
            String recComment = sc.nextLine();
            commentsList.add(recComment);
        }

        Recipe nRecipe = new Recipe(newRecipeName, new Portion(newRecipePortion, newRecipePortionUnit), ingredients, commentsList);

        addRecipe(nRecipe);
        im.add(ingredients);
        System.out.println("Recipe added. Proceeding...");
    }

    public ArrayList<Recipe> getAllRecipes() {
        return recipes;
    }

}
