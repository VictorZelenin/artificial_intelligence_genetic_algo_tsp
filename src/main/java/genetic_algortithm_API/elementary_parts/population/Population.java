package genetic_algortithm_API.elementary_parts.population;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.*;


/**
 * Created by User on 25.02.2016.
 */

public class Population implements Cloneable {

    // нету одинаковых особей в популяции
    private Set<Phenotype> population;


    public Population(int sizeOfPopulation, Routes routes) {

        population = generateFirstPopulation(sizeOfPopulation, routes);

    }


    private Set<Phenotype> generateFirstPopulation(int size, Routes routes) {

        int[] possibleGenes = routes.getCitiesID();
        Set<Phenotype> firstPopulation = new HashSet<>(size);

        for (int j = 0; j < size; j++) {

            firstPopulation.add(new Phenotype(shuffleArray(possibleGenes)));

        }


//        System.out.println(firstPopulation);

        return firstPopulation;
    }

    private int[] shuffleArray(int[] array) {


        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }

        return Arrays.copyOfRange(array, 0, array.length);
    }


    public void setPopulation(Set<Phenotype> population) {
        this.population = population;
    }

    public Set<Phenotype> getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "Population(phenotypes[])" + population;
    }


    // testing unit
    public static void main(String[] args) throws InvalidGeneException {

        Population firstPopulation = null;
        Routes routes = new Routes(100, new CoordinatesWeightFunction());


        firstPopulation = new Population(3, routes);


//        for (Phenotype phenotype : firstPopulation.population) {
//            System.out.println(phenotype + " " + phenotype.getFitnessValue(routes));
//        }

    }


}
