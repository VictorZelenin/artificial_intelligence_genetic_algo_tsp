package genetic_algortithm_API.genetic_algorithm.implementations_of_GA;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing.OrderedCrossing;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.selection.ProportionalSelection;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by User on 25.02.2016.
 */
public class CanonicalGeneticAlgorithm implements GeneticAlgorithm {

    private Population currentPopulation;
    private Routes routes;
    private int startCityID;
    private double mutationProbability;
    private int numberOfEpoch = 0;


    public CanonicalGeneticAlgorithm(int startCityID, int sizeOfPopulation, double mutationProbability,
                                     int maxNumberOfIterations) throws Exception {
        this.startCityID = startCityID;
        this.mutationProbability = mutationProbability;

        routes = new Routes(100, new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes, startCityID);

//        checkElementOfPopulation(currentPopulation);

    }


    @Override
    public void checkElementOfPopulation(Population currentPopulation) {
        Phenotype strongestIndividual;
        double shortestRoute;
        List<Phenotype> phenotypes = currentPopulation.getPopulation()
                .stream()
                .collect(Collectors.toList());

        Collections.sort(phenotypes, (o1, o2) -> (o1.getFitnessValue(routes) < o2.getFitnessValue(routes)) ? -1 : 1);

        strongestIndividual = phenotypes.get(0);
        shortestRoute = strongestIndividual.getFitnessValue(routes);

        for (Phenotype phenotype : phenotypes) {
            System.out.println(phenotype.getFitnessValue(routes));
        }

        System.out.println();
        System.out.println("Shortest Route: " + strongestIndividual +
                " Length: " + shortestRoute);

    }

    @Override
    public Phenotype[] select(Selection selection) {

        Phenotype[] pairForCrossover = new Phenotype[2];
        Population population = null;
        try {
            population = currentPopulation.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        pairForCrossover[0] = selection.select(population, routes);
        population.getPopulation().remove(pairForCrossover[0]);
        pairForCrossover[1] = selection.select(population, routes);


        return pairForCrossover;
    }

    @Override
    public Population crossover(Crossing crossing) {

        int sizeOfPopulation = currentPopulation.getPopulation().size();
        boolean oddNumberOfPopulationSize;

        int quantityOfCrossing;
        Population newPopulation = null;
        try {
            newPopulation = currentPopulation.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


//        newPopulation.getPopulation().removeAll(newPopulation.getPopulation());


        if (sizeOfPopulation % 2 == 0) {
            quantityOfCrossing = sizeOfPopulation / 2;
            oddNumberOfPopulationSize = false;

        } else {
            quantityOfCrossing = sizeOfPopulation / 2 + 1;
            oddNumberOfPopulationSize = true;
        }

        for (int i = 0; i < quantityOfCrossing; i++) {

            if (oddNumberOfPopulationSize) {
                if (i == quantityOfCrossing - 1) {

                    Phenotype[] pairForCrossing = select(new ProportionalSelection());
                    Phenotype[] generatedFamily = new Phenotype[4];


                    try {
                        generatedFamily = crossing.crossover(pairForCrossing[0], pairForCrossing[1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (generatedFamily[2].getFitnessValue(routes) > generatedFamily[3].getFitnessValue(routes)) {
                        newPopulation.getPopulation().add(generatedFamily[3]);
                    } else {
                        newPopulation.getPopulation().add(generatedFamily[2]);
                    }

                }
            } else {
                Phenotype[] pairForCrossing = select(new ProportionalSelection());
                Phenotype[] generatedFamily = new Phenotype[4];

                try {
                    generatedFamily = crossing.crossover(pairForCrossing[0], pairForCrossing[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                newPopulation.getPopulation().add(generatedFamily[2]);
                newPopulation.getPopulation().add(generatedFamily[3]);


            }


        }


        return newPopulation;
    }

    @Override
    public void mutate(Mutation mutation, double mutationProbability) {

        double randomValue = Math.random();

        currentPopulation.getPopulation()
                .stream()
                .filter(phenotype -> randomValue <= mutationProbability)
                .forEach(mutation::mutate);


    }

    public static void main(String[] args) throws Exception {

        CanonicalGeneticAlgorithm GA = new CanonicalGeneticAlgorithm(1, 2, 0.2, 4);

        GA.checkElementOfPopulation(GA.currentPopulation);

        GA.crossover(new OrderedCrossing());

        GA.checkElementOfPopulation(GA.currentPopulation);
    }

}
