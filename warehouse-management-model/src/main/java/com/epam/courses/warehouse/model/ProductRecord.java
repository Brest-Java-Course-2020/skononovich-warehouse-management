package com.epam.courses.warehouse.model;

import java.sql.Date;

/**
 * POJO Product record.
 */
public class ProductRecord {
    /**
     * Product record id.
     */
    private Integer productRecordId;

    /**
     * Product id.
     */
    private Integer productId;

    /**
     * Quantity of product.
     */
    private Integer quantityOfProduct;

    /**
     * Product record date.
     */
    private Date productRecordDate;

    /**
     * Deal type
     */
    private DealTypes dealType;

    /**
     * Return <code>Integer</code> representation of this product record id.
     * @return productRecordId product record id.
     */
    public Integer getProductRecordId() {
        return productRecordId;
    }

    /**
     * Set the product record id.
     * @param productRecordId product record id.
     */
    public void setProductRecordId(Integer productRecordId) {
        this.productRecordId = productRecordId;
    }

    /**
     * Return <code>Integer</code> representation of this product id.
     * @return productId product Id.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Set the product id.
     * @param productId product id.
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Return <code>Integer</code> representation of product quantity.
     * @return quantityOfProduct quantity of product.
     */
    public Integer getQuantityOfProduct() {
        return quantityOfProduct;
    }

    /**
     * Set the quantity of product.
     * @param quantityOfProduct quantity of product.
     */
    public void setQuantityOfProduct(Integer quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    /**
     * Return <code>Date</code> representation of deal date.
     * @return productRecordDate product record date.
     */
    public Date getProductRecordDate() {
        return productRecordDate;
    }

    /**
     * Set the product record date.
     * @param productRecordDate product record date.
     */
    public void setProductRecordDate(Date productRecordDate) {
        this.productRecordDate = productRecordDate;
    }

    /**
     * Return <code>DealType</code> representation of deal type.
     * @return dealType deal type.
     */
    public DealTypes getDealType() {
        return dealType;
    }

    /**
     * Set the deal type.
     * @param dealType deal type.
     */
    public void setDealType(DealTypes dealType) {
        this.dealType = dealType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductRecord{" +
                "productRecordId=" + productRecordId +
                ", productId=" + productId +
                ", quantityOfProduct=" + quantityOfProduct +
                ", productRecordDate=" + productRecordDate +
                ", dealType=" + dealType +
                '}';
    }
}
