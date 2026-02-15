package edu.snhu;

import java.util.Scanner;

/**
 * <p>
 * The Ingredient class models a single ingredient used in a recipe.
 * It stores the ingredient name, amount, unit of measurement,
 * calories per unit, and total calculated calories.
 * This class accepts validated user input and will be reused
 * in the final Recipe Manager application.</p>
 * @author Kiran Badi
 * @version 1.0
 */
public class Ingredient {

    /**
     * Name of the ingredient (e.g., Sugar, Flour)
     */
    private String nameOfIngredient;

    /**
     * Unit of measurement for the ingredient (cups, ounces, tbsp, etc.)
     */
    private String unitMeasurement;

    /**
     * Amount of the ingredient used.
     * Float is used because ingredient quantities may be fractional.
     */
    private float ingredientAmount;

    /**
     * Calories per unit of measurement.
     * Double is used for higher precision in calorie calculations.
     */
    private double numberCaloriesPerUnit;

    /**
     * Total calories for this ingredient.
     * Float datatype is good here since the value for this attribute is derived from other fields.
     */
    private float totalCalories;

    /**
     * Default constructor.
     * Prompts the user for ingredient data and validates input types.
     */
    @SuppressWarnings({"unused"})
    public Ingredient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ingredient name: ");
        String name = RecipeHelper.getStringValue(scanner,"Enter ingredient name: ");
        setNameOfIngredient(name);
        setIngredientAmount(RecipeHelper.getValidFloatInput(scanner,
                "Enter amount of ingredient (numeric value): "));
        System.out.print("Enter unit of measurement: ");
        setUnitMeasurement(scanner.nextLine());
        setNumberCaloriesPerUnit(RecipeHelper.getValidDoubleInput(scanner,
                "Enter calories per " + unitMeasurement + ": "));
        calculateTotalCalories();
    }

    /**
     * Parameterized constructor.
     *
     * @param nameOfIngredient      Name of the ingredient
     * @param ingredientAmount      Amount of the ingredient
     * @param unitMeasurement       Unit of measurement
     * @param numberCaloriesPerUnit Calories per unit of measurement
     *
     */
    public Ingredient(String nameOfIngredient,
                      float ingredientAmount,
                      String unitMeasurement,
                      double numberCaloriesPerUnit) {
        this.nameOfIngredient = nameOfIngredient;
        this.ingredientAmount = ingredientAmount;
        this.unitMeasurement = unitMeasurement;
        this.numberCaloriesPerUnit = numberCaloriesPerUnit;
        calculateTotalCalories();
    }


    /**
     * Calculates the total calories based on amount and calories per unit.
     * This method isolates the calculation logic for maintainability.
     */
    private void calculateTotalCalories() {
        totalCalories = (float) (ingredientAmount * numberCaloriesPerUnit);
    }

    /**
     * Gets the ingredient name.
     * @return the ingredient name
     */
    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    /**
     * gets the ingredient amount.
     * @return the ingredient amount
     */
    public float getIngredientAmount() {
        return ingredientAmount;
    }

    /**
     * gets the unit of measurement.
     * @return the unit of measurement
     */
    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    /**
     * gets calories per unit of measurement.
     * @return calories per unit of measurement
     */
    public double getNumberCaloriesPerUnit() {
        return numberCaloriesPerUnit;
    }

    /**
     * Gets total calculated calories.
     * @return total calculated calories
     */
    public float getTotalCalories() {

        return totalCalories;
    }

    /**
     * Sets the ingredient name.
     *
     * @param nameOfIngredient ingredient name
     */
    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }

    /**
     * Sets the ingredient amount and recalculates total calories.
     * Sets default to 0 if a negative value is provided.
     * @param ingredientAmount amount of ingredient
     */
    public void setIngredientAmount(float ingredientAmount) {
        this.ingredientAmount = Math.max(0f, ingredientAmount);
    }

    /**
     * Sets the unit of measurement.
     *
     * @param unitMeasurement unit of measurement
     */
    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    /**
     * Sets calories per unit and recalculates total calories.
     * Sets default to 0 if negative value is provided.
     * @param numberCaloriesPerUnit calories per unit
     */
    public void setNumberCaloriesPerUnit(double numberCaloriesPerUnit) {
        this.numberCaloriesPerUnit = Math.max(0.0, numberCaloriesPerUnit);
    }

    /**
     * Displays ingredient details.
     * Useful for testing and debugging.
     */
    public void printIngredient() {
        System.out.println("Ingredient Details:");
        System.out.println("Name: " + getNameOfIngredient());
        System.out.println("Amount: " + getIngredientAmount() + " " + getUnitMeasurement());
        System.out.println("Calories per " + getUnitMeasurement() + ": " + getNumberCaloriesPerUnit());
        System.out.println("Total Calories: " + getTotalCalories());
    }


    /**
     * Creates a new ingredient with the specified name.
     *
     * @param ingredientName Name of the ingredient
     * @return Ingredient object
     */
    @SuppressWarnings("unused")
    public Ingredient addNewIngredient(String ingredientName) {
        this.setNameOfIngredient(ingredientName);
        return this;
    }

    /**
     * Constructor that accepts a Scanner for user input.
     * It is used along with the Recipe class to create ingredients.
     * A single Scanner instance is passed from Recipe to avoid resource leaks.
     * @param scanner Scanner object for input
     */
    public Ingredient(Scanner scanner) {
        String name = RecipeHelper.getStringValue(scanner,"Enter ingredient name: ");
        setNameOfIngredient(name);
        setIngredientAmount(RecipeHelper.getValidFloatInput(scanner,
                "Enter amount of ingredient (numeric value): "));
        setUnitMeasurement(RecipeHelper.getStringValue(scanner,"Enter unit of measurement: "));
        setNumberCaloriesPerUnit(RecipeHelper.getValidDoubleInput(scanner,
                "Enter calories per " + unitMeasurement + ": "));
        calculateTotalCalories();
    }
}

