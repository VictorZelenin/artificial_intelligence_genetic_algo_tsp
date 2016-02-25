package genetic_algortithm_API.routes;

import genetic_algortithm_API.elementary_parts.gene.Gene;

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

    //    private double[][] routesMatrix;
    private int[] citiesID;
    private Gene[] cities;
    private int quantityOfRoutes;


    // generate cities by route matrix
    public Routes(double[][] routesMatrix) {
//        this.routesMatrix = routesMatrix;
        quantityOfRoutes = routesMatrix.length * routesMatrix[0].length;
    }

    public Routes(String fileName) {

        cities = generateCitiesFromFile(fileName);

//        routesMatrix = parseRoutesMatrix(fileName);

    }


    // no used routeMatrix
    public Routes(int quantityOfRoutes) {
        cities = new Gene[quantityOfRoutes];
        generateRandomCities(quantityOfRoutes, this.cities);

    }


    public double getWeight(Gene firstCity, Gene secondCity, RouteFunction weightFunction) {
        return weightFunction.getRouteWeightFunction(firstCity, secondCity);
    }


    public int getQuantityOfRoutes() {
        return quantityOfRoutes;
    }


    public Gene[] getCities() {
        return cities;
    }

    private void generateRandomCities(int quantityOfRoutes, Gene[] cities) {

        for (int i = 0; i < quantityOfRoutes; i++) {

            cities[i] = new Gene(i + 1);
        }

    }

    private Gene[] generateCitiesFromFile(String fileName) {

        Gene[] cities;

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


        cities = new Gene[N];
        citiesID = new int[N];

        int i = 0;
        while (scanner.hasNext()) {
            cities[i] = new Gene(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()), i + 1);
            citiesID[i] = i + 1;
            i++;
        }


        return cities;
    }


    public int[] getCitiesID() {
        return citiesID;
    }


    // testing unit
    public static void main(String[] args) {
//        Routes routes = new Routes(10);

        Routes routes = new Routes("input.txt");
        System.out.println(Arrays.toString(routes.getCities()));
        System.out.println(Arrays.toString(routes.getCitiesID()));
//        printMatrix(routes.routesMatrix);


    }

}
