package pl.shop.bike.models.model.enums;

public enum ImageType {

    BIKES,
    ACCESSORIES,
    WORKSHOP;

    ImageType() {
    }

    public static ImageType findImage(String value) {
        try {
            return ImageType.valueOf(value);
        } catch (Error e) {
            throw new IllegalArgumentException("tutaj error message");
        }
    }
}
