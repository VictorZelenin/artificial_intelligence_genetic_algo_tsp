package genetic_algortithm_API.elementary_parts.phenotype;

import genetic_algortithm_API.elementary_parts.gene.Gene;
import genetic_algortithm_API.routes.Routes;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by User on 24.02.2016.
 */
public class Phenotype implements Serializable {

    private Gene[] phenotype;

    // нормувати єнумом , щоб збільшити точність
    private boolean status;

    public Phenotype(Gene[] genes) {
        this.phenotype = genes;
    }


    public Phenotype(String fileName) {
        Routes routes = new Routes(fileName);
        phenotype = routes.getCities();
    }

    public Phenotype(int quantityOfGenes) {
        Routes routes = new Routes(quantityOfGenes);
        phenotype = routes.getCities();
    }

    public Gene[] getPhenotype() {
        return phenotype;
    }


    public double getFitness() {
        return 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAlive() {
        return status;
    }

    // test unit
    public static void main(String[] args) {

        Phenotype phenotype = new Phenotype(15);

        System.out.println(Arrays.toString(phenotype.getPhenotype()));


    }

}
