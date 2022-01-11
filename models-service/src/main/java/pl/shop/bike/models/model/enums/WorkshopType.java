package pl.shop.bike.models.model.enums;

public enum WorkshopType {

    MAINTENANCE("Konserwacja"),
    TOOLS("Narzędzia"),
    RACKS("Stojaki");

    private String name;

    WorkshopType(String name) {
        this.name = name;
    }

    public static WorkshopType findWorkshopTypeByName(String name) {
        try {
            for (WorkshopType type : WorkshopType.values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
        } catch (Error e) {
            throw new IllegalArgumentException("Tutaj będzie message");
        }
        throw new IllegalArgumentException("Nie znaleziono elementu");
    }

    public static WorkshopType findWorkshopByType(String type) {
        for (WorkshopType typ : WorkshopType.values()) {
            if (typ.name.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono");
    }
}
