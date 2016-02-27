package genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.routes.Routes;

/**
 * Created by User on 26.02.2016.
 */

@FunctionalInterface
public interface Selection {

    Phenotype select(Population currentPopulation, Routes routes);

}
