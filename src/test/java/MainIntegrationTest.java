package com.napier.sem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

public class MainIntegrationTest {

    static Main main;
    private Connection con;

    // Initialize Main and establish a database connection
    @BeforeAll
    static void init() {
        main = new Main();
        main.connect("localhost:33060", 30000);
    }

    @Test
    void testGetCapitalsInRegion() {
        // Create an instance of ListOfCapitalsInRegion with the current connection
        ListOfCapitalsInRegion testListOfCap = new ListOfCapitalsInRegion(main.con);
        StringBuilder output = new StringBuilder();

        // Call the method to get capitals in the specified region
        testListOfCap.getRegionCapitals("Western Europe");
        List<RegionCapital> TestCapitals = testListOfCap.getRegionCapitals("Western Europe");
        testListOfCap.printCapitalsinRegionbyPopulation(TestCapitals, output);

        // Expected output
        String expectedOutput = "Capital: Berlin - Population: 3386667\n" +
                "Capital: Paris - Population: 2125246\n";

        // Check if the output contains the expected content
        assertTrue(output.toString().contains(expectedOutput));
    }
}
