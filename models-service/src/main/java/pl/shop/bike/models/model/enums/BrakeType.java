package pl.shop.bike.models.model.enums;

public enum BrakeType {

    MECHANICAL("Hamulce mechaniczne"),
    HYDRAULIC("Hamulce hydrauliczne"),
    VBRAKE("Hamulce typu V-BRAKE"),
    ROLLER("Hamulce rolkowe"),
    UBRAKE("Hamulce typu U-BRAKE"),
    TORPEDO("Hamulec torpedo");

    private String name;

    BrakeType(String name){
        this.name = name;
    }

    public static BrakeType findBrakeType(String value){
        try{
            return BrakeType.valueOf(value);
        } catch (Exception e){
            throw new IllegalArgumentException("Tu będzie error");
        }
    }
}
