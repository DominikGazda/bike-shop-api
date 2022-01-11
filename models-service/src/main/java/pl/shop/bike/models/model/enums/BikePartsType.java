package pl.shop.bike.models.model.enums;

public enum BikePartsType {

    BRAKE("hamulce"),
    DRIVE("napęd"),
    FRAME("rama");

    private String name;

    BikePartsType(String name) {
        this.name = name;
    }

    public static BikePartsType findBikePartsTypeByName(String name) {
        try {
            for (BikePartsType type : BikePartsType.values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
        } catch (Error e) {
            throw new IllegalArgumentException("Tutaj będzie message");
        }

        throw new IllegalArgumentException("Nie znaleziono elementu");
    }

    public static BikePartsType findBikeByType(String type) {
        for (BikePartsType typ : BikePartsType.values()) {
            if (typ.name.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiego typu części rowerowej");
    }
}
