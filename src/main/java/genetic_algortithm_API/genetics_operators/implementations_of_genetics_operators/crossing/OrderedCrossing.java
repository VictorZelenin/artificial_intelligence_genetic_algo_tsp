package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.sql.Array;
import java.util.*;

/**
 * Created by User on 26.02.2016.
 */

public class OrderedCrossing implements Crossing {


    @Override
    public Phenotype[] crossover(Phenotype mother, Phenotype father) throws IllegalLengthOfPhenotypeException {

        Phenotype newMother = new Phenotype(Arrays.copyOfRange(mother.getPhenotype(), 1, mother.getPhenotype().length));
        Phenotype newFather = new Phenotype(Arrays.copyOfRange(father.getPhenotype(), 1, father.getPhenotype().length));


        int phenotypeLength;

        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new IllegalLengthOfPhenotypeException();
        } else {
            phenotypeLength = father.getPhenotype().length - 1;
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

            fatherGenes.add(newFather.getPhenotype()[index]);
            motherGenes.add(newMother.getPhenotype()[index]);

            index++;

        }


        for (int i = firstCrossingIndex; i < secondCrossingIndex; i++) {

            sonGenes[i] = newMother.getPhenotype()[i];
            fatherGenes.remove(fatherGenes.get(fatherGenes.indexOf(newMother.getPhenotype()[i])));

            daughterGenes[i] = newFather.getPhenotype()[i];
            motherGenes.remove(motherGenes.get(motherGenes.indexOf(newFather.getPhenotype()[i])));


        }
//        System.out.println(fatherGenes);
//        System.out.println(motherGenes);

        int j = secondCrossingIndex;
        int k = 0;
        while (k != fatherGenes.size()) {

            if (j == phenotypeLength + 1) {
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


        sonGenes[0] = 1;
        daughterGenes[0] = 1;


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

        OrderedCrossing crossing = new OrderedCrossing();

        System.out.println(Arrays.toString(crossing.crossover(mother, father)));

    }
}
