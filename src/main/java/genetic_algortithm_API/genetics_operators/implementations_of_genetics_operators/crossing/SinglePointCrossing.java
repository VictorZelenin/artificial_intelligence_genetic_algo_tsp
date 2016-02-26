package genetic_algortithm_API.genetics_operators.implementations_of_genetics_operators.crossing;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators.Crossing;

/**
 * Created by User on 26.02.2016.
 */
public class SinglePointCrossing implements Crossing {


    @Override
    public Phenotype crossing(Phenotype mother, Phenotype father) throws Exception {

        Phenotype son, doughter;
        int[] sonGenes = new int[mother.getPhenotype().length];
        int[] doughterGenes = new int[father.getPhenotype().length];

        int crossingPoint = (int) (Math.random() * father.getPhenotype().length);

        if (mother.getPhenotype().length != father.getPhenotype().length) {
            throw new Exception();
        }



        return null;
    }
}
