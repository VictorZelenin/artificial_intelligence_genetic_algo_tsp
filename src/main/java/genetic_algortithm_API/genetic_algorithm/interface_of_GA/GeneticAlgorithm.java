package genetic_algortithm_API.genetic_algorithm.interface_of_GA;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;

/**
 * Created by User on 26.02.2016.
 */
public interface GeneticAlgorithm {

    void checkElementOfPopulation();

    Phenotype[] select(Selection selection);

    Population crossover(Crossing crossing) throws InvalidGeneException;

    void mutate(Mutation mutation, double probability);
}
