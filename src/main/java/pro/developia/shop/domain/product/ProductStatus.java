package pro.developia.shop.domain.product;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum ProductStatus {
    WAITING("waiting"),
    APPROVE("approve"),
    REJECT("reject");

    private static final Map<String, ProductStatus> map = new HashMap<>();

    static {
        for (ProductStatus value : values()) {
            map.put(value.name(), value);
        }
    }

    @Getter
    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
