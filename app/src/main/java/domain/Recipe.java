package domain;

import java.util.ArrayList;

public class Recipe {

    private String name;
    private Portion portion;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> comments;

    public Recipe(String name, Portion portion, ArrayList<Ingredient> ingredients, ArrayList<String> comments) {
        this.portion = portion;
        this.name = name;
        this.ingredients = ingredients;
        this.comments = comments;
    }

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Portion getPortion() {
        return portion;
    }

    public void setPortion(Portion portion) {
        this.portion = portion;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    /**
     * @return Recipe information as a String
     * @brief Returns Recipe information as a String
     */
    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", portion=" + portion +
                ", ingredients=" + ingredients +
                ", comments=" + comments +
                '}';
    }
}

