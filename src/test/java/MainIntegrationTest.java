package com.napier.sem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
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
        List<Region> TestCapitals = testListOfCap.getRegionCapitals("Western Europe");
        testListOfCap.printCapitalsinRegionbyPopulation(TestCapitals, output);

        // Expected output
        String expectedOutput = "Capital: Berlin - Population: 3386667\n" +
                "Capital: Paris - Population: 2125246\n";

        // Check if the output contains the expected content
        assertTrue(output.toString().contains(expectedOutput));
    }

    @Test
    void testgetRegions(){
        // Create an instance of RegionsGlobal with the current connection
        RegionsGlobal testglobalRegions = new RegionsGlobal(main.con);
        StringBuilder output = new StringBuilder();

        // Call the method to get all regions
        testglobalRegions.getRegions();
        List<Region> TestRegions = testglobalRegions.getRegions();
        testglobalRegions.printRegions(TestRegions, output);

        // Expected output
        String expectedOutput = "Continent: Africa - Region: Central Africa\n" +
                                "Continent: Africa - Region: Eastern Africa\n" +
                                "Continent: Africa - Region: Western Africa\n" +
                                "Continent: Africa - Region: Southern Africa\n" +
                                "Continent: Africa - Region: Northern Africa\n" +
                                "Continent: Antarctica - Region: Antarctica\n" ;

        // Check if the output contains the expected content
        assertTrue(output.toString().contains(expectedOutput));
    }
    @Test
    void testGetCountriesBasedOnPopulation()
    {
        PopRep popRep = new PopRep(main.con);
        ArrayList<Country> countriesInPopOrder = popRep.getCountriesPop();
        popRep.printCountires(countriesInPopOrder);
    }
}
