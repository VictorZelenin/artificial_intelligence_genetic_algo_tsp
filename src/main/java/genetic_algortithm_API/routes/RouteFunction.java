package genetic_algortithm_API.routes;

import genetic_algortithm_API.elementary_parts.gene.Gene;

/**
 * Created by User on 24.02.2016.
 */
@FunctionalInterface
public interface RouteFunction {

    double getRouteWeightFunction(Gene firstCity, Gene secondCity);

}
