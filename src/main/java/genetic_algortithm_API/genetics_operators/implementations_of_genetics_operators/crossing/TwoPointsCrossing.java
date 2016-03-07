package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.util.*;

/**
 * Created by User on 26.02.2016.
 */

public class TwoPointsCrossing implements Crossing {


    @Override
    public Phenotype[] crossover(Phenotype mother, Phenotype father) throws IllegalLengthOfPhenotypeException {


        int phenotypeLength;

        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new IllegalLengthOfPhenotypeException();
        } else {
            phenotypeLength = father.getPhenotype().length;
        }

        Phenotype[] successors = new Phenotype[2];

        int[] sonGenes = new int[father.getPhenotype().length];
        int[] daughterGenes = new int[mother.getPhenotype().length];

        List<Integer> fatherGenes = new ArrayList<>();
        List<Integer> motherGenes = new ArrayList<>();


        int firstCrossingIndex;
        int secondCrossingIndex;

        do {
            firstCrossingIndex = (int) ((Math.random() * (phenotypeLength - 1)) + 1);

            secondCrossingIndex = (int) ((Math.random() * (phenotypeLength - 1)) + 1);

        } while (secondCrossingIndex == firstCrossingIndex);

        if (firstCrossingIndex > secondCrossingIndex) {

            int temp = secondCrossingIndex;
            secondCrossingIndex = firstCrossingIndex;
            firstCrossingIndex = temp;

        }

        int index = secondCrossingIndex;
        while (fatherGenes.size() != phenotypeLength &&
                motherGenes.size() != phenotypeLength) {

            if (index == phenotypeLength) {

                index = index % phenotypeLength;

            }

            fatherGenes.add(father.getPhenotype()[index]);
            motherGenes.add(mother.getPhenotype()[index]);

            index++;

        }


        for (int i = firstCrossingIndex; i < secondCrossingIndex; i++) {

            sonGenes[i] = mother.getPhenotype()[i];
            fatherGenes.remove(fatherGenes.get(fatherGenes.indexOf(mother.getPhenotype()[i])));

            daughterGenes[i] = father.getPhenotype()[i];
            motherGenes.remove(motherGenes.get(motherGenes.indexOf(father.getPhenotype()[i])));


        }
//        System.out.println(fatherGenes);
//        System.out.println(motherGenes);

        int j = secondCrossingIndex;
        int k = 0;
        while (k != fatherGenes.size()) {

            if (j == phenotypeLength) {
                j = j % phenotypeLength;

                sonGenes[j] = fatherGenes.get(k);
                daughterGenes[j] = motherGenes.get(k);

                j++;
                k++;
            } else {

                sonGenes[j] = fatherGenes.get(k);
                daughterGenes[j] = motherGenes.get(k);

                k++;
                j++;

            }

        }


        successors[0] = new Phenotype(sonGenes);
        successors[1] = new Phenotype(daughterGenes);
        return successors;
    }


    // testing unit
    public static void main(String[] args) throws Exception {

        int[] motherGenes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] fatherGenes = {1, 12, 5, 3, 11, 7, 6, 8, 10, 9, 2, 4};

        Phenotype mother = new Phenotype(motherGenes);
        Phenotype father = new Phenotype(fatherGenes);

        TwoPointsCrossing crossing = new TwoPointsCrossing();

        System.out.println(Arrays.toString(crossing.crossover(mother, father)));

    }
}
