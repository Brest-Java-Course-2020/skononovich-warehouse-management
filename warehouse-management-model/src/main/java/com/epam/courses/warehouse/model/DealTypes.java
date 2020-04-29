package com.epam.courses.warehouse.model;

/**
 * Enum deal types.
 */
public enum DealTypes {
    GETTING(0), DELIVERY(1);
    private int value;

    DealTypes(int value){
        this.value = value;
    }

    public static DealTypes fromInt(int x) {
        switch(x) {
            case 0:
                return GETTING;
            case 1:
                return DELIVERY;
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
