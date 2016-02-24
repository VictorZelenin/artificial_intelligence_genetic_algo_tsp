package genetic_algortithm_API.routes;

import genetic_algortithm_API.elementary_parts.gene.Gene;

/**
 * Created by User on 24.02.2016.
 */
public class Routes {

    private double[][] routesMatrix;
    private Gene[] cities;
    private int quantityOfRoutes;


    public Routes(double[][] routesMatrix) {
        this.routesMatrix = routesMatrix;
        quantityOfRoutes = routesMatrix.length * routesMatrix[0].length;
    }


    // no used routeMatrix
    public Routes(int quantityOfRoutes) {

        generateRandomCities(quantityOfRoutes, this.cities);

    }


    public double getWeight(Gene firstCity, Gene secondCity, RouteFunction weightFunction) {
        return weightFunction.getRouteWeightFunction(firstCity, secondCity);
    }


    public int getQuantityOfRoutes() {
        return quantityOfRoutes;
    }


    private void generateRandomCities(int quantityOfRoutes, Gene[] cities) {

        for (int i = 0; i < quantityOfRoutes; i++) {
            cities[i] = new Gene(i + 1);
        }

    }


}
