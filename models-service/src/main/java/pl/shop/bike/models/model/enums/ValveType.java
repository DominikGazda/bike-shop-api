package pl.shop.bike.models.model.enums;

public enum ValveType {

    BIKE("Pompka rowerowa"),
    CAR("Pompka samochodowa");

    private String name;

    ValveType(String name) {
        this.name = name;
    }

    public static ValveType findValveType(String value) {
        try {
            return ValveType.findValveType(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("to jest error");
        }
    }

    public static ValveType findValveByType(String type) {
        for (ValveType typ : ValveType.values()) {
            if (typ.name.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiej pompki");
    }
}
