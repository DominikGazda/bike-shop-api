package pl.shop.bike.models.model.enums;

public enum BikeType {

    BMX("BMX"),
    CROSS("Crossowe"),
    ELECTRIC("Elektryczne"),
    MTB("Górskie MTB"),
    GRAVEL("Gravele i przełajowe"),
    URBAN("Miejskie"),
    TREKKING("Trekkingowe");


    private String type;

    BikeType(String type) {
        this.type = type;
    }

    public static BikeType findBikeByType(String types) {
        for (BikeType typ : BikeType.values()) {
            if (typ.type.equalsIgnoreCase(types)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiego typu roweru");
    }
}
