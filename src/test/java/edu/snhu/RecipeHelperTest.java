package edu.snhu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>
 * Test class for RecipeHelper utility methods.
 * Includes tests for input validation and ingredient printing.
 * Test cases simulate user input and verify outputs.
 * It uses JUnit 5 for testing.
 * </p>
 *
 * @author Kiran Badi
 * @version 1.0
 */

public class RecipeHelperTest {

    // Redirects System.out to capture output for testing
    private final PrintStream originalOut = System.out;

    // ByteArrayOutputStream to capture the printed output
    private ByteArrayOutputStream outContent;


    /**
     * Sets up the output stream before each test.
     */
    @BeforeEach
   public void setUp() {
        outContent = new ByteArrayOutputStream();
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
     * Tests that the RecipeHelper class cannot be instantiated.
     * Verifies that an UnsupportedOperationException is thrown.
     */
    @Test
    public void testRecipeHelperInstantiation() throws NoSuchMethodException {
        Constructor<RecipeHelper> constructor =
                RecipeHelper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException thrown =
                Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
        Assertions.assertInstanceOf(UnsupportedOperationException.class, thrown.getCause());
        Assertions.assertEquals(
                "Utility class cannot be instantiated.",
                thrown.getCause().getMessage()
        );
    }

    /**
     * Tests the getValidFloatInput method.
     * Simulates user input and verifies the returned float value.
     */
    @Test
    public void testGetValidFloatInput() {
        Scanner scanner = new Scanner("3.14\n");
        float result = RecipeHelper.getValidFloatInput(scanner, "Enter a float value: ");
        Assertions.assertEquals(3.14f, result);
    }

    /**
     * Tests the getValidDoubleInput method.
     * Simulates user input and verifies the returned double value.
     */
    @Test
    public void testGetValidDoubleInput() {
        Scanner scanner = new Scanner("2.71828\n");
        double result = RecipeHelper.getValidDoubleInput(scanner, "Enter a double value: ");
        Assertions.assertEquals(2.71828, result);
    }


    /**
     * Tests the getStringValue method.
     * Simulates user input and verifies the returned string value.
     */
    @Test
    public void testGetStringValue() {
        Scanner scanner = new Scanner("Test Ingredient\n");
        String result = RecipeHelper.getStringValue(scanner, "Enter ingredient name: ");
        Assertions.assertEquals("Test Ingredient", result);
    }


    /**
     * Tests the getIntegerValue method.
     * Simulates user input and verifies the returned integer value.
     */
    @Test
    public void testGetIntegerValue() {
        Scanner scanner = new Scanner("42\n");
        int result = RecipeHelper.getIntegerValue(scanner, "Enter an integer value: ");
        Assertions.assertEquals(42, result);
    }

    /**
     * Tests the printIngredients method.
     * Verifies that the ingredients are printed correctly.
     */

    @Test
    public void testPrintIngredients() {
        Ingredient ingredient1 = new Ingredient("Sugar", 100.0f, "grams", 4.0);
        Ingredient ingredient2 = new Ingredient("Flour", 200.0f, "grams", 3.5);
        java.util.List<Ingredient> ingredients = java.util.Arrays.asList(ingredient1, ingredient2);
        RecipeHelper.printIngredients(ingredients);
        String output = outContent.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Sugar"), "Should print 'Sugar'");
        assertTrue(output.contains("Flour"), "Should print 'Flour'");
    }


    /**
     * Tests getIntegerValue with negative integer input.
     * Verifies that it prompts again and returns a valid integer.
     */
    @Test
    public void testGetIntegerValueWithNegativeInteger() {
        Scanner scanner = new Scanner("-5\n42\n");
        int result = RecipeHelper.getIntegerValue(scanner, "Enter an integer: ");
        Assertions.assertEquals(42, result);
    }

    /**
     * Tests getStringValue with empty string input.
     * Verifies that it prompts again and returns a valid string.
     */
    @Test
    public void testGetStringValueWithEmptyString() {
        Scanner scanner = new Scanner("\nTest\n");
        String result = RecipeHelper.getStringValue(scanner, "Enter a string: ");
        Assertions.assertEquals("Test", result);
    }


    /**
     * Tests getValidFloatInput with negative float input.
     * Verifies that it prompts again and returns a valid float.
     */
    @Test
    public void testGetFloatInputLessThanZero() {
        Scanner scanner = new Scanner("-1.0\n4.25\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            float value = RecipeHelper.getValidFloatInput(scanner, "Enter: ");
            Assertions.assertEquals(4.25f, value, 0.0001f);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Please enter a non-negative numeric value."));
        } finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests getValidFloatInput with invalid (non-numeric) input.
     * Verifies that it prompts again and returns a valid float.
     */
    @Test
    public void testGetFloatInputForInvalid() {
        Scanner scanner = new Scanner("abc\n3.0\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            float value = RecipeHelper.getValidFloatInput(scanner, "Enter: ");
            Assertions.assertEquals(3.0f, value, 0.0001f);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Please enter a numeric value."));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Tests getValidDoubleInput with negative double input.
     * Verifies that it prompts again and returns a valid double.
     */
    @Test
    public void testGetValidDoubleInputLessThanZero() {
        Scanner scanner = new Scanner("-1.0\n4.25\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            double value = RecipeHelper.getValidDoubleInput(scanner, "Enter: ");
            Assertions.assertEquals(4.25, value, 0.000001);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Please enter a non-negative numeric value."),
                    "Should print non-negative warning for negative input");
        } finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests getValidDoubleInput with invalid (non-numeric) input.
     * Verifies that it prompts again and returns a valid double.
     */
    @Test
    public void testGetValidDoubleInputWithInvalid() {
        Scanner scanner = new Scanner("nope\n-2\nx\n0\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            double value = RecipeHelper.getValidDoubleInput(scanner, "Enter: ");
            Assertions.assertEquals(0.0, value, 0.000001);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Please enter a numeric value."),
                    "Should print numeric warning at least once");
            assertTrue(output.contains("Invalid input. Please enter a non-negative numeric value."),
                    "Should print non-negative warning at least once");
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Tests getStringValue with invalid input.
     * Verifies that it prompts again and returns a valid string.
     */
    @Test
    public void testGetStringValueWithInvalidInput() {
        Scanner scanner = new Scanner("Bro\twnies\nBrownies\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            String value = RecipeHelper.getStringValue(scanner, "Enter: ");
            Assertions.assertEquals("Brownies", value);
            String output = out.toString(StandardCharsets.UTF_8);
            Assertions.assertTrue(output.contains("Invalid input. Please enter a name without tabs."),
                    "Should warn when input contains a tab");
        } finally {
            System.setOut(originalOut);
        }
    }



    /**
     * Tests getIntegerValue with empty input.
     * Verifies that it prompts again and returns a valid integer.
     */
    @Test
   public  void getIntegerValueWithEmptyValue() {
        Scanner scanner = new Scanner("\n7\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            Integer value = RecipeHelper.getIntegerValue(scanner, "Enter: ");
            Assertions.assertEquals(7, value);
            String output = out.toString(StandardCharsets.UTF_8);
            Assertions.assertTrue(output.contains("Invalid input. Please enter a non-empty value."));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Tests getIntegerValue with input containing tabs.
     * Verifies that it prompts again and returns a valid integer.
     */
    @Test
    public void getIntegerValueWithTabs() {
        Scanner scanner = new Scanner("12\t3\n5\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            Integer value = RecipeHelper.getIntegerValue(scanner, "Enter: ");
            Assertions.assertEquals(5, value);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Do not include spaces or tabs."));
        } finally {
            System.setOut(originalOut);
        }
    }


    /**
     * Tests getIntegerValue with input containing spaces.
     * Verifies that it prompts again and returns a valid integer.
     */
    @Test
    public void getIntegerValueWithSpaces() {
        Scanner scanner = new Scanner("12 3\n9\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

        try {
            Integer value = RecipeHelper.getIntegerValue(scanner, "Enter: ");
            Assertions.assertEquals(9, value);
            String output = out.toString(StandardCharsets.UTF_8);
            Assertions.assertTrue(output.contains("Invalid input. Do not include spaces or tabs."));
        } finally {
            System.setOut(originalOut);
        }
    }

    /**
     * Tests getIntegerValue with invalid (non-integer) input.
     * Verifies that it prompts again and returns a valid integer.
     */
    @Test
    public void getIntegerValueWithInvalidInput() {
        Scanner scanner = new Scanner("abc\n11\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
        try {
            Integer value = RecipeHelper.getIntegerValue(scanner, "Enter: ");
            Assertions.assertEquals(11, value);
            String output = out.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Invalid input. Please enter a valid integer."));
        } finally {
            System.setOut(originalOut);
        }
    }

}