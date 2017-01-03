package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.util.*;

/**
 * Created by User on 26.02.2016.
 */

public class TwoPointsCrossing implements Crossing {
    @Override
    public Phenotype crossover(Phenotype mother, Phenotype father) throws IllegalLengthOfPhenotypeException, InvalidGeneException {
        int phenotypeLength;

        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new IllegalLengthOfPhenotypeException();
        } else {
            phenotypeLength = father.getPhenotype().length;
        }

        int[] sonGenes = new int[father.getPhenotype().length];

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
            motherGenes.remove(motherGenes.get(motherGenes.indexOf(father.getPhenotype()[i])));
        }
        int j = secondCrossingIndex;
        int k = 0;
        while (k != fatherGenes.size()) {
            if (j == phenotypeLength) {
                j = j % phenotypeLength;
                sonGenes[j] = fatherGenes.get(k);
                j++;
                k++;
            } else {
                sonGenes[j] = fatherGenes.get(k);
                k++;
                j++;
            }
        }

        return new Phenotype(sonGenes);
    }
}
