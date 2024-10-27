package com.napier.sem;

import java.sql.*;

public class ListOfCapitalsInRegion {

    private PreparedStatement pstmt;

    public ListOfCapitalsInRegion(Connection con) {
        // Connection isn't used in this example, focus on testing result output
    }

    // Set the PreparedStatement for testing
    public void setPreparedStatement(PreparedStatement pstmt) {
        this.pstmt = pstmt;
    }

    public void getCapitalsInRegion(String region, StringBuilder output) {
        try {
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                String cityName = rset.getString("Name");
                int population = rset.getInt("Population");
                output.append("Hauptstadt: " + cityName + " - Bevölkerung: " + population + "\n");
            }
        } catch (SQLException e) {
            System.out.println("Failed to get population details");
        }
    }
}