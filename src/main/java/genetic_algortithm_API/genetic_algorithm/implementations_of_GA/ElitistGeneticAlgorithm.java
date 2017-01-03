package genetic_algortithm_API.genetic_algorithm.implementations_of_GA;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing.TwoPointsCrossing;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation.GreedyMutation;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation.SinglePointMutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by User on 28.02.2016.
 */
public class ElitistGeneticAlgorithm implements GeneticAlgorithm {
    private Population currentPopulation;
    private Routes routes;
    private Phenotype result;

    public ElitistGeneticAlgorithm(int sizeOfPopulation, double mutationProbability,
                                   int maxNumberOfIterations, City[] cities,
                                   Crossing crossing, Mutation mutation) throws IllegalLengthOfPhenotypeException, InvalidGeneException {
        routes = new Routes(cities, new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes);
        ArrayList<Phenotype> results = new ArrayList<>();

        for (int i = 0; i < maxNumberOfIterations; i++) {
            checkElementOfPopulation();
            createNewGeneration(crossing);
            mutate(mutation, mutationProbability);
            results.add(currentPopulation.getPopulation().get(0));
        }

        results.forEach(o -> System.out.print(o.getFitnessValue(routes) + " "));
        System.out.println();
        Collections.sort(results, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));
        results.forEach(o -> System.out.print(o.getFitnessValue(routes) + " "));
        result = results.get(0);
    }


    public ElitistGeneticAlgorithm(int sizeOfPopulation, double mutationProbability,
                                   int maxNumberOfIterations, City[] cities) throws IllegalLengthOfPhenotypeException, InvalidGeneException {
        routes = new Routes(cities, new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes);
        ArrayList<Phenotype> results = new ArrayList<>();

        for (int i = 0; i < maxNumberOfIterations; i++) {
            checkElementOfPopulation();
            createNewGeneration(new TwoPointsCrossing());
            mutate(new SinglePointMutation(), mutationProbability);
            results.add(currentPopulation.getPopulation().get(0));
        }

        results.forEach(o -> System.out.print(o.getFitnessValue(routes) + " "));
        System.out.println();
        Collections.sort(results, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));
        System.out.println("size " + results.size());
        System.out.println("iterations " + maxNumberOfIterations);
        results.forEach(o -> System.out.print(o.getFitnessValue(routes) + " "));
        result = results.get(0);
        System.out.println("RESULT: " + result.getFitnessValue(routes));

    }


    public ElitistGeneticAlgorithm(int sizeOfPopulation, double mutationProbability,
                                   int maxNumberOfIterations) throws InvalidGeneException, IllegalLengthOfPhenotypeException {
        routes = new Routes(20, new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes);
        ArrayList<Phenotype> results = new ArrayList<>(8);

        for (int i = 0; i < maxNumberOfIterations; i++) {
            checkElementOfPopulation();
            createNewGeneration(new TwoPointsCrossing());
            mutate(new GreedyMutation(), mutationProbability);
            results.add(currentPopulation.getPopulation().get(0));
            System.out.println(result.getFitnessValue(routes));
        }

        Collections.sort(results, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));
        results.forEach(o -> System.out.println(o.getFitnessValue(routes)));
        result = results.get(0);
        System.out.println("RESULT: " + result);

    }


    public Phenotype getResult() {
        return result;
    }


    @Override
    public void checkElementOfPopulation() {
        Collections.sort(currentPopulation.getPopulation(),
                Comparator.comparingDouble(o -> o.getFitnessValue(routes)));
    }

    @Override
    public Phenotype[] select(Selection selection) {
        return new Phenotype[0];
    }

    public void createNewGeneration(Crossing crossing) throws IllegalLengthOfPhenotypeException, InvalidGeneException {
        int j = 0;
        for (int i = currentPopulation.getPopulation().size() / 4; i < currentPopulation.getPopulation().size(); i++) {
            currentPopulation.getPopulation().set(i, crossing.crossover(currentPopulation.getPopulation().get(j),
                    currentPopulation.getPopulation().get(j + 1)));
        }
    }

    @Override
    public Population crossover(Crossing crossing) {
        return null;
    }

    @Override
    public void mutate(Mutation mutation, double probability) {
        for (int i = 0; i < currentPopulation.getPopulation().size(); i++) {
            if (Math.random() < probability) {
                mutation.mutate(currentPopulation.getPopulation().get(i), routes);
            }
        }
    }
}
