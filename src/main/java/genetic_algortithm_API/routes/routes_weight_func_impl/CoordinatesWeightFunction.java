package genetic_algortithm_API.routes.routes_weight_func_impl;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.routes.RouteFunction;

import static java.lang.Math.*;

/**
 * Created by User on 24.02.2016.
 */
public class CoordinatesWeightFunction implements RouteFunction {
    @Override
    public double getRouteWeightFunction(City firstCity, City secondCity) {
        return sqrt(pow(firstCity.getX() - secondCity.getX(), 2) + pow(firstCity.getY() - secondCity.getY(), 2));
    }
}
