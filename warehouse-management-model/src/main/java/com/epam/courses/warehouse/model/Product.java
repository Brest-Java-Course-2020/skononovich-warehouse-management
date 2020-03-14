package com.epam.courses.warehouse.model;

/**
 * POJO Product.
 */
public class Product {

    /**
     * Product id.
     */
    private Integer productId;

    /**
     * Product name.
     */
    private String productName;

    /**
     * Return <code>Integer</code> representation of this product id.
     * @return productId product id.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Set this product id.
     * @param productId product id.
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * Return <code>String</code> representation of this product name.
     * @return productName product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set this product name.
     * @param productName product name.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productTitle='" + productName + '\'' +
                '}';
    }
}
