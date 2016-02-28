package genetic_algortithm_API.genetic_algorithm.implementations_of_GA;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;

/**
 * Created by User on 28.02.2016.
 */
public class ElitistGeneticAlgorithm implements GeneticAlgorithm {



    @Override
    public void checkElementOfPopulation(Population currentPopulation) {

    }

    @Override
    public Phenotype[] select(Selection selection) {
        return new Phenotype[0];
    }

    @Override
    public Population crossover(Crossing crossing) {
        return null;
    }

    @Override
    public void mutate(Mutation mutation, double probability) {

    }
}
