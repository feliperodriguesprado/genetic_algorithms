package br.edu.univas.ga.framework;

public abstract class Chromosome {

    public abstract void doMutation();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    @Override
    public abstract Chromosome clone();

}
