package edu.snhu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Test class for RecipeBox
 * </p>
 *
 * @author Kiran Badi
 * @version 1.0
 * @see edu.snhu.RecipeBox
 */
public class RecipeBoxTest {

    /**
     * Capture the original System. Out to restore after tests
     */
    private final PrintStream originalOut = System.out;

    /**
     * ByteArrayOutputStream to capture console output during tests
     */
    private ByteArrayOutputStream outContent;


    /**
     * Set up method to redirect System. Out before each test
     */
    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }


    /**
     * Teardown method to restore the original System. Out after each test
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test method for getListOfRecipes
     *
     * @see edu.snhu.RecipeBox#getListOfRecipes()
     */
    @Test
    public void getListOfRecipes() {
        RecipeBox recipeBox = new RecipeBox();
        Assertions.assertTrue(recipeBox.getListOfRecipes().isEmpty());
    }

    /**
     * Test method for setListOfRecipes
     *
     * @see edu.snhu.RecipeBox#setListOfRecipes(ArrayList)
     */
    @Test
    public void setListOfRecipes() {
        RecipeBox recipeBox = new RecipeBox();
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pancakes", 4, new ArrayList<>(), 350));
        recipeBox.setListOfRecipes(recipes);
        Assertions.assertEquals(1, recipeBox.getListOfRecipes().size());
        Assertions.assertEquals("Pancakes", recipeBox.getListOfRecipes().getFirst().getRecipeName());
    }


    /**
     * Test method for printAllRecipeNames
     *
     * @see edu.snhu.RecipeBox#printAllRecipeNames()
     */
    @Test
    public void printAllRecipeNames() {
        RecipeBox recipeBox = new RecipeBox();
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pancakes", 4, new ArrayList<>(), 350));
        recipes.add(new Recipe("Waffles", 2, new ArrayList<>(), 400));
        recipeBox.setListOfRecipes(recipes);
        recipeBox.printAllRecipeNames();
        String output = outContent.toString(StandardCharsets.UTF_8)
                .replace("\r\n", "\n");
        Assertions.assertTrue(output.contains("Pancakes"), "Should print recipe name 'Pancakes'");
        Assertions.assertTrue(output.contains("Waffles"), "Should print recipe name 'Waffles'");
    }


    /**
     * Test method for printAllRecipeDetails
     *
     * @see edu.snhu.RecipeBox#printAllRecipeDetails(String)
     */
    @Test
    public void printAllRecipeDetails() {
        RecipeBox recipeBox = new RecipeBox();
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipe.setServings(4);
        recipeBox.setListOfRecipes(new ArrayList<>(List.of(recipe)));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        try {
            recipeBox.printAllRecipeDetails("Pancakes");
        } finally {
            System.setOut(originalOut);
        }
        String output = outContent.toString().replace("\r\n", "\n");
        Assertions.assertTrue(output.contains("Pancakes"),
                "Should print recipe name");
        Assertions.assertTrue(output.contains("4"),
                "Should print number of servings");
    }


    /**
     * Test method for a menu
     *
     * @see edu.snhu.RecipeBox#menu()
     */
    @Test
    public void menu() {
        String userInput =
                """
                        1
                        Brownies
                        8
                        Sugar
                        1
                        cup
                        770
                        y
                        Cocoa
                        0.5
                        cups
                        196
                        n
                        2
                        3
                        Brownies
                        4
                        """;

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(
                    userInput.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
            new RecipeBox().menu();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        String output = outContent.toString(StandardCharsets.UTF_8)
                .replace("\r\n", "\n");
        Assertions.assertTrue(output.contains("Brownies"),
                "Recipe name 'Brownies' should appear in output");
        Assertions.assertTrue(
                output.lines().anyMatch(line -> line.trim().equalsIgnoreCase("Brownies")),
                "Recipe list should contain 'Brownies'"
        );
        Assertions.assertTrue(output.contains("8"),
                "Recipe details should include servings count");
        Assertions.assertTrue(
                output.toLowerCase().contains("sugar") &&
                        output.toLowerCase().contains("cocoa"),
                "Recipe details should include ingredients"
        );
        Assertions.assertTrue(
                output.toLowerCase().contains("exit") ||
                        output.toLowerCase().contains("goodbye") ||
                        output.toLowerCase().contains("thank"),
                "Menu should exit gracefully"
        );

    }

    /**
     * Test method for printAllRecipeDetails when no recipe is found
     *
     * @see edu.snhu.RecipeBox#printAllRecipeDetails(String)
     */
    @Test
    public void printAllRecipeDetailsWhenNoRecipeFound() {
        RecipeBox recipeBox = new RecipeBox();
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pancakes");
        recipeBox.setListOfRecipes(new ArrayList<>(List.of(recipe)));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            recipeBox.printAllRecipeDetails("Waffles");
        } finally {
            System.setOut(originalOut);
        }
        String output = out.toString();
        Assertions.assertTrue(output.contains("Recipe not found."),
                "Should print not-found message when recipe is missing");
    }


    /**
     * Test parameterized constructor of RecipeBox
     *
     * @see edu.snhu.RecipeBox#RecipeBox(ArrayList)
     */
    @Test
    public void parameterizedConstructorRecipeBoxTest() {
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeName("Pancakes");
        Recipe recipe2 = new Recipe();
        recipe2.setRecipeName("Waffles");
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);
        RecipeBox recipeBox = new RecipeBox(recipes);
        Assertions.assertNotNull(recipeBox.getListOfRecipes(), "Recipe list should not be null");
        Assertions.assertEquals(2, recipeBox.getListOfRecipes().size(), "Recipe list size should match");
        Assertions.assertEquals("Pancakes", recipeBox.getListOfRecipes().get(0).getRecipeName());
        Assertions.assertEquals("Waffles", recipeBox.getListOfRecipes().get(1).getRecipeName());
    }


    /**
     * Test menu with non-integer input
     *
     * @see edu.snhu.RecipeBox#menu()
     */
    @Test
    public void menuWithNonIntegerInput() {
        String output = runMenuWithInput("""
                hello
                4
                """);
        Assertions.assertTrue(output.contains("Please enter a number (1-4): "),
                "Should reprompt when non-integer input is entered");
        Assertions.assertTrue(output.contains("Thank you and Goodbye!"),
                "Should exit when choice 4 is entered");
    }


    /**
     * Helper method to run the menu with specified input
     *
     * @param input the input string to simulate user input
     * @return the captured output from the menu
     */
    private String runMenuWithInput(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
            new RecipeBox().menu();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return out.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }


    /**
     * Test menu with invalid choice input
     *
     * @see edu.snhu.RecipeBox#menu()
     */
    @Test
    public void menuWithInvalidChoice() {
        String output = runMenuWithInput("""
                9
                4
                """);
        Assertions.assertTrue(output.contains("Invalid Menu choice.Please provide valid input (1-4)."),
                "Should print invalid choice message for integers not 1-4");
        Assertions.assertTrue(output.contains("Thank you and Goodbye!"),
                "Should exit cleanly");
    }

}