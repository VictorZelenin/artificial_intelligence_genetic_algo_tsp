package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.routes.Routes;

import java.util.Arrays;

/**
 * Created by User on 27.02.2016.
 */

// it worked correct
public class SinglePointMutation implements Mutation {


    @Override
    public void mutate(Phenotype individual, Routes routes) {


        int firstRandomIndex = (int) ((Math.random() * (individual.getPhenotype().length - 1) + 1));
        int secondRandomIndex;

        do {
            secondRandomIndex = (int) ((Math.random() * individual.getPhenotype().length - 1));
        }
        while (secondRandomIndex == firstRandomIndex);

        swap(individual, firstRandomIndex, secondRandomIndex);

    }

    private void swap(Phenotype individual, int firstRandomIndex, int secondRandomIndex) {
        int temp = individual.getPhenotype()[firstRandomIndex];
        individual.getPhenotype()[firstRandomIndex] = individual.getPhenotype()[secondRandomIndex];
        individual.getPhenotype()[secondRandomIndex] = temp;

    }


    // testing unit
    public static void main(String[] args) throws InvalidGeneException {
        SinglePointMutation mutation = new SinglePointMutation();

        int[] genes = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(genes));
        Phenotype phenotype = new Phenotype(genes);

//        mutation.mutate(phenotype);
        System.out.println(phenotype);
    }

}
