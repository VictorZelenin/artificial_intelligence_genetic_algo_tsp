package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

import java.util.*;

/**
 * Created by User on 26.02.2016.
 */
public class SinglePointCrossing implements Crossing {


    @Override
    public Phenotype[] crossover(Phenotype mother, Phenotype father) throws Exception {

        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new Exception();
        }


        Phenotype son, daughter;

        Phenotype[] family = new Phenotype[4];

        int[] sonGenes = new int[mother.getPhenotype().length];
        int[] daughterGenes = new int[father.getPhenotype().length];


        int crossingPoint = (int) (Math.random() * father.getPhenotype().length);
//        int crossingPoint = 1;

        System.out.println("Crossing Point: " + crossingPoint);
        ArrayList<Integer> geneMapping = new ArrayList<>();


        // парні номери в лісту -- батьківські гени , непарні номери -- материнські
        for (int i = 0; i <= crossingPoint; i++) {
            geneMapping.add(father.getPhenotype()[i]);
            geneMapping.add(mother.getPhenotype()[i]);
        }

        for (int i = 0; i < father.getPhenotype().length; i++) {

            int index = geneMapping.indexOf(father.getPhenotype()[i]);

            if (geneMapping.contains(father.getPhenotype()[i])) {

                if (index % 2 == 0) {
                    sonGenes[i] = geneMapping.get(index + 1);
                    daughterGenes[i] = geneMapping.get(index);
                } else {
                    sonGenes[i] = geneMapping.get(index - 1);
                    daughterGenes[i] = geneMapping.get(index);
                }

            } else {
                sonGenes[i] = father.getPhenotype()[i];
                daughterGenes[i] = mother.getPhenotype()[i];
            }
        }

        son = new Phenotype(sonGenes);
        daughter = new Phenotype(daughterGenes);


        family[0] = father;
        family[1] = mother;
        family[2] = son;
        family[3] = daughter;

        return family;
    }


    // testing unit
    public static void main(String[] args) throws Exception {

        int[] motherGenes = {3, 5, 2, 4, 1};
        int[] fatherGenes = {1, 2, 5, 4, 3};
        Phenotype mother = new Phenotype(motherGenes);
        Phenotype father = new Phenotype(fatherGenes);

        SinglePointCrossing crossing = new SinglePointCrossing();

        System.out.println(Arrays.toString(crossing.crossover(mother, father)));

    }
}
