package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PopRep
{
    private Connection con;

    public PopRep(Connection conget)
    {
        con = conget;
    }
    public ArrayList<Country> getCountriesPop() {
        // creating an initial empty arraylist
        ArrayList countriesPop = new ArrayList();
        // query for getting the pops in order
        String getCountires = "SELECT Code, Name, Continent, Population FROM country ORDER BY population DESC";

        // a try catch just incase something goes wrong
        // also prepares the statement for execution
        try (PreparedStatement pstmt = con.prepareStatement(getCountires))
        {
            // makes a result set
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // goes through the result set and adds each rows details to a new country object
                Country country = new Country();
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.population = rs.getInt("Population");

                // adds the country object to the list
                countriesPop.add(country);
            }
        } catch (Exception e)
        {
            // shows what went wrong, if there was an issue
            System.out.println(e.toString());
        }


        return countriesPop;
    }
    public void printCountires(ArrayList<Country> countries, StringBuilder output)
    {
        // prints a title for this part
        System.out.println("Printing Countries");

        // sorts the list, though the get statements may get them sorted anyway
        countries.sort(Comparator.comparing(Country::getPopulation).reversed());


        for (Country country : countries)
        {
            // adds all the parts of the list to a output
            output.append("Country: " + country.name).append(" Population: " + country.population).append("\n");

        }
        // prints the output
        System.out.println(output.toString());
    }
}
