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


    public double getWeight(Gene firstCity, Gene secondCity, RouteFunction weightFunction) {
        return weightFunction.getRouteWeightFunction(firstCity, secondCity);
    }


    public int getQuantityOfRoutes() {
        return quantityOfRoutes;
    }


}
