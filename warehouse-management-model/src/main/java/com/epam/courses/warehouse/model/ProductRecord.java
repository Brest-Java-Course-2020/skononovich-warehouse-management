package com.epam.courses.warehouse.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productRecordDate;

    /**
     * Deal type.
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
     * @param id product record id.
     * @return this ProductRecord.
     */
    public ProductRecord setProductRecordId(final Integer id) {
        this.productRecordId = id;
        return this;
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
     * @param id product id.
     * @return this ProductRecord.
     */
    public ProductRecord setProductId(final Integer id) {
        this.productId = id;
        return this;
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
     * @param quantity quantity of product.
     * @return this ProductRecord.
     */
    public ProductRecord setQuantityOfProduct(final Integer quantity) {
        this.quantityOfProduct = quantity;
        return this;
    }

    /**
     * Return <code>Date</code> representation of deal date.
     * @return productRecordDate product record date.
     */
    public Date getProductRecordDate() {
        if (this.productRecordDate == null) {
            return null;
        }
        return new Date(productRecordDate.getTime());
    }

    /**
     * Set the product record date.
     * @param date product record date.
     * @return this ProductRecord.
     */
    public ProductRecord setProductRecordDate(final Date date) {
        if (date == null) {
            this.productRecordDate = null;
        } else {
            this.productRecordDate = new Date(date.getTime());
        }
        return this;
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
     * @param pDealType deal type.
     * @return this ProductRecord.
     */
    public ProductRecord setDealType(final DealTypes pDealType) {
        this.dealType = pDealType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductRecord{"
                + "productRecordId=" + productRecordId
                + ", productId=" + productId
                + ", quantityOfProduct=" + quantityOfProduct
                + ", productRecordDate=" + productRecordDate
                + ", dealType=" + dealType
                + '}';
    }
}
