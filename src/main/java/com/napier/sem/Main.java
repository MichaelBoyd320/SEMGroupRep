package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create new Application and connect to database
        Main a = new Main();

        String region = "Western Europe";

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        //CAPTIALS IN REGION
        ListOfCapitalsInRegion listOfCaps = new ListOfCapitalsInRegion(a.con);
        List<Region> capitals = listOfCaps.getRegionCapitals(region);
        StringBuilder output = new StringBuilder();
        listOfCaps.printCapitalsinRegionbyPopulation(capitals, output);
        //

        //REGIONS
        RegionsGlobal globalRegions = new RegionsGlobal(a.con);
        List<Region> regions = globalRegions.getRegions();
        output = new StringBuilder();
        globalRegions.printRegions(regions, output);
        //

        // Get countries in order of population
        PopRep popRep = new PopRep(a.con);
        ArrayList<Country> countriesInPopOrder = popRep.getCountriesPop();
        StringBuilder popoutput = new StringBuilder();
        popRep.printCountires(countriesInPopOrder, popoutput);

        // Disconnect from database
        a.disconnect();
    }

    public Connection con = null;

    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

}