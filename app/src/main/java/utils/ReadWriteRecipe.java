package utils;

import managers.IngredientManager;
import domain.Ingredient;
import domain.Portion;
import domain.Recipe;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteRecipe {

    private static final String RECIPES_FILE = "../recipes.txt";

    private ArrayList<Recipe> read(IngredientManager im) throws IOException {
        ArrayList<Recipe> recipeResult = new ArrayList<>();

        File file = new File(RECIPES_FILE);
        FileReader fr = new FileReader(file);

        String line;

        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            String[] apa = line.split(";");

            Recipe recipe = new Recipe();
            recipe.setName(apa[0]);

            String[] recipePortion = apa[1].split(",");
            ArrayList<String> portions = new ArrayList<>();
            for (String string : recipePortion) {
                portions.add(string);
            }
            recipe.setPortion(new Portion(Integer.parseInt(portions.get(0)), portions.get(1)));

            String[] recipeIngredientIDs = apa[2].split("-");
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (String recipeIngredientId : recipeIngredientIDs) {
                ingredients.add(im.getIngredientById(Integer.valueOf(recipeIngredientId)));
            }
            recipe.setIngredients(ingredients);

            String[] commentsS = apa[3].split(",");
            ArrayList<String> comments = new ArrayList<>();
            for (String string : commentsS) {
                comments.add(string);
            }
            recipe.setComments(comments);

            recipeResult.add(recipe);
        }
        br.close();
        return recipeResult;

    }

    public ArrayList<Recipe> getRecipes(IngredientManager im) {
        try {
            return read(im);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read recipes", e);
        }
    }

    public void writeToRec(ArrayList<Recipe> allRecipes) {
        BufferedWriter output = null;
        try {
            File file = new File(RECIPES_FILE);
            output = new BufferedWriter(new FileWriter(file));
            for (Recipe r : allRecipes) {
                StringBuilder sb = new StringBuilder();
                sb.append(r.getName()).append(";")
                        .append(r.getPortion().getAmountOfPeople()).append(",")
                        .append(r.getPortion().getUnit()).append(";");
                boolean skip = true;
                for (Ingredient ingredient : r.getIngredients()) {
                    if (!skip) {
                        sb.append("-");
                    }
                    skip = false;
                    sb.append(ingredient.getId());
                }
                sb.append(";");

                skip = true;

                for (String comment : r.getComments()) {
                    if (!skip) {
                        sb.append(",");
                    }
                    skip = false;

                    sb.append(comment);
                }
                sb.append("\n");
                output.write(sb.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    //Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
