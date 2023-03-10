import managers.IngredientManager;
import managers.RecipeManager;

import java.util.Scanner;


public class Main {

    private Scanner sc = new Scanner(System.in);
    private IngredientManager im = new IngredientManager();
    private RecipeManager rm = new RecipeManager(im);

    boolean userExits = false;

    private void printGreeting() {
        System.out.println("+----------------------------------------+");
        System.out.println("|              Welcome to the            |");
        System.out.println("|                recipe app              |");
        System.out.println("+----------------------------------------+");
    }

    private void printMainMenu() {
        System.out.println("1. Add an ingredient");
        System.out.println("2. Delete an ingredient");
        System.out.println("3. Update an ingredient");
        System.out.println("4. View all ingredients");
        System.out.println("5. Add a recipe");
        System.out.println("6. Delete a recipe");
        System.out.println("7. Update a recipe");
        System.out.println("8. Search a recipe by its properties");
        System.out.println("9. View all recipes");
        System.out.println("0. Quit");
    }

    private int getUserInput() {
        int userInput = -1;

        while (userInput < 0 || userInput > 9) {
            try {
                System.out.println("\nPlease choose the desired action! : ");
                userInput = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Something went wrong. A digit please!");
            }
        }
        return userInput;
    }


    private void performAction(int userInput) {
        switch (userInput) {
            case 0:
                userExits = true;
                im.write();
                rm.write();
                System.out.println("Exiting program. Thank you!");
                sc.close();
                break;
            case 1:
                im.add(sc);
                break;
            case 2:
                im.delete(sc);
                break;
            case 3:
                im.updateIngredient(sc);
                break;
            case 4:
                im.view(sc);
                System.out.println("\n");
                pauseMainMenu();
                break;
            case 5:
                rm.add(sc);
                break;
            case 6:
                rm.delete(sc);
                break;
            case 7:
                rm.updateRecipe(sc);
                break;
            case 8:
                rm.search(sc);
                System.out.println("\n");
                pauseMainMenu();
                break;
            case 9:
                rm.view(sc);
                System.out.println("\n");
                pauseMainMenu();
                break;
            default:
                System.out.println("\n");
                System.out.println("An error has occurred.");
        }
    }

    private void pauseMainMenu() {
        System.out.println("Press any key to go back: ");
        sc.nextLine();
    }

    public void runMainMenu() {
        printGreeting();
        while (!userExits) {
            printMainMenu();
            int userInput = getUserInput();
            performAction(userInput);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.runMainMenu();
    }
}