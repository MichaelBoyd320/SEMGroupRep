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
        // Testdaten erstellen
        List<RegionCapital> capitals = new ArrayList<>();
        capitals.add(new RegionCapital("Berlin", 3386667));
        capitals.add(new RegionCapital("Paris", 2125246));

        // Output vorbereiten
        StringBuilder output = new StringBuilder();

        // Klasse instanziieren und Methode aufrufen
        ListOfCapitalsInRegion listOfCapitals = new ListOfCapitalsInRegion(null); // Keine DB benötigt
        listOfCapitals.printCapitalsinRegionbyPopulation(capitals, output);

        // Erwarteter Output
        String expectedOutput = "Capital: Berlin - Population: 3386667\n" +
                "Capital: Paris - Population: 2125246\n";

        // Überprüfen, ob der Output korrekt ist
        assertEquals(expectedOutput, output.toString());
    }
}
