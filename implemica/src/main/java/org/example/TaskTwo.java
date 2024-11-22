package org.example;

import java.util.*;

public class TaskTwo {
    public static void bellmanFordAdventure(Map<String, Map<String, Integer>> graph, String source, String destination) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();

        // Initialize distances from A to B point as infinity
        for (String point : graph.keySet()) {
            distances.put(point, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        //Outer loop relaxation (V-1 times)
        int verticesCount = graph.size();
        for (int i = 0; i < verticesCount - 1; i++) {
            //Iterating through all points
            for (String point : graph.keySet()) {
                //Iterating through neighbors of the current  point
                for (Map.Entry<String, Integer> neighbor : graph.get(point).entrySet()) {
                    String neighborPoint = neighbor.getKey();
                    int weight = neighbor.getValue();
                    //Relaxation step
                    if (distances.get(point) != Integer.MAX_VALUE && distances.get(point) + weight < distances.get(neighborPoint)) {
                        distances.put(neighborPoint, distances.get(point) + weight);
                        predecessors.put(neighborPoint, point);
                    }
                }
            }
        }

        //Check for negative-weight cycles
        for (String point : graph.keySet()) {
            for (Map.Entry<String, Integer> neighbor : graph.get(point).entrySet()) {
                String neighborPoint = neighbor.getKey(); //Retrieves neighbor point's name
                int weight = neighbor.getValue();
                if (distances.get(point) != Integer.MAX_VALUE && distances.get(point) + weight < distances.get(neighborPoint)) {
                    System.out.println("Graph contains cycle with negative weight");
                    return;
                }
            }
        }

        //Display the shortest path, also checked if path is available
        if (!distances.containsKey(destination) || distances.get(destination) == Integer.MAX_VALUE) {
            System.out.println("Destination " + destination + " is unreachable from " + source + ".");
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at = destination; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("Shortest path from " + source + " to " + destination + " is: " + path + " with cost: " + distances.get(destination));
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter the number of test cases:");
        int testCases = myScanner.nextInt();
        myScanner.nextLine();

        for (int t = 1; t <= testCases; t++) {
            System.out.println("Test case #" + t);  //Just to add a new one number for test case

            System.out.println("Enter the number of cities:");
            int numberOfCities = myScanner.nextInt();
            myScanner.nextLine();
            Map<String, Map<String, Integer>> graph = new HashMap<>(); //Key-Value, declaring and initializing a graph as an adjacency list
            List<String> citiesList = new ArrayList<>();  //Creation of list with cities

            System.out.println("Enter the name of cities");
            for (int i = 0; i < numberOfCities; i++) {
                System.out.println("Enter the name of city " + (i + 1) + ":");//The same as with test cases - for city number
                String cityName = myScanner.nextLine().trim();
                citiesList.add(cityName);
                graph.put(cityName, new HashMap<>());
            }

            System.out.println("Enter connections between cities:");
            for (int i = 0; i < numberOfCities; i++) {
                String cityName = citiesList.get(i);

                System.out.println("Enter the number of neighbors for " + cityName + ":");
                int neighborsCount = myScanner.nextInt();
                myScanner.nextLine();

                for (int j = 0; j < neighborsCount; j++) {
                    System.out.println("Enter the neighbor index (1-based) and transportation cost:");
                    int neighborIndex = myScanner.nextInt() - 1;
                    int cost = myScanner.nextInt();
                    myScanner.nextLine();

                    /*Additional check for cases with "broken index", waisted all evening on this problem
                    so placed it here as analogue of Exception*/
                    if (neighborIndex < 0 || neighborIndex >= citiesList.size()) {
                        System.out.println("Invalid neighbor index. Skipping...");
                        continue;
                    }

                    String neighborName = citiesList.get(neighborIndex);
                    graph.get(cityName).put(neighborName, cost);
                }
            }

            System.out.println("Enter the source and destination cities:");
            String source = myScanner.next();
            String destination = myScanner.next();
            myScanner.nextLine();

            bellmanFordAdventure(graph, source, destination);
            System.out.println();
        }

        myScanner.close();
    }
}
