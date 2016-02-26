package genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators;

import genetic_algortithm_API.elementary_parts.population.Population;

/**
 * Created by User on 26.02.2016.
 */

@FunctionalInterface
public interface Selection {

    Population select(Population oldPopulation);

}
