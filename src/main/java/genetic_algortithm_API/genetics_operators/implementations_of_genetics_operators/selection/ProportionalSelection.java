package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.selection;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.elementary_parts.population.Population;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Selection;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.*;

/**
 * Created by User on 27.02.2016.
 */
public class ProportionalSelection implements Selection {


    @Override
    public Phenotype select(Population currentPopulation, Routes routes) {

        Map<Phenotype, Double> probabilityMap = new HashMap<>();

        Phenotype phenotype;

        double averageFitnessValue = averageFitness(currentPopulation, routes);

        for (Phenotype individual : currentPopulation.getPopulation()) {

            double fitnessValue = individual.getFitnessValue(routes) / averageFitnessValue;
            double fitnessValueIntegerPart = Math.floor(individual.getFitnessValue(routes) / averageFitnessValue);
            double probabilityValue = Math.random();
            double fitnessValueDivPart = fitnessValue - fitnessValueIntegerPart;

            if (fitnessValueDivPart >= probabilityValue) {
                fitnessValueIntegerPart++;
            }


            probabilityMap.put(individual, fitnessValueIntegerPart);


        }

        phenotype = choosePhenotype(probabilityMap);



        return phenotype;
    }

    private Phenotype choosePhenotype(Map<Phenotype, Double> probabilityMap) {


        List<Phenotype> roulette = new ArrayList<>();

        for (Phenotype phenotype : probabilityMap.keySet()) {
            for (int i = 0; i < probabilityMap.get(phenotype); i++) {
                roulette.add(phenotype);
            }
        }

        int randomIndex = (int) (Math.random() * roulette.size());


        return roulette.get(randomIndex);
    }


    private double averageFitness(Population population, Routes routes) {

        double averageFitnessValue = 0;

        for (Phenotype phenotype : population.getPopulation()) {
            averageFitnessValue += phenotype.getFitnessValue(routes) / population.getPopulation().size();
        }


        return averageFitnessValue;
    }


    // testing unit
    public static void main(String[] args) throws Exception {

        ProportionalSelection selection = new ProportionalSelection();
        Routes routes = new Routes("input.txt", new CoordinatesWeightFunction());
        Population population = new Population(5, routes, 1);
        selection.select(population, routes);


    }

}
