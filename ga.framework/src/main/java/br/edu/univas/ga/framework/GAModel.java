package br.edu.univas.ga.framework;

import java.util.ArrayList;

public abstract class GAModel {

    public static enum SelectionType {

        ROULETTE, CLASSIFICATION
    };

    public static enum CrossType {

        BINARY, PERMUTATION, UNIFORM, ARITMETIC
    };

    public static enum MutationType {

        PERMUTATION, BINARY, NUMERICAL
    };

    protected ArrayList<Individual> population;

    private int populationSize = 25;
    private int generationQuantity = 100;
    private boolean elitism = true;
    private float foreignIndividualRate = 0;
    private float mutationRate = 0;
    private float mutationQuantity = 1;
    private SelectionType selectionType = SelectionType.CLASSIFICATION;
    private CrossType crossType = CrossType.BINARY;
    private MutationType mutationType = MutationType.PERMUTATION;

    public abstract void createInitialPopulation();

    public abstract Individual createIndividual();

    public abstract Individual createIndividual(ArrayList<Chromosome> chromosomes);

    public GAModel() {
        population = new ArrayList<>();
    }

    public ArrayList<Individual> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Individual> popupation) {
        this.population = popupation;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getGenerationQuantity() {
        return generationQuantity;
    }

    public void setGenerationQuantity(int generationQuantity) {
        this.generationQuantity = generationQuantity;
    }

    public boolean isElitism() {
        return elitism;
    }

    public void setElitism(boolean elitism) {
        this.elitism = elitism;
    }

    public SelectionType getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(SelectionType selectionType) {
        this.selectionType = selectionType;
    }

    public CrossType getCrossType() {
        return crossType;
    }

    public void setCrossType(CrossType crossType) {
        this.crossType = crossType;
    }

    public float getForeignIndividualRate() {
        return foreignIndividualRate;
    }

    public void setForeignIndividualRate(float foreignIndividualRate) {
        this.foreignIndividualRate = foreignIndividualRate;
    }

    public MutationType getMutationType() {
        return mutationType;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public float getMutationQuantity() {
        return mutationQuantity;
    }

    public void setMutationQuantity(float mutationQuantity) {
        this.mutationQuantity = mutationQuantity;
    }

}
