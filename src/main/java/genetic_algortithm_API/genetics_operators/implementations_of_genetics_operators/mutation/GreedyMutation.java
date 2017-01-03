package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.mutation;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.InvalidGeneException;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Mutation;
import genetic_algortithm_API.routes.Routes;
import genetic_algortithm_API.routes.routes_weight_func_impl.CoordinatesWeightFunction;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by User on 07.03.2016.
 */
public class GreedyMutation implements Mutation {
    @Override
    public void mutate(Phenotype individual, Routes routes) {
        int phenotypeLength = individual.getPhenotype().length;
        int firstRandomIndex, secondRandomIndex;
        double[][] matrix = routes.getRoutesMatrix();

        do {
            firstRandomIndex = (int) (Math.random() * (phenotypeLength - 1));
            secondRandomIndex = (int) (Math.random() * (phenotypeLength - 1));
        }
        while (firstRandomIndex == secondRandomIndex || Math.abs(firstRandomIndex - secondRandomIndex) < 2);

        if (firstRandomIndex > secondRandomIndex) {

            int temp = secondRandomIndex;
            secondRandomIndex = firstRandomIndex;
            firstRandomIndex = temp;

        }

        Phenotype copyOfIndividual;
        copyOfIndividual = new Phenotype(Arrays.copyOfRange(individual.getPhenotype(), 0, individual.getPhenotype().length));
        for (int i = firstRandomIndex + 1; i <= secondRandomIndex; i++) {
            swap(individual.getPhenotype(), i, chooseNextCity(copyOfIndividual, i - 1, secondRandomIndex, matrix));
        }
    }

    private void swap(int[] phenotype, int a, int b) {
        int temp = phenotype[a];
        phenotype[a] = phenotype[b];
        phenotype[b] = temp;
    }

    private int chooseNextCity(Phenotype phenotype, int firstIndex, int secondIndex, double[][] matrix) {
        Map<Integer, Double> map = new HashMap<>();

        for (int i = firstIndex + 1; i <= secondIndex; i++) {
            map.put(phenotype.getPhenotype()[i], matrix[firstIndex][phenotype.getPhenotype()[i] - 1]);
        }

        final Map<Integer, Double> sortedMap =
                new TreeMap<>((Comparator<Integer>) (lhs, rhs) -> Double.compare(map.get(lhs), map.get(rhs)));
        sortedMap.putAll(map);

        return indexOf(phenotype.getPhenotype(), sortedMap.keySet()
                .stream()
                .findFirst()
                .get());
    }

    private int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
