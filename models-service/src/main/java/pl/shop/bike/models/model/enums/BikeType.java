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

    BikeType(String type){
        this.type = type;
    }

    public static BikeType findBikeByType(String type){
        try{
            return BikeType.valueOf(type);
        } catch (Exception ex){
            throw new IllegalArgumentException("tutaj trzeba dodac error");
        }
    }
}
