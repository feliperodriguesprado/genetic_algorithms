package br.edu.univas.ga.production;

import br.edu.univas.ga.framework.Chromosome;
import java.util.HashMap;
import java.util.Objects;

public class ProductionChromosome extends Chromosome {

    private int employeeId;
    private String employeeName;
    private Machine machine;
    private EmployeeSkill skill;
    private HashMap<EmployeeSkill, MachineType> skillList = new HashMap();

    public ProductionChromosome() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public EmployeeSkill getSkill() {
        return skill;
    }

    public void setSkill(EmployeeSkill skill) {
        this.skill = skill;
    }

    public HashMap<EmployeeSkill, MachineType> getSkillList() {
        return skillList;
    }

    public void setSkillList(HashMap<EmployeeSkill, MachineType> skillList) {
        this.skillList = skillList;
    }

    public void addSkill(EmployeeSkill skill, MachineType machineType) {
        skillList.put(skill, machineType);
    }

    @Override
    public void doMutation() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.employeeId;
        hash = 19 * hash + Objects.hashCode(this.employeeName);
        hash = 19 * hash + Objects.hashCode(this.machine);
        hash = 19 * hash + Objects.hashCode(this.skill);
        hash = 19 * hash + Objects.hashCode(this.skillList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductionChromosome other = (ProductionChromosome) obj;
        if (this.employeeId != other.employeeId) {
            return false;
        }
        if (!Objects.equals(this.employeeName, other.employeeName)) {
            return false;
        }
        if (this.machine != other.machine) {
            return false;
        }
        if (this.skill != other.skill) {
            return false;
        }
        if (!Objects.equals(this.skillList, other.skillList)) {
            return false;
        }
        return true;
    }

    @Override
    public Chromosome clone() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("{\"employeeId\": %s, \"employeeName\": \"%s\", \"machine\": %s}, \"skill\": %s}", employeeId, employeeName, machine, skill);
    }

}
