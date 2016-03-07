package genetic_algortithm_API.genetic_algorithm.implementations_of_GA;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.exceptions.SelectionForCrossoverException;
import genetic_algortithm_API.genetic_algorithm.interface_of_GA.GeneticAlgorithm;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing.OrderedCrossing;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation.SinglePointMutation;
import genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.selection.ProportionalSelection;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by User on 25.02.2016.
 */
public class CanonicalGeneticAlgorithm implements GeneticAlgorithm {

    private Population currentPopulation;
    private Routes routes;
    private Phenotype result;


    public CanonicalGeneticAlgorithm(int startCityID, int sizeOfPopulation, double mutationProbability,
                                     int maxNumberOfIterations) throws IllegalLengthOfPhenotypeException, InvalidGeneException {

        ArrayList<Phenotype> phenotypes = new ArrayList<>();
//        this.routes = routes;
        routes = new Routes("input.txt", new CoordinatesWeightFunction());
//        routes.printMatrix();
        currentPopulation = new Population(sizeOfPopulation, routes, startCityID);

//
        for (int i = 0; i < maxNumberOfIterations; i++) {


            crossover(new OrderedCrossing());

            mutate(new SinglePointMutation(), mutationProbability);

            currentPopulation.getPopulation().add(getStrongestPhenotype());

//            checkElementOfPopulation(currentPopulation);

            phenotypes.add(getStrongestPhenotype());
        }


        Collections.sort(phenotypes, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));

//        System.out.println(currentPopulation);
        System.out.println(phenotypes.get(0));
        System.out.println(phenotypes.get(0).getFitnessValue(routes));

//        checkElementOfPopulation(currentPopulation);

    }

    public CanonicalGeneticAlgorithm(int startCityID, int sizeOfPopulation, double mutationProbability,
                                     int maxNumberOfIterations, City[] cities) throws Exception {
        ArrayList<Phenotype> phenotypes = new ArrayList<>();
        routes = new Routes(cities, new CoordinatesWeightFunction());
        currentPopulation = new Population(sizeOfPopulation, routes, startCityID);

        for (int i = 0; i < maxNumberOfIterations; i++) {


            crossover(new OrderedCrossing());

            mutate(new SinglePointMutation(), mutationProbability);

            currentPopulation.getPopulation().add(getStrongestPhenotype());

//            checkElementOfPopulation(currentPopulation);

            phenotypes.add(getStrongestPhenotype());
        }


        Collections.sort(phenotypes, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));

//        System.out.println(currentPopulation);
        System.out.println(phenotypes.get(0));
        System.out.println(phenotypes.get(0).getFitnessValue(routes));
        result = phenotypes.get(0);

    }

    @Override
    public void checkElementOfPopulation(Population currentPopulation) {
        Phenotype strongestIndividual = getStrongestPhenotype();
        double shortestRoute;
        shortestRoute = strongestIndividual.getFitnessValue(routes);
//
//        for (Phenotype phenotype : phenotypes) {
//            System.out.println(phenotype.getFitnessValue(routes));
//        }

        System.out.println();
        System.out.println("Shortest Route: " + strongestIndividual +
                " Length: " + shortestRoute);


    }

    private Phenotype getStrongestPhenotype() {
        Phenotype strongestIndividual;
//        double shortestRoute;
        List<Phenotype> phenotypes = currentPopulation.getPopulation()
                .stream()
                .collect(Collectors.toList());

        Collections.sort(phenotypes, (o1, o2) -> (Double.compare(o1.getFitnessValue(routes), o2.getFitnessValue(routes))));

        strongestIndividual = phenotypes.get(0);

        return strongestIndividual;
    }

    @Override
    public Phenotype[] select(Selection selection) {

        if (currentPopulation.getPopulation().size() < 2) {
            try {
                throw new SelectionForCrossoverException();
            } catch (SelectionForCrossoverException e) {
                System.err.println("Population size < 2 !");
            }
        }

        Phenotype[] pairForCrossover = new Phenotype[2];

        pairForCrossover[0] = selection.select(currentPopulation, routes);

        do {
            pairForCrossover[1] = selection.select(currentPopulation, routes);

        } while (Arrays.equals(pairForCrossover[0].getPhenotype(), pairForCrossover[1].getPhenotype()));


        return pairForCrossover;
    }

    @Override
    public Population crossover(Crossing crossing) {
        Set<Phenotype> newGeneration = new HashSet<>();

        int sizeOfPopulation = currentPopulation.getPopulation().size();
        boolean isOddSize;
        if (sizeOfPopulation % 2 == 0) {
            isOddSize = false;
        } else {
            isOddSize = true;
        }

        for (int i = 0; i < sizeOfPopulation / 2 + 1; i++) {

            Phenotype[] pairForCrossover = select(new ProportionalSelection());

            Phenotype[] successors = null;


            if (i == sizeOfPopulation / 2) {

                if (isOddSize) {
                    try {
                        successors = crossing.crossover(pairForCrossover[0], pairForCrossover[1]);
                    } catch (IllegalLengthOfPhenotypeException e) {
                        System.err.println("Bad length of phenotypes");
                    }

                    if (successors[0].getFitnessValue(routes) > successors[1].getFitnessValue(routes)) {

                        newGeneration.add(successors[1]);
                    } else {
                        newGeneration.add(successors[0]);
                    }

                } else {
                    break;
                }

            } else {
                try {
                    successors = crossing.crossover(pairForCrossover[0], pairForCrossover[1]);
                } catch (IllegalLengthOfPhenotypeException e) {
                    System.err.println("Bad length of phenotypes");
                }

                newGeneration.add(successors[0]);
                newGeneration.add(successors[1]);

            }


        }

        currentPopulation.setPopulation(newGeneration);

        return currentPopulation;
    }

    @Override
    public void mutate(Mutation mutation, double mutationProbability) {

        double randomValue = Math.random();

        currentPopulation.getPopulation()
                .stream()
                .filter(phenotype -> randomValue <= mutationProbability)
                .forEach(mutation::mutate);


    }

    public Phenotype getResult(){
        return result;
    }

    public static void main(String[] args) throws Exception {


        // 1 - startCity , 2 - sizeOfPopulation, 3 - mutationProb , 4 - iterations

        new CanonicalGeneticAlgorithm(1, 100, 0.25, 10000);

//        System.out.println(Arrays.toString(GA.select(new ProportionalSelection())));


//        GA.checkElementOfPopulation(GA.currentPopulation);
//
//        GA.crossover(new OrderedCrossing());
//
//        GA.checkElementOfPopulation(GA.currentPopulation);
//
    }

}
