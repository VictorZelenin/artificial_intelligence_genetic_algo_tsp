package genetic_algortithm_API.elementary_parts.phenotype;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.io.File;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Arrays;

/**
 * @author Zelenin Victor
 */
public class Phenotype implements Serializable {

    private final int[] phenotype;
//    private Routes routes;

    // нормувати єнумом , щоб збільшити точність
    private boolean status;


    // initialize in different order
    public Phenotype(int[] phenotype) {
        this.phenotype = phenotype;

    }

    private void validate(int[] genes, Routes routes) throws InvalidGeneException {

        int[] genesCopy = genes.clone();

        if (genes.length != routes.getCitiesID().length) {
            throw new InvalidGeneException();
        }

        Arrays.sort(genesCopy);
        Arrays.sort(routes.getCitiesID());

        if (!Arrays.equals(genesCopy, routes.getCitiesID())) {
            throw new InvalidGeneException();
        }


    }


    public int[] getPhenotype() {
        return phenotype;
    }


    // 1 -> 2 -> 3 -> 1 = value
    public double getFitnessValue(Routes routes) {

        double fitnessFunctionValue = 0;

        try {
            validate(this.phenotype, routes);
        } catch (Exception e) {
            System.err.println("Current gene doesn't exist");
        }

        for (int i = 0; i < phenotype.length; i++) {

            int m = phenotype[i] - 1;
            int n = phenotype[(i + 1) % phenotype.length] - 1;


            fitnessFunctionValue += routes.getRoutesMatrix()[m][n];

        }


        return fitnessFunctionValue;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAlive() {
        return status;
    }

    @Override
    public String toString() {
        return "Phenotype: genes =  " + Arrays.toString(phenotype);
    }


    // test unit
    public static void main(String[] args) {

        int[] genes = {1, 3, 4, 2};
        Routes routes = new Routes("input.txt", new CoordinatesWeightFunction());

        Phenotype phenotype = new Phenotype(genes);



        Routes.printMatrix(routes.getRoutesMatrix());
        System.out.println(phenotype.getFitnessValue(routes));
    }

}
