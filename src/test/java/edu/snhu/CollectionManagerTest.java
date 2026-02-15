package edu.snhu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;


/**
 * <p>
 * Test class for CollectionManager.
 * This class contains unit tests to verify the functionality
 * of the CollectionManager class.
 * </p>
 * @author Kiran Badi
 * @version 1.0
 * @see CollectionManager
 */

public class CollectionManagerTest {


    /**
     * Test the main method of CollectionManager.
     * This test simulates user input to ensure the main method runs without exceptions.
     */

    @Test
    public void testMain() {
        CollectionManager manager = new CollectionManager();
        System.setIn(new ByteArrayInputStream(
                "4\n".getBytes(StandardCharsets.UTF_8)
        ));
        Assertions.assertDoesNotThrow(manager::main);
    }
}