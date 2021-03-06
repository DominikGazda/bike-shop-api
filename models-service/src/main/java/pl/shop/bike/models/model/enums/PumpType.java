package pl.shop.bike.models.model.enums;

public enum PumpType {

    HAND("Pompka ręczna"),
    AUTOMATIC("Pompka automatyczna");

    private String name;

    PumpType(String name) {
        this.name = name;
    }

    public static PumpType findPumpType(String value) {
        try {
            return PumpType.valueOf(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tutaj message trzeba zrobić");
        }
    }

    public static PumpType findPumpByType(String type) {
        for (PumpType typ : PumpType.values()) {
            if (typ.name.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiej pompki");
    }
}
