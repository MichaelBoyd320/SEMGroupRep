package com.napier.sem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

public class MainTest {

    @Test
    void testprintCapitalsinRegionbyPopulation() {
        // Create test data
        List<Region> capitals = new ArrayList<>();

        //Creating regions to compare to output
        Region berlin = new Region();
        berlin.setCityName("Berlin");
        berlin.setPopulation(3386667);

        Region paris = new Region();
        paris.setCityName("Paris");
        paris.setPopulation(2125246);

        //Adding capitals to list
        capitals.add(berlin);
        capitals.add(paris);

        // Prepare output
        StringBuilder output = new StringBuilder();

        // Instantiate the class and call the method
        ListOfCapitalsInRegion listOfCapitals = new ListOfCapitalsInRegion(null); // No database needed
        listOfCapitals.printCapitalsinRegionbyPopulation(capitals, output);

        // Expected output
        String expectedOutput = "Capital: Berlin - Population: 3386667\n" +
                "Capital: Paris - Population: 2125246\n";

        // Check if the output is correct
        assertEquals(expectedOutput, output.toString());
    }

    @Test
    void testprintRegions(){
        List<Region> regions = new ArrayList<>();

        //Creating regions to compare output
        Region CentralAfrica = new Region();
        CentralAfrica.setRegion("Central Africa");
        CentralAfrica.setContinent("Africa");

        Region SouthernEurope = new Region();
        SouthernEurope.setRegion("Southern Europe");
        SouthernEurope.setContinent("Europe");

        Region Melanesia = new Region();
        Melanesia.setRegion("Melanesia");
        Melanesia.setContinent("Oceania");

        //Adding to regions list
        regions.add(CentralAfrica);
        regions.add(SouthernEurope);
        regions.add(Melanesia);

        // Prepare output
        StringBuilder output = new StringBuilder();

        // Instantiate the class and call the method
        RegionsGlobal globalRegions = new RegionsGlobal(null); // No database needed
        globalRegions.printRegions(regions, output);

        // Expected output
        String expectedOutput = "Continent: Africa - Region: Central Africa\n" +
                                "Continent: Europe - Region: Southern Europe\n" +
                                "Continent: Oceania - Region: Melanesia\n";

        // Check if the output is correct
        assertEquals(expectedOutput, output.toString());
    }
}
