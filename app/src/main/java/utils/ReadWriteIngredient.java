package utils;

import domain.Ingredient;
import domain.Unit;

import java.io.*;
import java.util.ArrayList;

/**
 * Open and read a file, return an arraylist of the ingredients
 */

public class ReadWriteIngredient {

    private static final String INGREDIENTS_FILE = "../ingredients.txt";

    private ArrayList<Ingredient> read() throws IOException {
        ArrayList<Ingredient> ingredientResult = new ArrayList<>();

        FileReader file = new FileReader(INGREDIENTS_FILE);
        String line;

        BufferedReader br = new BufferedReader(file);
        while ((line = br.readLine()) != null) {
            String[] lineOfIngredient = line.split(";");

            Ingredient ingredient = new Ingredient();
            ingredient.setId(Integer.valueOf(lineOfIngredient[0]));

            ingredient.setName(lineOfIngredient[1]);

            ingredient.setUnit(Unit.fromString(lineOfIngredient[2]));
            ingredient.setAmountOfUnit(Integer.valueOf(lineOfIngredient[3]));

            ingredient.setPrice(Integer.parseInt(lineOfIngredient[4]));

            ingredientResult.add(ingredient);
        }
        br.close();
        return ingredientResult;
    }

    public ArrayList<Ingredient> getIngredients()  {
        try {
            return read();
        } catch (IOException e) {
            throw new IllegalStateException("Could not load ingredients",e);
        }
    }

    public void writeToIng(ArrayList<Ingredient> allIngredients) {
        BufferedWriter output = null;
        try {
            File file = new File(INGREDIENTS_FILE);
            output = new BufferedWriter(new FileWriter(file));
            for (Ingredient i : allIngredients) {
                StringBuilder sb = new StringBuilder();

                String ingString = sb.append(i.getId()).append(";")
                        .append(i.getName()).append(";")
                        .append(i.getUnit().name()).append(";")
                        .append(i.getAmountOfUnit()).append(";")
                        .append(i.getPrice()).append("\n").toString();
                output.write(ingString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}