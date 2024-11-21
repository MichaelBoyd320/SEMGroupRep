package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class RegionsGlobal {

    private Connection con;

    public RegionsGlobal(Connection con) {
        this.con = con;
    }

    public List<Region> getRegions() {
        String strSelect = "SELECT Continent, Region\n "
                                + "FROM\n "
                                + "country\n "
                                + "GROUP BY\n "
                                + "Continent, Region\n ";

        List<Region> regions = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(strSelect)) {

            // execute Query
            ResultSet rset = pstmt.executeQuery();

            // Add results to list
            while (rset.next()) {
                // Create a new Region object
                Region region = new Region();

                // Retrieve values from the ResultSet and set them using setters
                region.setRegion(rset.getString("Region"));
                region.setContinent(rset.getString("Continent"));

                // Add the object to the list
                regions.add(region);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details: " + e.getMessage());
        }

        return regions;
    }

    public void printRegions(List<Region> regions, StringBuilder output) {
        regions.sort(Comparator.comparing(Region::getContinent));

        for (Region region : regions) {
            output.append("Continent: ")
                    .append(region.getContinent())
                    .append(" - Region: ")
                    .append(region.getRegion())
                    .append("\n");
        }
        System.out.println(output.toString());
    }
}