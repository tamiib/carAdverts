package com.codevibe.codevibe.enums;

public enum FuelType {
    DIEZEL("diezel"),
    PETROL("petrol"),
    GAS("gas"),
    ELECTRICITY("electricity");

    private final String value;

    FuelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FuelType fromValue(String value) {
        for (FuelType fuelType : FuelType.values()) {
            if (fuelType.value.equalsIgnoreCase(value)) {
                return fuelType;
            }
        }
        throw new IllegalArgumentException("Unsupported fuel type: " + value);
    }

}
