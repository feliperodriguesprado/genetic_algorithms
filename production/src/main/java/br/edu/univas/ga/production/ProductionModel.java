package br.edu.univas.ga.production;

import br.edu.univas.ga.framework.Chromosome;
import br.edu.univas.ga.framework.GAModel;
import br.edu.univas.ga.framework.Individual;
import java.util.ArrayList;

public class ProductionModel extends GAModel {

    @Override
    public void createInitialPopulation() {
        for (int i = 0; i < getPopulationSize(); i++) {
            population.add(new ProductionIndividual());
        }
    }

    @Override
    public Individual createIndividual() {
        return new ProductionIndividual();
    }

    @Override
    public Individual createIndividual(ArrayList<Chromosome> chromosomes) {
        return new ProductionIndividual(chromosomes);
    }

}
