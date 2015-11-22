package br.edu.univas.ga.framework;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GAController {

    private final GAModel model;

    public GAController(GAModel model) {
        this.model = model;
    }

    public void execute() {

        Individual lastBest = null;
        HashMap<Integer, ArrayList<Individual>> generationList = new HashMap<>();
        ArrayList<Individual> populationList;

        model.createInitialPopulation();

        for (int i = 0;; i++) {

            ArrayList<Individual> population = model.getPopulation();
            ArrayList<Individual> newGeneration = new ArrayList<>();

            classify(population);

//            populationList = new ArrayList<>();
//
//            for (Individual individual : population) {
//                populationList.add(individual);
//            }
//
//            generationList.put(i + 1, populationList);
            if (lastBest == null || lastBest != population.get(0)) {
                lastBest = population.get(0);

                populationList = new ArrayList<>();
                populationList.add(population.get(0));
                generationList.put(i + 1, populationList);
                //System.out.println(String.format("%6d", (i + 1)) + " - " + population.get(0).toString());
            }

            // Verifica o final da execução
            if (i == model.getGenerationQuantity()) {
                break;
            }

            if (model.isElitism()) {
                doElitism(newGeneration);
            }

            // Acho o número par de estrangeiros
            int foreignQuantity = Math.round(
                    model.getPopulationSize() * model.getForeignIndividualRate());
            foreignQuantity = foreignQuantity % 2 == 0 ? foreignQuantity : foreignQuantity + 1;

            // Criando os estrangeiros
            for (int j = 0; j < foreignQuantity; j++) {
                newGeneration.add(model.createIndividual());
            }

            while (newGeneration.size() < model.getPopulationSize()) {
                // Seleção
                Individual individual1 = doSelection();
                Individual individual2;

                do {
                    individual2 = doSelection();
                } while (individual1 == individual2);

                // Cruzamento
                IndividualPair pair = doCrossing(individual1, individual2);

                // Mutação
                doMutation(pair.getIndividual1());
                doMutation(pair.getIndividual2());

                newGeneration.add(pair.getIndividual1());
                newGeneration.add(pair.getIndividual2());
            }

            model.setPopulation(newGeneration);

        }

        saveResultToJSON(generationList);

    }

    private void doMutation(Individual individual) {
        if (Math.random() < model.getMutationRate()) {
            for (int i = 0; i < model.getMutationQuantity(); i++) {
                switch (model.getMutationType()) {
                    case BINARY:
                        doBinaryMutation(individual);
                        break;
                    case NUMERICAL:
                        break;
                    case PERMUTATION:
                        doPermutationMutation(individual);
                        break;
                }
            }
        }
    }

    private void doBinaryMutation(Individual individual) {
        ArrayList<Chromosome> chromosomes = individual.getChromosomes();

        int position = (int) (Math.random() * chromosomes.size());

        chromosomes.get(position).doMutation();
    }

    private void doPermutationMutation(Individual individual) {
        ArrayList<Chromosome> chromosomes = individual.getChromosomes();

        int firstPosition = (int) (Math.random() * chromosomes.size());
        int secondPosition = 0;

        do {
            secondPosition = (int) (Math.random() * chromosomes.size());
        } while (firstPosition == secondPosition);

        Chromosome firstChromosome = chromosomes.get(firstPosition);
        Chromosome secondChromosome = chromosomes.get(secondPosition);

        chromosomes.set(firstPosition, secondChromosome);
        chromosomes.set(secondPosition, firstChromosome);
    }

    private IndividualPair doCrossing(Individual individual1, Individual individual2) {
        switch (model.getCrossType()) {
            case ARITMETIC:
                break;
            case BINARY:
                return doBinaryCrossing(individual1, individual2);
            case PERMUTATION:
                return doPermutationCrossing(individual1, individual2);
            case UNIFORM:
                break;
        }

        return null;
    }

    private IndividualPair doBinaryCrossing(Individual individual1, Individual individual2) {
        int crossPoint = (int) (Math.random() * (individual1.getChromosomes().size() - 1)) + 1;

        ArrayList<Chromosome> chromosomes1 = new ArrayList<>();
        ArrayList<Chromosome> chromosomes2 = new ArrayList<>();

        for (int i = 0; i < crossPoint; i++) {
            chromosomes1.add(individual1.getChromosomes().get(i).clone());
            chromosomes2.add(individual2.getChromosomes().get(i).clone());
        }

        for (int i = crossPoint; i < individual1.getChromosomes().size(); i++) {
            chromosomes1.add(individual2.getChromosomes().get(i).clone());
            chromosomes2.add(individual1.getChromosomes().get(i).clone());
        }

        Individual newIndividual1 = model.createIndividual(chromosomes1);
        Individual newIndividual2 = model.createIndividual(chromosomes2);

        return new IndividualPair(newIndividual1, newIndividual2);
    }

    private IndividualPair doPermutationCrossing(Individual individual1, Individual individual2) {
        int crossPoint = (int) (Math.random() * (individual1.getChromosomes().size() - 1)) + 1;

        ArrayList<Chromosome> chromosomes1 = new ArrayList<>();
        ArrayList<Chromosome> chromosomes2 = new ArrayList<>();

        if (crossPoint >= chromosomes1.size()) {
            crossPoint = chromosomes1.size() - 1;
        }

        if (crossPoint >= chromosomes2.size()) {
            crossPoint = chromosomes1.size() - 1;
        }

        for (int i = 0; i < crossPoint; i++) {
            chromosomes1.add(individual1.getChromosomes().get(i));
            chromosomes2.add(individual2.getChromosomes().get(i));
        }

        for (int i = 0; i < individual1.getChromosomes().size(); i++) {
            if (!chromosomes1.contains(individual2.getChromosomes().get(i))) {
                chromosomes1.add(individual2.getChromosomes().get(i));
            }

            if (!chromosomes2.contains(individual1.getChromosomes().get(i))) {
                chromosomes2.add(individual1.getChromosomes().get(i));
            }
        }

        Individual newIndividual1 = model.createIndividual(chromosomes1);
        Individual newIndividual2 = model.createIndividual(chromosomes2);

        return new IndividualPair(newIndividual1, newIndividual2);
    }

    private Individual doSelection() {
        switch (model.getSelectionType()) {
            case CLASSIFICATION:
                return doSelectionByClassification();
            case ROULETTE:
                return doSelectionByRoulette();
        }

        return null;
    }

    private Individual doSelectionByRoulette() {
        // TODO: not implemented.
        return null;
    }

    private Individual doSelectionByClassification() {
        int maxValue = 0;

        for (int i = 0; i < model.getPopulationSize(); i++) {
            maxValue += (i + 1);
        }

        double index = Math.random() * maxValue;
        int cursor = 0;

        for (int i = 0; i < model.getPopulationSize(); i++) {
            cursor += model.getPopulationSize() - i;

            if (index <= cursor) {
                return model.getPopulation().get(i);
            }
        }

        return null;
    }

    private void doElitism(ArrayList<Individual> newGeneration) {
        if (model.getPopulationSize() % 2 == 0) {
            newGeneration.add(model.getPopulation().get(0));
            newGeneration.add(model.getPopulation().get(1));
        } else {
            newGeneration.add(model.getPopulation().get(0));
        }
    }

    private void classify(ArrayList<Individual> population) {
        for (Individual individual : population) {
            individual.calculateValue();
        }

        Collections.sort(population);
    }

    private void saveResultToJSON(HashMap<Integer, ArrayList<Individual>> generationList) {

        File file;
        PrintWriter printWriter;
        int index = 1;
        ArrayList<Individual> individualList;

        try {

            file = new File(new File("./report/public_html/data"), "result.js");
            file.createNewFile();
            printWriter = new PrintWriter(file, "UTF-8");

            printWriter.println("var result = [");

            for (Integer keySet : generationList.keySet()) {

                printWriter.println(String.format("{\"number\": %s, ", keySet));
                printWriter.println("\"population\": {\"individualList\": [");

                individualList = generationList.get(keySet);

                for (int i = 0; i < individualList.size(); i++) {

                    printWriter.println(individualList.get(i).toString());

                    if (i + 1 < individualList.size()) {
                        printWriter.println(", ");
                    }
                }

                printWriter.println("]}}");

                if (index < generationList.size()) {
                    printWriter.println(", ");
                }

                index++;
            }

            printWriter.println("];");
            printWriter.flush();
            printWriter.close();

        } catch (IOException ex) {
            System.out.println("Error writer file. Cause: " + ex.getMessage());
        }

    }
}
