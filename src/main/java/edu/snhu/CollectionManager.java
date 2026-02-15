package edu.snhu;

/*
    Collection Manager is complied and build using JDK 25
    It is a Maven-based project and uses JUnit 5 for testing.
    It uses the following classes:
    RecipeBox
    Recipe
    Ingredient
    RecipeHelper

    when run with maven command: mvn clean install test javadoc:javadoc javadoc:test-javadoc
    it will compile the code, run the tests, and generate javadocs for both main and test classes.
    API documentation will be available in the target / site / apidocs directory.
    Test code documentation will be available in the target / site / testapidocs directory.
    Test reports will be available in the target / surefire-reports directory.
 */

/**
 * <p>
 * The CollectionManager class serves as the entry point
 * for the Recipe Box application. It initializes the
 * RecipeBox and starts the user interaction menu.</p>
 * @author Kiran Badi
 * @version 1.0
 */
public class CollectionManager {

    /**
     * Main method to run the Recipe Box application.
     */
    void main() {
        RecipeBox recipeBox = new RecipeBox();
        recipeBox.menu();
    }
}
