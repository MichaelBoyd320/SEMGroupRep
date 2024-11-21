package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfCapitalsInRegion {

    private Connection con;

    // Constructor that receives the database connection
    public ListOfCapitalsInRegion(Connection con) {
        this.con = con;
    }

    // Method to query capitals by region and output results to the StringBuilder
    public List<Region> getRegionCapitals(String region) {
        String strSelect = "SELECT city.Name, city.Population FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = ? "
                + "ORDER BY city.Population DESC";

        List<Region> capitals = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(strSelect)) {
            // Set parameter for region
            pstmt.setString(1, region);

            // execute Query
            ResultSet rset = pstmt.executeQuery();

            // Add results to list
            while (rset.next()) {
                // Create a new Region object
                Region regionObject = new Region();

                // Retrieve values from the ResultSet and set them using setters
                regionObject.setCityName(rset.getString("Name"));
                regionObject.setPopulation(rset.getInt("Population"));

                // Add the object to the list
                capitals.add(regionObject);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

        return capitals;
    }

    public void printCapitalsinRegionbyPopulation(List<Region> capitals, StringBuilder output) {
        for (Region capital : capitals) {
            output.append("Capital: ")
                    .append(capital.getCityName())
                    .append(" - Population: ")
                    .append(capital.getPopulation())
                    .append("\n");
        }
        System.out.println(output.toString());
    }
}