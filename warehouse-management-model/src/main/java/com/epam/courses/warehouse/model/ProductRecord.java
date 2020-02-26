package com.epam.courses.warehouse.model;

import java.sql.Date;

public class ProductRecord {
    Integer productRecordId;
    Integer productId;
    Integer quantityOfProduct;
    Date productRecordDate;
    Byte dealType; //0 - delivery, 1 - receipt

    public Integer getProductRecordId() {
        return productRecordId;
    }

    public void setProductRecordId(Integer productRecordId) {
        this.productRecordId = productRecordId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(Integer quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public Date getProductRecordDate() {
        return productRecordDate;
    }

    public void setProductRecordDate(Date productRecordDate) {
        this.productRecordDate = productRecordDate;
    }

    public Byte getDealType() {
        return dealType;
    }

    public void setDealType(Byte dealType) {
        this.dealType = dealType;
    }

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
