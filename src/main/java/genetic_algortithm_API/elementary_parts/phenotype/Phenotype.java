package genetic_algortithm_API.elementary_parts.phenotype;

import genetic_algortithm_API.elementary_parts.gene.Gene;

import java.io.Serializable;

/**
 * Created by User on 24.02.2016.
 */
public class Phenotype implements Serializable {

    private Gene[] genes;

    private boolean status;

    public Phenotype(Gene[] genes) {
        this.genes = genes;
    }


    public double getFitness() {
        return 0;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAlive(){
        return status;
    }

}
