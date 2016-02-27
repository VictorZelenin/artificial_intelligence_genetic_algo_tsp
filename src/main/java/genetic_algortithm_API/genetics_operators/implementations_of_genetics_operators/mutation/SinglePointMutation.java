package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;

import java.util.Arrays;

/**
 * Created by User on 27.02.2016.
 */
public class SinglePointMutation implements Mutation {


    @Override
    public void mutate(Phenotype individual) {
        int firstRandomIndex = (int) (Math.random() * individual.getPhenotype().length);
        int secondRandomIndex;

        do {
            secondRandomIndex = (int) (Math.random() * individual.getPhenotype().length);
        }
        while (secondRandomIndex == firstRandomIndex);

        System.out.println("first gene index: " + firstRandomIndex);
        System.out.println("second gene index: " + secondRandomIndex);

        swap(individual, firstRandomIndex, secondRandomIndex);

    }

    private void swap(Phenotype individual, int firstRandomIndex, int secondRandomIndex) {
        int temp = individual.getPhenotype()[firstRandomIndex];
        individual.getPhenotype()[firstRandomIndex] = individual.getPhenotype()[secondRandomIndex];
        individual.getPhenotype()[secondRandomIndex] = temp;

    }


    // testing unit
    public static void main(String[] args) {
        SinglePointMutation mutation = new SinglePointMutation();

        int[] genes = {1, 2, 3, 4};
        System.out.println(Arrays.toString(genes));
        Phenotype phenotype = new Phenotype(genes);

        mutation.mutate(phenotype);
        System.out.println(phenotype);
    }

}
