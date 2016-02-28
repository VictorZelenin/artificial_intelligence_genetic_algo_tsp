package genetic_algortithm_API.routes;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by User on 24.02.2016.
 */

// rewrite scanner parsing!
public class Routes {

    private double[][] routesMatrix;
    private int[] citiesID;
    private City[] cities;
    private int quantityOfRoutes;



    public Routes(String fileName, RouteFunction routeFunction) {

        cities = generateCitiesFromFile(fileName);

        routesMatrix = generateRoutesMatrix(cities, routeFunction);

    }


    // no used routeMatrix
    public Routes(int quantityOfRoutes, RouteFunction routeFunction) {
        cities = new City[quantityOfRoutes];
        generateRandomCities(quantityOfRoutes, this.cities);

        routesMatrix = generateRoutesMatrix(cities, routeFunction);
        this.quantityOfRoutes = quantityOfRoutes;

    }


    public static double getWeight(City firstCity, City secondCity, RouteFunction weightFunction) {
        return weightFunction.getRouteWeightFunction(firstCity, secondCity);
    }


    public int getQuantityOfRoutes() {
        return quantityOfRoutes;
    }


    public City[] getCities() {
        return cities;
    }

    private void generateRandomCities(int quantityOfRoutes, City[] cities) {

        citiesID = new int[quantityOfRoutes];

        for (int i = 0; i < quantityOfRoutes; i++) {

            cities[i] = new City(i + 1);

            citiesID[i] = i + 1;
        }

    }

    private City[] generateCitiesFromFile(String fileName) {

        City[] cities;

        File file = new File(fileName);
        Scanner scanner = null;


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Can not create such file");
            }
        }

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("Can not find such file");
        }

        int N = Integer.parseInt(scanner.next());


        cities = new City[N];
        citiesID = new int[N];
        quantityOfRoutes = N;

        int i = 0;
        while (scanner.hasNext()) {
            cities[i] = new City(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()), i + 1);
            citiesID[i] = i + 1;
            i++;
        }


        return cities;
    }

    private double[][] generateRoutesMatrix(City[] cities, RouteFunction routeFunction) {

        int N = cities.length;
        quantityOfRoutes = N;

        double[][] matrix = new double[N][N];


        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities.length; j++) {

                matrix[i][j] = routeFunction.getRouteWeightFunction(cities[i], cities[j]);

            }
        }

        return matrix;

    }


    public int[] getCitiesID() {
        return citiesID;
    }

    public double[][] getRoutesMatrix() {
        return routesMatrix;
    }


    public  void printMatrix() {

        for (int i = 0; i < routesMatrix.length; i++) {
            for (int j = 0; j < routesMatrix[i].length; j++) {
                System.out.print(routesMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }


    // testing unit
    public static void main(String[] args) {
//        Routes routes = new Routes(5, new CoordinatesWeightFunction());

        Routes routes = new Routes("input.txt", new CoordinatesWeightFunction());
        System.out.println(Arrays.toString(routes.getCities()));
        System.out.println(Arrays.toString(routes.getCitiesID()));
//        printMatrix(routes.getRoutesMatrix());
//        printMatrix(routes.routesMatrix);


    }

}
