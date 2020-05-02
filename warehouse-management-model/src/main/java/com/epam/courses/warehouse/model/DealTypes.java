package com.epam.courses.warehouse.model;

/**
 * Enum deal types.
 */
public enum DealTypes {
    /**
     * Deal types.
     */
    GETTING(0), DELIVERY(1);

    /**
     * Int representation of deal type.
     */
    private int value;

    /**
     * DealType constructor.
     * @param intDealType int value.
     */
    DealTypes(final int intDealType) {
        this.value = intDealType;
    }

    /**
     * Convert to DealType from int.
     * @param value input int.
     * @return DealType or null.
     */
    public static DealTypes fromInt(final int value) {
        switch (value) {
            case 0:
                return GETTING;
            case 1:
                return DELIVERY;
            default:
                return null;
        }
    }

    /**
     * Return int value equivalently DealType.
     * @return int.
     */
    public int getValue() {
        return value;
    }
}
