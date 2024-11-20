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
    public List<RegionCapital> getRegionCapitals(String region) {
        String strSelect = "SELECT city.Name, city.Population FROM city "
                + "JOIN country ON city.ID = country.Capital "
                + "WHERE country.Region = ? "
                + "ORDER BY city.Population DESC";

        List<RegionCapital> capitals = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(strSelect)) {
            // Setze den Parameter für die Region
            pstmt.setString(1, region);

            // Führe die Abfrage aus
            ResultSet rset = pstmt.executeQuery();

            // Füge die Ergebnisse zur Liste hinzu
            while (rset.next()) {
                String cityName = rset.getString("Name");
                int population = rset.getInt("Population");
                capitals.add(new RegionCapital(cityName, population));
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

        return capitals;
    }

    public void printCapitalsinRegionbyPopulation(List<RegionCapital> capitals, StringBuilder output) {
        for (RegionCapital capital : capitals) {
            output.append("Capital: ")
                    .append(capital.getCityName())
                    .append(" - Population: ")
                    .append(capital.getPopulation())
                    .append("\n");
        }
        System.out.println(output.toString());
    }
}