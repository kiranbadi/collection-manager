package edu.snhu;

import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * The RecipeHelper class provides static utility methods
 * for validating user input and printing ingredient details.
 * These methods are reused across the Recipe and Ingredient classes.</p>
 * @author Kiran Badi
 * @version 1.0
 *
 */

public class RecipeHelper {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private RecipeHelper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }
    /**
     * Validates and returns a float value from user input.
     *
     * @param scanner Scanner object for input
     * @param input   input message for the user
     * @return validated float value
     */
    public static float getValidFloatInput(Scanner scanner, String input) {
        scanner.useDelimiter("\\R");
        while (true) {
            System.out.print(input);
            if (scanner.hasNextFloat()) {
                float value = scanner.nextFloat();
                if (value < 0f) {
                    System.out.println("Invalid input. Please enter a non-negative numeric value.");
                    continue;
                }
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
    }

    /**
     * Validates and returns a double value from user input.
     *
     * @param scanner Scanner object for input
     * @param input   Input message for the user
     * @return validated double value
     */
    public static double getValidDoubleInput(Scanner scanner, String input) {
        while (true) {
            System.out.print(input);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                if (value < 0) {
                    System.out.println("Invalid input. Please enter a non-negative numeric value.");
                    continue;
                }
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next();
            }
        }
    }

    /**
     * Prompts the user for a non-empty string value.
     *
     * @param scanner Scanner object for input
     * @param input   input message for the user
     * @return validated non-empty string name
     */

    public static String getStringValue(Scanner scanner, String input) {
        while (true) {
            System.out.print(input);
            String name = scanner.nextLine().trim();
            if (name.contains("\t")) {
                System.out.println("Invalid input. Please enter a name without tabs.");
            } else if (!name.isBlank()) {
                return name;
            } else {
                System.out.println("Invalid input. Please enter a non-empty input.");
            }
            System.out.flush();
        }
    }

    /**
     * Prompts the user for a non-negative integer value.
     *
     * @param scanner Scanner object for input
     * @param input   input message for the user
     * @return validated non-negative integer value
     */

    public static Integer getIntegerValue(Scanner scanner,String input) {
        while (true) {
            System.out.print(input);
            String line = scanner.nextLine().trim();
            String trimmedValue = line.trim();
            if (trimmedValue.isEmpty()) {
                System.out.println("Invalid input. Please enter a non-empty value.");
                continue;
            }
            if (trimmedValue.contains(" ") || trimmedValue.contains("\t")) {
                System.out.println("Invalid input. Do not include spaces or tabs.");
                continue;
            }
            try {
                int value =  Integer.parseInt(trimmedValue);
                if (value < 0) {
                    System.out.println("Invalid input. Please enter a non-negative integer.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Prints the list of ingredients with their details on the console.
     *
     * @param ingredients List of Ingredient objects
     */
    public static void printIngredients(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            ingredient.printIngredient();
        }
    }
}
