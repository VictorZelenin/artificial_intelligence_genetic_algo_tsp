package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 07.03.2016.
 */
@SuppressWarnings("Duplicates")
public class SinglePointCrossing implements Crossing {
    @Override
    public Phenotype crossover(Phenotype mother, Phenotype father) throws IllegalLengthOfPhenotypeException, InvalidGeneException {
        int phenotypeLength;
        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new IllegalLengthOfPhenotypeException();
        } else {
            phenotypeLength = mother.getPhenotype().length;
        }

        int[] sonGenes = new int[phenotypeLength];
        int crossingPoint = (int) (Math.random() * (phenotypeLength - 1));
        ArrayList<Integer> usedGenes = new ArrayList<>();

        int i = 0;
        while (i != phenotypeLength) {
            if (i <= crossingPoint) {
                sonGenes[i] = mother.getPhenotype()[i];
                usedGenes.add(mother.getPhenotype()[i]);
                i++;
            } else {
                int j = crossingPoint + 1;
                int k = crossingPoint + 1;
                while (j != phenotypeLength) {
                    if (!usedGenes.contains(father.getPhenotype()[j])) {
                        sonGenes[i] = father.getPhenotype()[j];
                        usedGenes.add(father.getPhenotype()[j]);
                        i++;
                        j++;
                    } else {
                        j++;
                        continue;
                    }
                }


                while (k != phenotypeLength || i != phenotypeLength) {
                    if (!usedGenes.contains(mother.getPhenotype()[k])) {
                        sonGenes[i] = mother.getPhenotype()[k];
                        usedGenes.add(mother.getPhenotype()[k]);
                        i++;
                        k++;
                    } else {
                        k++;
                        continue;
                    }
                }
            }
        }

        return new Phenotype(sonGenes);
    }
}
