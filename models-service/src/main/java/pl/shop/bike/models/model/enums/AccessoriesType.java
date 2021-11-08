package pl.shop.bike.models.model.enums;

import javassist.NotFoundException;

public enum AccessoriesType {

    PUMP("pompka"),
    BAGS("torba"),
    BOTTLE("bidon"),
    FENDERS("błotnik");

    private String name;

    AccessoriesType(String name){
        this.name = name;
    }

    public static AccessoriesType findAccessoriesTypeByName(String name){
        try{
            for(AccessoriesType type: AccessoriesType.values()){
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
