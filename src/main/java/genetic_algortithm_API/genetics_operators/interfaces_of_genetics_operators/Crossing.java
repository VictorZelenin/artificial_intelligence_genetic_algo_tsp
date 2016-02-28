package genetic_algortithm_API.genetics_operators.interfaces_of_genetics_operators;

import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.exceptions.IllegalLengthOfPhenotypeException;

/**
 * Created by User on 26.02.2016.
 */

@FunctionalInterface
public interface Crossing {

    Phenotype[] crossover(Phenotype mother, Phenotype father) throws IllegalLengthOfPhenotypeException;

}
