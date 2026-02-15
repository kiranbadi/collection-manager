package edu.snhu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * <p>
 * Test class for Ingredient.
 * This class contains unit tests to verify the functionality
 * of the Ingredient class.
 * </p>
 * @author Kiran Badi
 * @version 1.0
 * @see Ingredient
 */

public class IngredientTest {

    /**
     * Test the Ingredient constructor and getters.
     */
    @Test
   public void testConstructor() {
        Ingredient ingredient = new Ingredient("Sugar", 10.0f, "grams", 4.0);
        Assertions.assertEquals("Sugar", ingredient.getNameOfIngredient());
        Assertions.assertEquals(10.0f, ingredient.getIngredientAmount(), 0.001f);
        Assertions.assertEquals("grams", ingredient.getUnitMeasurement());
        Assertions.assertEquals(4.0, ingredient.getNumberCaloriesPerUnit(), 0.001);
        Assertions.assertEquals(40.0f, ingredient.getTotalCalories(), 0.001f); // 10 * 4
    }


    /**
     * Test the Ingredient parameterized constructor with Scanner input.
     */
    @Test
    public void testParameterisedConstructor() {
        String input = """
                Cocoa
                0.5
                cups
                196
                """;

        Ingredient ingredient = new Ingredient(new Scanner(input));
        Assertions.assertEquals("Cocoa", ingredient.getNameOfIngredient());
        Assertions.assertEquals(0.5f, ingredient.getIngredientAmount(), 0.001f);
        Assertions.assertEquals("cups", ingredient.getUnitMeasurement());
        Assertions.assertEquals(196.0, ingredient.getNumberCaloriesPerUnit(), 0.001);
        Assertions.assertEquals(98.0f, ingredient.getTotalCalories(), 0.001f);
    }

    @Test
   public void setIngredientAmountWithNegativeValues() {
        Ingredient ingredient = new Ingredient("Sugar", 1.0f, "cup", 100.0);
        ingredient.setIngredientAmount(-5.0f);
        Assertions.assertEquals(0.0f, ingredient.getIngredientAmount(), 0.001f,
                "Negative ingredient amount should be set to 0");
    }

    @Test
    public void setNumberCaloriesPerUnitWithNegativeValues() {
        Ingredient ingredient = new Ingredient("Sugar", 1.0f, "cup", 100.0);
        ingredient.setNumberCaloriesPerUnit(-10.0);
        Assertions.assertEquals(0.0, ingredient.getNumberCaloriesPerUnit(), 0.001,
                "Negative calories per unit should be set to 0");
    }


    /**
     * Test the printIngredient method output.
     */

    @Test
    public void printIngredientPrintsExpectedFields() {
        Ingredient ingredient = new Ingredient("Sugar", 2.0f, "cups", 50.0); // total=100
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            ingredient.printIngredient();
        } finally {
            System.setOut(originalOut);
        }
        String output = out.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
        Assertions.assertTrue(output.contains("Ingredient Details:"));
        Assertions.assertTrue(output.contains("Name: Sugar"));
        Assertions.assertTrue(output.contains("Amount: 2.0 cups"));
        Assertions.assertTrue(output.contains("Calories per cups: 50.0"));
        Assertions.assertTrue(output.contains("Total Calories: 100.0"));
    }


    /**
     * Test the addNewIngredient method.
     * Verifies that the ingredient name is updated correctly.
     */
    @Test
    public void addNewIngredientAndSetName() {
        Ingredient ingredient = new Ingredient("Sugar", 1.0f, "cup", 100.0);
        Ingredient returned = ingredient.addNewIngredient("Flour");
        Assertions.assertEquals("Flour", ingredient.getNameOfIngredient(),
                "Ingredient name should be updated");
        Assertions.assertSame(ingredient, returned,
                "Method should return the same Ingredient instance");
    }



    /**
     * Test the default constructor which reads from System.in.
     * Simulates user input for ingredient details.
     */
    @Test
   public void defaultConstructorIngredientTest() {
        String input = """
                Sugar
                10
                grams
                4
                """;
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            Ingredient ingredient = new Ingredient();
            Assertions.assertEquals("Sugar", ingredient.getNameOfIngredient());
            Assertions.assertEquals(10.0f, ingredient.getIngredientAmount(), 0.001f);
            Assertions.assertEquals("grams", ingredient.getUnitMeasurement());
            Assertions.assertEquals(4.0, ingredient.getNumberCaloriesPerUnit(), 0.001);
            Assertions.assertEquals(40.0f, ingredient.getTotalCalories(), 0.001f); // 10 * 4
        } finally {
            System.setIn(originalIn); // restore System.in
        }
    }
}