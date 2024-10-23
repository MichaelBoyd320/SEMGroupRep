package com.napier.sem;

import java.sql.*;

public class ListOfCapitalsInRegion {

    private Connection con;

    // Konstruktor, der die Verbindung entgegennimmt
    public ListOfCapitalsInRegion(Connection con) {
        this.con = con;
    }

    // Methode zur Abfrage der Hauptstädte nach Region
    public void getCapitalsInRegion(String region) {
        try {
            String strSelect =
                    "SELECT city.Name, city.Population "
                            + "FROM city "
                            + "JOIN country ON city.ID = country.Capital "
                            + "WHERE country.Continent = ? "
                            + "ORDER BY city.Population DESC ";

            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, region);  // Setzt den Kontinentnamen in die Abfrage

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                String cityName = rset.getString("Name");
                int population = rset.getInt("Population");
                System.out.println("Hauptstadt: " + cityName + " - Bevölkerung: " + population);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }
}
