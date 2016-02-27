package genetic_algortithm_API.elementary_parts.population;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.util.*;


/**
 * Created by User on 25.02.2016.
 */

public class Population implements Cloneable {

    private Set<Phenotype> population;


    public Population(int sizeOfPopulation, Routes routes, int startID) throws Exception {

        population = generateFirstPopulation(sizeOfPopulation, routes, startID);

    }


    private Set<Phenotype> generateFirstPopulation(int size, Routes routes, int startID) throws Exception {

        validate(routes, startID);

        int[] possibleGenes = routes.getCitiesID();

        Set<Phenotype> firstPopulation = new HashSet<>();


        for (int i = 0; i < size; i++) {
            int[] genes = new int[possibleGenes.length];
            Phenotype phenotype;

            int j = 0;
            List<Integer> usedGenes = new ArrayList<>();
            while (j != possibleGenes.length) {


                int gene;
                int randomIndex = (int) (Math.random() * possibleGenes.length);

                if (j == 0) {
                    gene = startID;

                } else {
                    gene = possibleGenes[randomIndex];
                }

                if (!usedGenes.contains(gene)) {
                    genes[j] = gene;
                    usedGenes.add(gene);
                    j++;

                }


            }

            phenotype = new Phenotype(genes);
            firstPopulation.add(phenotype);

        }


        return firstPopulation;
    }

    private void validate(Routes routes, int startID) throws Exception {

        List<Integer> items = new ArrayList<>();

        for (int i = 0; i < routes.getCitiesID().length; i++) {
            items.add(routes.getCitiesID()[i]);
        }

        if (!items.contains(startID)) {
            throw new Exception();
        }


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

    @Override
    public Population clone() throws CloneNotSupportedException {
        return (Population) super.clone();
    }


    // testing unit
    public static void main(String[] args) {

        Population firstPopulation = null;
        Routes routes = new Routes(4, new CoordinatesWeightFunction());

        try {
            firstPopulation = new Population(4, routes, 1);
        } catch (Exception e) {
            System.err.println("Invalid start city");
        }

        for (Phenotype phenotype : firstPopulation.population) {
            System.out.println(phenotype + " " + phenotype.getFitnessValue(routes));
        }

    }


}
