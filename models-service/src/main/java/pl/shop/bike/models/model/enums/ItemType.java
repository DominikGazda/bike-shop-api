package pl.shop.bike.models.model.enums;

import java.util.Locale;

public enum ItemType {

    BIKES("bikes"),
    ACCESSORIES("accessories"),
    PARTS("parts"),
    WORKSHOP("workshop");

    private String type;

    ItemType(String type){
        this.type = type;
    }

}
