package genetic_algortithm_API.elementary_parts.population;

import genetic_algortithm_API.elementary_parts.city.City;
import genetic_algortithm_API.elementary_parts.phenotype.Phenotype;
import genetic_algortithm_API.routes.Routes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.02.2016.
 */
public class Population {

    private Phenotype[] population;
    private Routes routes;


    public Population(int sizeOfPopulation, Routes routes, int startID) {

        this.routes = routes;

        population = generateFirstPopulation(sizeOfPopulation, routes, startID);

    }

    private Phenotype[] generateFirstPopulation(int size, Routes routes, int startID) {

        Phenotype[] firstPopulation = new Phenotype[size];


        for (int i = 0; i < size; i++) {

            for (int j = 0; j < routes.getCitiesID().length; j++) {
                List<Integer> usedIDs = new ArrayList<>();
                usedIDs.add(startID);
//                if ()


            }


        }

        //genes
        routes.getCitiesID();


        return firstPopulation;
    }


}
