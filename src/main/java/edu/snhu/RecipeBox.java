package edu.snhu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * The RecipeBox class models a collection of Recipe objects.
 * It provides methods to add new recipes, print recipe names,
 * and print detailed recipe information.
 * This class will be used in the final project to manage
 * a collection of recipes.</p>
 *
 * @author Kiran Badi
 * @version 1.0
 *
 *
 */
@SuppressWarnings({"unused"})
public class RecipeBox {
    /**
     * listOfRecipes - An ArrayList of SteppingStone5_Recipe objects
     */
    private ArrayList<Recipe> listOfRecipes;

    /**
     * Default constructor
     */
    public RecipeBox() {
        listOfRecipes = new ArrayList<>();
    }

    /**
     * Parameterized constructor to initialize listOfRecipes
     */
    public RecipeBox(ArrayList<Recipe> recipes) {
        this.listOfRecipes = recipes;
    }

    /**
     * Accessor for listOfRecipes
     *
     * @return listOfRecipes
     */
    public ArrayList<Recipe> getListOfRecipes() {
        return listOfRecipes;
    }

    /**
     * Mutator for listOfRecipes
     *
     * @param recipes - ArrayList of Recipe objects to set
     */
    public void setListOfRecipes(ArrayList<Recipe> recipes) {
        this.listOfRecipes = recipes;
    }

    /**
     * Method to print all recipe details for a given recipe name
     * It uses the Recipe class's getRecipeName() method to match names
     *
     */
    public void printAllRecipeNames() {
        System.out.println("\nRecipe List:");
        for (Recipe recipe : listOfRecipes) {
            System.out.println(recipe.getRecipeName());
        }
    }


    /**
     *
     * Method to print all recipe details for a given recipe name
     *
     * @param recipeName - Name of the recipe to print details for
     *                   Uses the Recipe class's printRecipe() method to display details
     */
    public void printAllRecipeDetails(String recipeName) {
        for (Recipe recipe : listOfRecipes) {
            if (recipe.getRecipeName().equalsIgnoreCase(recipeName)) {
                recipe.printRecipe();
                return;
            }
        }
        System.out.println("Recipe not found.");
    }


    /**
     * Method to add a new recipe to the recipe box
     * It uses the Recipe class's createNewRecipe() method to gather details
     *
     * @param scanner shared Scanner from a driver
     *
     */
    public void addNewRecipe(Scanner scanner) {
        Recipe newRecipe = new Recipe();
        newRecipe.createNewRecipe(scanner);
        listOfRecipes.add(newRecipe);
        System.out.println("\nRecipe added successfully!");
    }

    /**
     * The main method to run the RecipeBox application
     * Provides a menu-driven interface for user interaction
     *
     */
    public void menu() {

        RecipeBox recipeBox = new RecipeBox();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n**** RecipeBox Menu ****");
            System.out.println("1. Add New Recipe");
            System.out.println("2. Print All Recipe Names");
            System.out.println("3. Print Recipe Details");
            System.out.println("4. Exit");
            System.out.print("Please select a menu item choice: ");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print("Please enter a number (1-4): ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    recipeBox.addNewRecipe(scanner);
                    break;
                case 2:
                    recipeBox.printAllRecipeNames();
                    break;
                case 3:
                    System.out.print("Enter recipe name: ");
                    String name = scanner.nextLine();
                    recipeBox.printAllRecipeDetails(name);
                    break;
                case 4:
                    System.out.println("Thank you and Goodbye!");
                    break;
                default:
                    System.out.println("Invalid Menu choice.Please provide valid input (1-4).");
            }
        } while (choice != 4);
    }

}


