package managers;

import domain.Ingredient;
import domain.Unit;
import utils.ReadWriteIngredient;

import java.util.ArrayList;
import java.util.Scanner;

public class IngredientManager implements CommonIngredientsRecipeAction {

    private ArrayList<Ingredient> ingredients;
    private ReadWriteIngredient readWriteIngredient = new ReadWriteIngredient();
    public IngredientManager() {
        this.ingredients = readWriteIngredient.getIngredients();
    }

    public static boolean unitContains(String test) {
        for (Unit u : Unit.values()) {
            if (u.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    private Ingredient findIngredient(String ingredient, ArrayList<Ingredient> ingredients) {
        for (Ingredient i : ingredients) {
            if (i.getName().equalsIgnoreCase(ingredient)) {
                return i;
            }
        }
        return null;
    }

    public Ingredient search(String ingredient) {
        Ingredient ingredientExists = null;

        for (Ingredient i : ingredients) {
            if (i.getName().equalsIgnoreCase(ingredient)) {
                ingredientExists = i;
                break;
            }
        }
        return ingredientExists;
    }

    private boolean ingredientExists(String ingredientName, ArrayList<Ingredient> ingredients) {
        boolean ingredientExists = false;
        for (Ingredient i : ingredients) {
            if (i.getName().equalsIgnoreCase(ingredientName)) {
                ingredientExists = true;
                break;
            }
        }
        return ingredientExists;
    }


    public Ingredient getIngredientById(int id) {
        Ingredient ingredientExists = null;

        for (Ingredient i : ingredients) {
            if (i.getId() == (id)) {
                ingredientExists = i;
                break;
            }
        }
        return ingredientExists;
    }


    public void add(Scanner sc) {

        System.out.println("\nName of the ingredient to be added: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        String newIngredientName = sc.nextLine().toLowerCase();

        System.out.println("Which unit does the ingredient possess? Gram, Litre, Piece? ");
        while(!sc.hasNext(("(?i)\\b(\\w*gram|litre|piece\\w*)\\b"))) {
            System.out.println("Please enter gram, litre or piece! ");
            sc.next();
        }
        String newIngredientUnit = (sc.nextLine().toLowerCase());

        System.out.println("Amount of unit to be added: ");
        int newIngredientUnitAmount;
        while (true) {
            try {
                newIngredientUnitAmount = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        System.out.println("Price of the ingredient to be added: ");
        int newIngredientPrice;
        while (true) {
            try {
                newIngredientPrice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }

        boolean ingredientAlreadyExists = false;
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(newIngredientName) &&
                    ingredient.getUnit() == Unit.fromString(newIngredientUnit)
                    && ingredient.getPrice() == newIngredientPrice
                    && ingredient.getAmountOfUnit() == newIngredientUnitAmount) {
                System.out.println("Ingredient already exists!");
                ingredientAlreadyExists = true;
            }

        }

        if (!ingredientAlreadyExists) {
            Ingredient newIngredient = new Ingredient(getNextId(), newIngredientName,
                    Unit.fromString(newIngredientUnit), newIngredientUnitAmount, newIngredientPrice);
            getAllIngredients().add(newIngredient);
            System.out.println("Ingredient added. Proceeding...");
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        for (Ingredient i : ingredients) {
            if (i.getId() == ingredient.getId()) {
                ingredients.remove(i);
                break;
            }
        }
    }

    public void delete(Scanner sc) {
        String deleteIngredientName;
        boolean ingredientExists = false;
        System.out.println("\nName of the ingredient to be deleted: ");

        deleteIngredientName = sc.nextLine().toLowerCase();
        Ingredient ingredient = search(deleteIngredientName);

        if (ingredient == null) {
            do {
                System.out.println("Please enter an existing ingredient: ");
                deleteIngredientName = sc.nextLine().toLowerCase();
                ingredient = search(deleteIngredientName);
                if (ingredient != null) {
                    ingredientExists = true;
                }
            } while (!ingredientExists);
        }
        deleteIngredient(ingredient);
        System.out.println("Ingredient has been deleted. Proceeding...");
    }

    @Override
    public void write() {
        readWriteIngredient.writeToIng(getAllIngredients());
    }

    public void updateIngredient(String oldIngredientName, Ingredient ingredient, ArrayList<Ingredient> ingredients) {
        for (Ingredient i : ingredients) {
            if (i.getName().equalsIgnoreCase(oldIngredientName)) {
                int index = ingredients.indexOf(i);
                ingredients.set(index, ingredient);
                break;
            }
        }
    }

    public void updateIngredient(Scanner sc) {
        String oldIngredientName;
        boolean ingredientExists = false;
        System.out.println("\nInsert old name of ingredient: ");
        oldIngredientName = sc.nextLine().toLowerCase();
        Ingredient oldIngredient = null;
        if (search(oldIngredientName) == null) {
            do {
                System.out.println("Please enter an existing recipe: ");
                oldIngredientName = sc.nextLine().toLowerCase();

                oldIngredient = search(oldIngredientName);
                if (oldIngredient != null) {
                    ingredientExists = true;
                }

            } while (!ingredientExists);
        }

        System.out.println("Insert updated name of ingredient: ");
        while (!sc.hasNext("[A-Za-z]+")) {
            System.out.println("Only letters! Try again please!");
            sc.next();
        }
        while(sc.nextLine().equalsIgnoreCase(oldIngredientName)) {
            System.out.println("This name cannot be chosen. Pick another! ");
            sc.next();
        }
        String updateIngredient = sc.nextLine().toLowerCase();

        System.out.println("Insert updated unit: ");
        while(!sc.hasNext(("(?i)\\b(\\w*gram|litre|piece\\w*)\\b"))) {
            System.out.println("Only gram, litre or piece please!");
            sc.next();
        }
        String updateUnit = sc.nextLine().toLowerCase();

        System.out.println("Insert updated unit amount: ");
        while (!sc.hasNext("[0-9]+")) {
            System.out.println("Only digits! Try again please!");
            sc.next();
        }
        int updateUnitAmount = Integer.valueOf(sc.nextLine());

        System.out.println("Insert updated price per unit: ");

        int updatePrice;
        while (true) {
            try {
                updatePrice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException ne) {
                System.out.println("Please enter an integer!");
            }
        }
        Ingredient updatedIngredient = new Ingredient(getNextId(), updateIngredient, Unit.fromString(updateUnit), updateUnitAmount, updatePrice);
        updateIngredient(oldIngredientName, updatedIngredient, getAllIngredients());
        System.out.println("Ingredient updated. Proceeding...");
    }

    public void view(Scanner sc) {
        System.out.println("----------------Current ingredients--------------------");
        for (Ingredient i : getAllIngredients()) {
            System.out.println(i.getName());
        }

        System.out.println("\n What is the next course of action? ");
        System.out.println("1. View ingredient by details");
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
                String ingredientName = sc.nextLine().toLowerCase();

                if (ingredientExists(ingredientName, getAllIngredients())) {
                    Ingredient foundIngredient = findIngredient(ingredientName, getAllIngredients());
                    System.out.println("Ingredient details" + "\n");
                    System.out.println("----------Its details-----------");
                    System.out.println("Unit: " + foundIngredient.getUnit());
                    System.out.println("Price : " + foundIngredient.getPrice());
                    System.out.println("ID : " + foundIngredient.getId());
                    System.out.println("Amount of unit : " + foundIngredient.getAmountOfUnit());
                } else {
                    System.out.println("Ingredient not found.");
                }
                break;
            case 2:
                break;
        }
        System.out.println("-------------------------------------------------------");
    }

    public ArrayList<Ingredient> getAllIngredients() {
        return ingredients;
    }

    public int getNextId() {
        int max = 0;
        for (Ingredient ingredient : ingredients) {
            if (max <= ingredient.getId()) {
                max = ingredient.getId();
            }
        }
        return max + 1;
    }

    public Ingredient getIngredient(String nameOfIng, Unit unit, int price, int amountOfUnit) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(nameOfIng) &&
                    ingredient.getUnit() == unit && ingredient.getPrice() == price
                    && ingredient.getAmountOfUnit() == amountOfUnit) {
                return ingredient;
            }
        }
        return null;
    }

    public void add(ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            Ingredient value = getIngredient(ingredient.getName(), ingredient.getUnit(), ingredient.getPrice(), ingredient.getAmountOfUnit());
            if (value == null) {
                getAllIngredients().add(ingredient);
            }
        }
    }
}
