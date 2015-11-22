package br.edu.univas.ga.production;

public enum Machine {

    MACHINE_1(1, "Máquina 1", MachineType.TYPE1),
    MACHINE_2(2, "Máquina 2", MachineType.TYPE1),
    MACHINE_3(3, "Máquina 3", MachineType.TYPE2),
    MACHINE_4(4, "Máquina 4", MachineType.TYPE2),
    MACHINE_5(5, "Máquina 5", MachineType.TYPE3),
    MACHINE_6(6, "Máquina 6", MachineType.TYPE3),
    MACHINE_7(7, "Máquina 7", MachineType.TYPE4),
    MACHINE_8(8, "Máquina 8", MachineType.TYPE4);

    private final int code;
    private final String description;
    private final MachineType type;

    private Machine(int code, String description, MachineType type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public MachineType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("{\"code\": %s, \"description\": \"%s\", \"type\": %s}", code, description, type);
    }

}
