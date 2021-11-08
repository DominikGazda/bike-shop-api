package pl.shop.bike.models.model.enums;

public enum WorkshopType {

    MAINTENANCE("konserwacja"),
    TOOLS("narzędzia"),
    RACKS("stojaki");

    private String name;

    WorkshopType(String name){
        this.name = name;
    }

    public static WorkshopType findWorkshopTypeByName(String name){
        try{
            for(WorkshopType type: WorkshopType.values()){
                if(type.name.equals(name)){
                    return type;
                }
            }
        }catch(Error e){
            throw new IllegalArgumentException("Tutaj będzie message");
        }
        throw new IllegalArgumentException("Nie znaleziono elementu");
    }
}
