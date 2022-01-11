package pl.shop.bike.models.model.enums;

public enum GenderType {

    MALE("Mężczyzna"), FEMALE("Kobieta");

    private String gender;

    GenderType(String gender) {
        this.gender = gender;
    }

    public static GenderType findGenderByType(String type) {
        for (GenderType typ : GenderType.values()) {
            if (typ.gender.equalsIgnoreCase(type)) {
                return typ;
            }
        }

        throw new IllegalArgumentException("Nie znaleziono takiej płci");
    }
}
