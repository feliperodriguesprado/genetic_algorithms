package br.edu.univas.ga.production;

public enum MachineType {

    TYPE1(1, "Tipo 1", 100),
    TYPE2(2, "Tipo 2", 200),
    TYPE3(3, "Tipo 3", 300),
    TYPE4(4, "Tipo 4", 400);

    private final int code;
    private final String description;
    private final float production;

    private MachineType(int code, String description, float production) {
        this.code = code;
        this.description = description;
        this.production = production;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public float getProduction() {
        return production;
    }

    @Override
    public String toString() {
        return String.format("{\"code\": %s, \"description\": \"%s\", \"production\": \"%s/h\"", code, description, production);
    }

}
