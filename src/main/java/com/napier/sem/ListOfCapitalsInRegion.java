package com.napier.sem;

import java.sql.*;

public class ListOfCapitalsInRegion {

    private Connection con;

    // Constructor that receives the database connection
    public ListOfCapitalsInRegion(Connection con) {
        this.con = con;
    }

    // Method to query capitals by region and output results to the StringBuilder
    public void getCapitalsInRegion(String region, StringBuilder output) {
        String strSelect = "SELECT city.Name, city.Population FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = ? "
                + "ORDER BY city.Population DESC";

        try (PreparedStatement pstmt = con.prepareStatement(strSelect)) {
            // Set the parameter for the region
            pstmt.setString(1, region);

            // Execute the query
            ResultSet rset = pstmt.executeQuery();
            boolean hasResults = false;

            // Iterate through the results and append them to the StringBuilder
            while (rset.next()) {
                hasResults = true;
                String cityName = rset.getString("Name");
                int population = rset.getInt("Population");
                output.append("Capital: " + cityName + " - Population: " + population + "\n");
            }

            // Check if any results were found
            if (!hasResults) {
                System.out.println("No results for region: " + region);
            } else {
                System.out.println("Results found:");
                System.out.println(output.toString());
            }

        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }
    }
}
