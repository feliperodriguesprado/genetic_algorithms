package br.edu.univas.ga.production;

import br.edu.univas.ga.framework.Chromosome;
import br.edu.univas.ga.framework.Individual;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductionIndividual extends Individual {

    public ProductionIndividual() {

        List<ProductionChromosome> chromosomeList;
        chromosomes = new ArrayList<>();
        chromosomeList = getChromosomeList();
        chromosomeList = mountChromosomeListToIndividual(chromosomeList);

        for (ProductionChromosome chromosome : chromosomeList) {
            if (chromosome.getMachine() != null && chromosome.getSkill() != null) {
                chromosomes.add(chromosome);
            }
        }

        Collections.shuffle(chromosomes);

    }

    public ProductionIndividual(ArrayList<Chromosome> chromosomes) {
        super(chromosomes);
    }

    @Override
    public void calculateValue() {

        float productionTotal = 0;
        float partsValue = 0;
        float skillValue = 0;

        for (Chromosome chromosome : chromosomes) {
            ProductionChromosome productionChromosome = (ProductionChromosome) chromosome;

            partsValue = productionChromosome.getMachine().getType().getProduction();
            skillValue = productionChromosome.getSkill().getValue();

            productionTotal += (partsValue * skillValue) / 100;

        }

        //System.out.println(String.format("Total de peças por hora indivíduo %s = %s", this.toString(), productionTotal));
        setValue(productionTotal);
    }

    private List<ProductionChromosome> getChromosomeList() {

        List<ProductionChromosome> chromosomeList = new ArrayList<>();
        ProductionChromosome chromosome;

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(100);
        chromosome.setEmployeeName("Carlos");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE1);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(101);
        chromosome.setEmployeeName("Henrique");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE3);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(102);
        chromosome.setEmployeeName("Vanessa");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE4);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(103);
        chromosome.setEmployeeName("Maria");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE3);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(104);
        chromosome.setEmployeeName("Robson");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE4);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(105);
        chromosome.setEmployeeName("Sônia");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE2);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(106);
        chromosome.setEmployeeName("Marcelo");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE2);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(107);
        chromosome.setEmployeeName("Rodrigo");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE1);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(108);
        chromosome.setEmployeeName("Júlia");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE3);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE4);

        chromosomeList.add(chromosome);

        chromosome = new ProductionChromosome();
        chromosome.setEmployeeId(109);
        chromosome.setEmployeeName("Jéferson");
        chromosome.addSkill(EmployeeSkill.SKILL0, MachineType.TYPE1);
        chromosome.addSkill(EmployeeSkill.SKILL1, MachineType.TYPE4);
        chromosome.addSkill(EmployeeSkill.SKILL2, MachineType.TYPE2);
        chromosome.addSkill(EmployeeSkill.SKILL3, MachineType.TYPE3);

        chromosomeList.add(chromosome);

        return chromosomeList;

    }

    private List<ProductionChromosome> mountChromosomeListToIndividual(List<ProductionChromosome> chromosomeList) {

        List<ProductionChromosome> chromosomeListToIndividual = new ArrayList<>();
        MachineType type;
        Machine machine;
        boolean machineAdded;

        Collections.shuffle(chromosomeList);

        for (ProductionChromosome chromosome : chromosomeList) {

            machineAdded = false;

            if (!machineAdded) {
                type = chromosome.getSkillList().get(EmployeeSkill.SKILL3);
                machine = checkChromosomeListToIndividual(chromosomeList, type);

                if (machine != null) {
                    chromosome.setMachine(machine);
                    chromosome.setSkill(EmployeeSkill.SKILL3);
                    machineAdded = true;
                }
            }

            if (!machineAdded) {
                type = chromosome.getSkillList().get(EmployeeSkill.SKILL2);
                machine = checkChromosomeListToIndividual(chromosomeList, type);

                if (machine != null) {
                    chromosome.setMachine(machine);
                    chromosome.setSkill(EmployeeSkill.SKILL2);
                    machineAdded = true;
                }
            }

            if (!machineAdded) {
                type = chromosome.getSkillList().get(EmployeeSkill.SKILL1);
                machine = checkChromosomeListToIndividual(chromosomeList, type);

                if (machine != null) {
                    chromosome.setMachine(machine);
                    chromosome.setSkill(EmployeeSkill.SKILL1);
                    machineAdded = true;
                }
            }

            if (!machineAdded) {
                type = chromosome.getSkillList().get(EmployeeSkill.SKILL0);
                machine = checkChromosomeListToIndividual(chromosomeList, type);

                if (machine != null) {
                    chromosome.setMachine(machine);
                    chromosome.setSkill(EmployeeSkill.SKILL0);
                }
            }

            chromosomeListToIndividual.add(chromosome);

        }

        return chromosomeListToIndividual;
    }

    private Machine checkChromosomeListToIndividual(List<ProductionChromosome> chromosomeList, MachineType machineType) {

        int indexToDelete;
        List<Machine> machineList = new ArrayList<>();

        for (Machine machine : Machine.values()) {
            if (machine.getType() == machineType) {
                machineList.add(machine);
            }
        }

        for (ProductionChromosome chromosome : chromosomeList) {

            indexToDelete = -1;

            if (!machineList.isEmpty()) {

                for (int i = 0; i < machineList.size(); i++) {
                    if (chromosome.getMachine() == machineList.get(i)) {
                        indexToDelete = i;
                        break;
                    }
                }

                if (indexToDelete >= 0) {
                    machineList.remove(indexToDelete);
                }

            }

        }

        if (!machineList.isEmpty()) {
            return machineList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public int compareTo(Individual individual) {
        if (getValue() == individual.getValue()) {
            return 0;
        } else if (getValue() < individual.getValue()) {
            return -1;
        } else {
            return 1;
        }
    }

}
