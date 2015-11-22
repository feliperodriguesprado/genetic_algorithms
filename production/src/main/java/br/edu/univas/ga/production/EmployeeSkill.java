package br.edu.univas.ga.production;

public enum EmployeeSkill {

    SKILL0(0, "Não possui habilidade", 0),
    SKILL1(1, "Regular", 40),
    SKILL2(2, "Bom", 80),
    SKILL3(3, "Ótimo", 120);

    private final int code;
    private final String description;
    private final float value;

    private EmployeeSkill(int code, String description, float value) {
        this.code = code;
        this.description = description;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{\"code\": %s, \"description\": \"%s\", \"value\": %s}", code, description, value);
    }

}
