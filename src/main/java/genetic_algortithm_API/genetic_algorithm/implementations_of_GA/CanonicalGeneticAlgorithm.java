package genetic_algortithm_API.genetic_algorithm.implementations_of_GA;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.Set;

/**
 * Created by User on 25.02.2016.
 */
public class CanonicalGeneticAlgorithm implements GeneticAlgorithm {

    Population currentPopulation;
    Routes routes;
    int startCityID;
    double mutationProbability;
    int numberOfEpoch = 0;


    public CanonicalGeneticAlgorithm(int startCityID, int sizeOfPopulation, double mutationProbability,
                                     int maxNumberOfIterations) throws Exception {
        this.startCityID = startCityID;
        this.mutationProbability = mutationProbability;

        routes = new Routes("input.txt", new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes, startCityID);

//        checkElementOfPopulation(currentPopulation);

    }

    private double averageFitness() {
        Set<Phenotype> phenotypes = currentPopulation.getPopulation();

        double fitnessValue = 0;

        for (Phenotype phenotype : phenotypes) {
            fitnessValue += phenotype.getFitnessValue(routes) / phenotypes.size();
        }

        return fitnessValue;
    }


    @Override
    public void checkElementOfPopulation(Population currentPopulation) {

    }

    @Override
    public Population select(Population oldPopulation, Selection selection) {
        return null;
    }

    @Override
    public Population crossover(Population oldPopulation, Crossing crossing) {
        return null;
    }

    @Override
    public Population mutate(Population oldPopulation, double probability, Mutation mutation) {
        return null;
    }

    public static void main(String[] args) throws Exception {

        CanonicalGeneticAlgorithm GA = new CanonicalGeneticAlgorithm(1, 5, 0.2, 4);
        System.out.println(GA.averageFitness());
    }

}
