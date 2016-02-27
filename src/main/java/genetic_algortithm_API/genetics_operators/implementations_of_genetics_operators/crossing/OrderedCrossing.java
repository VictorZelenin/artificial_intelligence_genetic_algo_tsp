package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.util.*;

/**
 * Created by User on 26.02.2016.
 */

public class OrderedCrossing implements Crossing {


    @Override
    public Phenotype[] crossover(Phenotype mother, Phenotype father) {



        return null;
    }


    // testing unit
    public static void main(String[] args) throws Exception {

        int[] motherGenes = {1, 2, 3, 4};
        int[] fatherGenes = {1, 4, 2, 3};
        Phenotype mother = new Phenotype(motherGenes);
        Phenotype father = new Phenotype(fatherGenes);

        OrderedCrossing crossing = new OrderedCrossing();

        System.out.println(Arrays.toString(crossing.crossover(mother, father)));

    }
}
