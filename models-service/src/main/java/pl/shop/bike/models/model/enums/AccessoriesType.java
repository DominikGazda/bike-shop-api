package pl.shop.bike.models.model.enums;

public enum AccessoriesType {

    PUMP("Pompki"),
    BAGS("Torby i sakwy"),
    BOTTLE("Bidony"),
    FENDERS("Błotniki");

    private String name;

    AccessoriesType(String name) {
        this.name = name;
    }

    public static AccessoriesType findAccessoriesTypeByName(String name) {
        try {
            for (AccessoriesType type : AccessoriesType.values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
        } catch (Error e) {
            throw new IllegalArgumentException("Tutaj będzie message");
        }

        throw new IllegalArgumentException("Nie znaleziono elementu");
    }

    public static AccessoriesType findAccessoriesByType(String type) {
        for (AccessoriesType typ : AccessoriesType.values()) {
            if (typ.name.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiego typu akcesoria");
    }
}
