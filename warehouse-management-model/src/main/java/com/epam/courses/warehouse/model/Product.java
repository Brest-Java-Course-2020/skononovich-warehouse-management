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
     * @param id product id.
     * @return this Product.
     */
    public Product setProductId(final Integer id) {
        this.productId = id;
        return this;
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
     * @param name product name.
     * @return this Product.
     */
    public Product setProductName(final String name) {
        this.productName = name;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Product{"
                + "productId=" + productId
                + ", productName='" + productName + '\''
                + '}';
    }
}
