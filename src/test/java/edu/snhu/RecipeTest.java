package edu.snhu;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * Test class for the Recipe class.
 * It contains unit tests for the various methods
 * and functionalities of the Recipe class.
 * This class uses JUnit 5 for testing.
 * </p>
 * @author Kiran Badi
 * @version 1.0
 * @see Recipe
 */
public class RecipeTest {

    // Redirects System.out to capture output for testing
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the output stream before each test.
     */
    @BeforeEach
   public void setUp() {
        // ByteArrayOutputStream to capture the printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    /**
     * Restores the original output stream after each test.
     */
    @AfterEach
   public void tearDown() {
        System.setOut(originalOut);
    }


    /**
     * Tests the createNewRecipe method of the Recipe class.
     * Simulates user input to create a new recipe and verifies
     * that the recipe attributes are set correctly.
     */
    @Test
    public void createNewRecipe() {
        String input = """
                Brownies
                8
                Sugar
                1
                cup
                770
                n
                """;

        Recipe recipe = new Recipe();
        Scanner scanner = new Scanner(input);
        recipe.createNewRecipe(scanner);
        Assertions.assertEquals("Brownies", recipe.getRecipeName());
        Assertions.assertEquals(8, recipe.getServings());
        Assertions.assertNotNull(recipe.getRecipeIngredients());
        Assertions.assertEquals(1, recipe.getRecipeIngredients().size());
        Ingredient ing = recipe.getRecipeIngredients().getFirst();
        Assertions.assertEquals("Sugar", ing.getNameOfIngredient());
        Assertions.assertEquals(1.0f, ing.getIngredientAmount(), 0.001f);
        Assertions.assertEquals("cup", ing.getUnitMeasurement());
        Assertions.assertEquals(770.0, ing.getNumberCaloriesPerUnit(), 0.001);
    }

    /**
     * Tests the createNewRecipe method with multiple ingredients.
     * Simulates user input to create a new recipe with multiple ingredients
     * and verifies that all ingredients are added correctly.
     */
    @Test
    public void testCreateNewRecipe() {
        String input = """
                Pancakes
                4
                Flour
                2.5
                cups
                455
                y
                Sugar
                1
                cup
                770
                n
                """;
        Recipe recipe = new Recipe();
        Scanner scanner = new Scanner(input);
        recipe.createNewRecipe(scanner);
        Assertions.assertEquals("Pancakes", recipe.getRecipeName());
        Assertions.assertEquals(4, recipe.getServings());
        Assertions.assertEquals(2, recipe.getRecipeIngredients().size());
        Assertions.assertEquals("Flour", recipe.getRecipeIngredients().get(0).getNameOfIngredient());
        Assertions.assertEquals("Sugar", recipe.getRecipeIngredients().get(1).getNameOfIngredient());
    }

    /**
     * Tests the printRecipe method of the Recipe class.
     * Verifies that the printed output contains the correct recipe details.
     */
   @Test
   public void printRecipe() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipe.setServings(2);
        recipe.setRecipeIngredients(new ArrayList<>());
        recipe.setTotalRecipeCalories(400.0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            recipe.printRecipe();
        } finally {
            System.setOut(originalOut);
        }
        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Recipe Name: Pancakes"));
        Assertions.assertTrue(output.contains("Servings: 2"));
        Assertions.assertTrue(output.contains("Total Recipe Calories: 400.0"));
        Assertions.assertTrue(output.contains("Calories Per Serving: 200.0"));
    }

    /**
     * Tests the scaleRecipe method of the Recipe class.
     * Simulates user input to scale the recipe and verifies
     * that the servings and ingredient amounts are updated correctly.
     */

    @Test
    public void scaleRecipe() {
        Recipe recipe = new Recipe();
        recipe.setServings(2);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2.0f, "cups", 100));
        ingredients.add(new Ingredient("Sugar", 1.0f, "cup", 200));
        recipe.setRecipeIngredients(ingredients);
        recipe.setTotalRecipeCalories(300);
        String input = "4\n";
        Scanner scanner = new Scanner(input);
        recipe.scaleRecipe(scanner);
        Assertions.assertEquals(4, recipe.getServings());
        Assertions.assertEquals(4.0f, recipe.getRecipeIngredients().get(0).getIngredientAmount());
        Assertions.assertEquals(2.0f, recipe.getRecipeIngredients().get(1).getIngredientAmount());
        Assertions.assertEquals(400.0, recipe.getTotalRecipeCalories());
    }

    /**
     * Tests the createNewRecipe method using System.in redirection.
     * Simulates user input to create a new recipe and verifies
     * that the returned recipe object has the correct attributes.
     */

    @Test
    public void createNewRecipeWithScanner() {
        String input = """
                Pancakes
                4
                Flour
                2
                cups
                455
                n
                """;
        System.setIn(new ByteArrayInputStream(
                input.getBytes(StandardCharsets.UTF_8)));
        Recipe recipe = new Recipe();
        Recipe result = recipe.createNewRecipe();
       Assertions.assertNotNull(result, "Returned recipe should not be null");
        Assertions.assertEquals("Pancakes", result.getRecipeName());
        Assertions.assertEquals(4, result.getServings());
        Assertions.assertEquals(1, result.getRecipeIngredients().size());
    }


    /**
     * Tests the scaleRecipe method with invalid servings input.
     * Verifies that the recipe remains unchanged and an error message is printed.
     */
    @Test
    public void scaleRecipeWithLessServings() {
        Recipe recipe = new Recipe();
        recipe.setServings(0);
        Ingredient ingredient = new Ingredient("Sugar", 1.0f, "cup", 100.0);
        recipe.setRecipeIngredients(new ArrayList<>(java.util.List.of(ingredient)));
        Scanner scanner = new Scanner("4\n");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            recipe.scaleRecipe(scanner);
        } finally {
            System.setOut(originalOut);
        }
        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Cannot scale recipe"),
                "Should print error message when servings are not set");
        Assertions.assertEquals(0, recipe.getServings(),
                "Servings should remain unchanged");
        Assertions.assertEquals(1.0f,
                recipe.getRecipeIngredients().getFirst().getIngredientAmount(),
                0.001f,
                "Ingredient amount should remain unchanged");
    }


    /**
     * Tests the scaleRecipe method with invalid input followed by valid input.
     * Verifies that the recipe is updated correctly after valid input is provided.
     */
    @Test
    public void scaleRecipeWithEqualToSingleServing() {
        Recipe recipe = new Recipe();
        recipe.setServings(1); // allow scaling
        recipe.setRecipeIngredients(new ArrayList<>());
        Scanner scanner = new Scanner("0\n-3\n4\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            recipe.scaleRecipe(scanner);
        } finally {
            System.setOut(originalOut);
        }
        String output = out.toString(StandardCharsets.UTF_8);
        Assertions.assertTrue(output.contains("Servings must be 1 or greater"),
                "Should prompt again when invalid servings entered");
        Assertions.assertEquals(4, recipe.getServings(),
                "Should update servings once valid input is provided");
    }


    /**
     * Tests the printRecipe method with zero servings.
     * Verifies that the printed output contains the correct recipe details.
     */


    @Test
    public void printRecipeWithServingLessThanZero() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipe.setServings(0);
        recipe.setRecipeIngredients(new ArrayList<>());
        recipe.setTotalRecipeCalories(400.0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            recipe.printRecipe();
        } finally {
            System.setOut(originalOut);
        }
        String output = outContent.toString();
        Assertions.assertTrue(output.contains("Recipe Name: Pancakes"));
        Assertions.assertTrue(output.contains("Servings: 0"));
    }

    /**
     * Tests the deleteRecipe method when the recipe is present.
     * Verifies that the recipe attributes are cleared and a confirmation message is printed.
     */

    @Test
    public void deleteRecipeWhenRecipeIsPresent() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipe.setServings(4);
        Ingredient ingredient = new Ingredient("Flour", 2.0f, "cups", 100.0);
        recipe.setRecipeIngredients(new ArrayList<>(java.util.List.of(ingredient)));
        recipe.setTotalRecipeCalories(200.0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            recipe.deleteRecipe("Pancakes");
        } finally {
            System.setOut(originalOut);
        }
        Assertions.assertEquals("", recipe.getRecipeName(), "Recipe name should be cleared");
        Assertions.assertEquals(0, recipe.getServings(), "Servings should be reset to 0");
        Assertions.assertTrue(recipe.getRecipeIngredients().isEmpty(), "Ingredients list should be cleared");
        Assertions.assertEquals(0.0, recipe.getTotalRecipeCalories(), 0.001,
                "Total calories should be reset");
        String output = out.toString();
        Assertions.assertTrue(output.contains("Recipe 'Pancakes' has been deleted."),
                "Should print confirmation message");
    }


    @Test
    void deleteRecipeWhenRecipeNameIsNotPresent() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipe.setServings(4);
        Ingredient ingredient = new Ingredient("Flour", 2.0f, "cups", 100.0);
        recipe.setRecipeIngredients(new ArrayList<>(java.util.List.of(ingredient)));
        recipe.setTotalRecipeCalories(200.0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            recipe.deleteRecipe("Waffles");
        } finally {
            System.setOut(originalOut);
        }
        Assertions.assertEquals("Pancakes", recipe.getRecipeName());
        Assertions.assertEquals(4, recipe.getServings());
        Assertions.assertEquals(1, recipe.getRecipeIngredients().size());
        Assertions.assertEquals(200.0, recipe.getTotalRecipeCalories(), 0.001);
        String output = out.toString();
        Assertions.assertTrue(output.contains("Recipe 'Waffles' not found."),
                "Should print not-found message");
    }

}