package id.ac.ui.cs.advprog.toytopiaorder.enums;
import lombok.Getter;

@Getter
public enum OrderStatus {
    WAITING_VERIFICATION("WAITING_VERIFICATION"),
    CANCELED("CANCELED"),
    SET_DELIVERY("SET_DELIVERY"),
    IN_DELIVERY("IN_DELIVERY"),
    COMPLETED("COMPLETED");

    private final String value;
    private OrderStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}