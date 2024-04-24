package id.ac.ui.cs.advprog.toytopiaorder.enums;

import lombok.Getter;

@Getter
public enum DeliveryMethod {
    JTE("JTE"),
    GOBEK("GOBEK"),
    SIWUZZ("SIWUZZ");

    private final String value;
    private DeliveryMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (DeliveryMethod method : DeliveryMethod.values()) {
            if (method.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}