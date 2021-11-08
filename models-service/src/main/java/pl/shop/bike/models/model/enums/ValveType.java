package pl.shop.bike.models.model.enums;

public enum ValveType {

    BIKE("Pompka rowerowa"),
    CAR("Pompka samochodowa");

    private String name;

    ValveType(String name){
        this.name = name;
    }

    public static ValveType findValveType(String value){
        try{
            return ValveType.findValveType(value);
        } catch (Exception e){
            throw new IllegalArgumentException("to jest error");
        }
    }
}
