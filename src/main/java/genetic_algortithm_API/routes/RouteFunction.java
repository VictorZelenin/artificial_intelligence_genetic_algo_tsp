package genetic_algortithm_API.routes;

import genetic_algortithm_API.elementary_parts.city.City;

/**
 * Created by User on 24.02.2016.
 */
@FunctionalInterface
public interface RouteFunction {

    double getRouteWeightFunction(City firstCity, City secondCity);

}
