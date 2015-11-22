package br.edu.univas.ga.framework;

import java.util.ArrayList;

public abstract class Individual implements Comparable<Individual> {

    protected ArrayList<Chromosome> chromosomes;
    private float value = 0; // value of individual.

    /**
     * Method responsible by calculate value of individual.
     */
    public abstract void calculateValue();

    public Individual() {
    }

    public Individual(ArrayList<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public ArrayList<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public void setChromosomes(ArrayList<Chromosome> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("{\"chromossomeList\": [");

        for (int i = 0; i < chromosomes.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            
            builder.append(chromosomes.get(i).toString());
        }

        builder.append("], \"value\": ");
        builder.append(String.format("%s}", value));

        return builder.toString();
    }

}
