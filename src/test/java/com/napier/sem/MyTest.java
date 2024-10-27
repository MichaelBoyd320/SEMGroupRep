package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import static org.mockito.Mockito.*;

class MainTest {

    @Test
    void testGetCapitalsInRegion() throws SQLException {
        // Mock the PreparedStatement and ResultSet
        PreparedStatement mockPstmt = mock(PreparedStatement.class);
        ResultSet mockRset = mock(ResultSet.class);

        // Mock the return data of the ResultSet
        when(mockRset.next()).thenReturn(true).thenReturn(true).thenReturn(false);  // Simulates 2 rows
        when(mockRset.getString("Name")).thenReturn("Berlin").thenReturn("Paris");
        when(mockRset.getInt("Population")).thenReturn(3500000).thenReturn(2100000);

        // Mock the PreparedStatement to return the mocked ResultSet
        when(mockPstmt.executeQuery()).thenReturn(mockRset);

        // Create an instance of the class to test
        ListOfCapitalsInRegion capitalsInRegion = new ListOfCapitalsInRegion(null);  // Connection is null for simplicity

        // Override the PreparedStatement in the class with the mocked one
        capitalsInRegion.setPreparedStatement(mockPstmt);  // Assuming you modify the class for testing

        // Capture the output with a StringBuilder
        StringBuilder output = new StringBuilder();
        capitalsInRegion.getCapitalsInRegion("Europe", output);  // Passing a StringBuilder for output capturing

        // Expected results
        String expectedFirstCity = "Hauptstadt: Berlin - Bevölkerung: 3500000\n";
        String expectedSecondCity = "Hauptstadt: Paris - Bevölkerung: 2100000\n";

        // Assertions to compare the expected and actual results
        assertTrue(output.toString().contains(expectedFirstCity));
        assertTrue(output.toString().contains(expectedSecondCity));
    }
}