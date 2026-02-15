package edu.snhu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * The Recipe class models a recipe consisting of
 * a name, number of servings, a list of Ingredient objects,
 * and total calculated calories.
 * This class will be used in the final project to manage a
 * collection of recipes.</p>
 * @author Kiran Badi
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Recipe {

    /**
     * Name of the recipe
     */
    private String recipeName;

    /**
     * Number of servings
     */
    private int servings;

    /**
     * List of ingredients in the recipe
     */
    private ArrayList<Ingredient> recipeIngredients;

    /**
     * Total calories for the recipe
     */
    private double totalRecipeCalories;

    /**
     * Default constructor
     * Initializes an empty recipe.
     */
    public Recipe() {
        recipeName = "";
        servings = 0;
        recipeIngredients = new ArrayList<>();
        totalRecipeCalories = 0.0;
    }

    /**
     * Parameterized constructor
     *
     * @param recipeName          Name of the recipe
     * @param servings            Number of servings
     * @param recipeIngredients   List of ingredients
     * @param totalRecipeCalories Total calories for the recipe
     */
    public Recipe(String recipeName, int servings,
                  ArrayList<Ingredient> recipeIngredients,
                  double totalRecipeCalories) {
        this.recipeName = recipeName;
        this.servings = servings;
        this.recipeIngredients = recipeIngredients;
        this.totalRecipeCalories = totalRecipeCalories;
    }

    // =======================
    // Accessors and Mutators
    // =======================

    /**
     * Gets the name of the recipe.
     *
     * @return recipeName
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Sets the name of the recipe.
     *
     * @param recipeName Name of the recipe
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Gets the number of servings.
     *
     * @return servings
     */
    public int getServings() {
        return servings;
    }

    /**
     * Sets the number of servings.
     *
     * @param servings Number of servings
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * Gets the list of ingredients.
     *
     * @return recipeIngredients
     */
    public ArrayList<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * Sets the list of ingredients.
     *
     * @param recipeIngredients List of Ingredient objects
     */
    public void setRecipeIngredients(ArrayList<Ingredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * Gets the total calories for the recipe.
     *
     * @return totalRecipeCalories
     */
    public double getTotalRecipeCalories() {
        return totalRecipeCalories;
    }

    /**
     * Sets the total calories for the recipe.
     * Sets to 0.0 if a negative value is provided.
     * @param totalRecipeCalories Total calories
     */
    public void setTotalRecipeCalories(double totalRecipeCalories) {
        this.totalRecipeCalories = Math.max(0.0, totalRecipeCalories);
    }

    // =======================
    // Core Methods
    // =======================

    /**
     * Creates a new recipe by prompting the user for input
     * via the console.
     * @return this Recipe object
     */
    public Recipe createNewRecipe() {
        return createNewRecipe(new Scanner(System.in));
    }


    /**
     * Creates a new recipe using a shared Scanner instance from the driver.
     *
     * @param scanner shared Scanner from a driver
     * @return this Recipe object
     */
    public Recipe createNewRecipe(Scanner scanner) {

        recipeIngredients = new ArrayList<>();
        totalRecipeCalories = 0.0;

        // Prompt for recipe name and servings
        String name = RecipeHelper.getStringValue(scanner, "Enter recipe name: ");
        setRecipeName(name);

        int servingsValue = RecipeHelper.getIntegerValue(scanner, "Enter number of servings: ");
        setServings(servingsValue);

        // Add ingredients
        String choice;
        do {
            Ingredient ingredient = new Ingredient(scanner);
            recipeIngredients.add(ingredient);
            totalRecipeCalories += ingredient.getTotalCalories();
            System.out.print("Add another ingredient? (y/n): ");
            choice = scanner.nextLine().trim();
        } while (choice.equalsIgnoreCase("y"));
        return this;
    }


    /**
     * Prints recipe details including ingredients and calorie totals.
     * Uses the instance variables of the current object.
     */
    public void printRecipe() {
        System.out.println("\nRecipe Name: " + recipeName);
        System.out.println("Servings: " + servings);
        System.out.println("\nIngredients:");
        RecipeHelper.printIngredients(recipeIngredients);
        System.out.println("\nTotal Recipe Calories: " + totalRecipeCalories);
        // Check if servings are not zero to avoid division by zero
        if (servings > 0) {
            System.out.println("Calories Per Serving: " + (totalRecipeCalories / servings));
        } else {
            System.out.println("Calories Per Serving: N/A (servings not set)");
        }
    }

    /**
     * Scales the recipe to a new number of servings by adjusting
     * ingredient amounts proportionally and recalculating calories.
     *
     * @param scanner shared Scanner from the driver
     */
    public void scaleRecipe(Scanner scanner) {
        if (this.servings < 1) {
            System.out.println("Cannot scale recipe: servings not set.");
            return;
        }
        int newServings;
        while (true) {
            newServings = RecipeHelper.getIntegerValue(scanner,
                    "Enter the new number of servings: "
            );
            if (newServings >= 1) {
                break;
            }
            System.out.println("Servings must be 1 or greater. Please try again.");
        }
        double scaleFactor = (double) newServings / this.servings;
        for (Ingredient ingredient : recipeIngredients) {
            float currentAmount = ingredient.getIngredientAmount();
            float scaledAmount = (float) (currentAmount * scaleFactor);
            ingredient.setIngredientAmount(scaledAmount);
        }
        this.servings = newServings;
        totalRecipeCalories = 0.0;
        for (Ingredient ingredient : recipeIngredients) {
            totalRecipeCalories += ingredient.getTotalCalories();
        }
        System.out.println("Recipe scaled to " + newServings + " servings.");
    }


    /**
     * Delete the recipe by clearing all data given the recipe name
     * Resets instance variables to default values.
     */
    public void deleteRecipe(String recipeName) {
        if (this.recipeName.equals(recipeName)) {
            this.recipeName = "";
            this.servings = 0;
            this.recipeIngredients.clear();
            this.totalRecipeCalories = 0.0;
            System.out.println("Recipe '" + recipeName + "' has been deleted.");
        } else {
            System.out.println("Recipe '" + recipeName + "' not found.");
        }
    }
}
